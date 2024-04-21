package com.proton.services.user;

import com.proton.services.exceptions.ConstraintException;
import com.proton.services.exceptions.InvalidFieldsException;
import com.proton.services.jwt.JwtService;
import com.proton.services.validations.RegisterValidationService;
import com.proton.models.entities.token.Token;
import com.proton.models.repositories.MunicipeRepository;
import com.proton.models.repositories.TokenRepository;
import com.proton.models.entities.token.TokenType;
import com.proton.controller.resources.auth.AuthenticationRequest;
import com.proton.controller.resources.auth.AuthenticationResponse;
import com.proton.controller.resources.auth.requests.RegisterRequest;
import com.proton.controller.resources.auth.requests.RegisterRequestMunicipe;
import com.proton.models.entities.municipe.Municipe;
import com.proton.models.entities.user.User;
import com.proton.models.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import static com.proton.models.entities.roles.Role.MUNICIPE;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final MunicipeRepository municipeRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final RegisterValidationService registerValidationService;

  public AuthenticationResponse register(RegisterRequest request) {
    var user = User.builder()
        .nome(request.getNome())
        .email(request.getEmail())
        .senha(passwordEncoder.encode(request.getSenha()))
        .role(request.getRole())
        .build();
     
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(savedUser.getId(), user);
    var refreshToken = jwtService.generateRefreshToken(savedUser.getId(), user);
    saveUserToken(savedUser, jwtToken);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
            .refreshToken(refreshToken)
        .build();
  }

  //MÉTODO CADASTRAR MUNICIPE
  public AuthenticationResponse registerMunicipe(RegisterRequestMunicipe request){
    try {
      //Método valida os campos da requisição, se tiver algum inválido, é lançaca uma InvalidFieldsException
      registerValidationService.validateMunicipeFields(request);
      registerValidationService.validateCPF(request.getNum_CPF());

      //Caso os campos estejam validos, é construído um Municipe.
      var user = Municipe.builder()
      .nome(request.getNome())
      .email(request.getEmail())
      .senha(passwordEncoder.encode(request.getSenha()))
      .role(MUNICIPE)
      .num_CPF(request.getNum_CPF())
      .celular(request.getCelular())
      .data_nascimento(request.getData_nascimento())
      .endereco(request.getEndereco())
      .build();

      //Logo após, o Municipe é salvo usando os métodos  de User (isso é possível por causa da herança)
      var savedUser = municipeRepository.save(user);
      //Depois são gerados tokens e tanto o user quanto o token são salvos no banco de dados.
      var jwtToken = jwtService.generateToken(savedUser.getId(), user);
      var refreshToken = jwtService.generateRefreshToken(savedUser.getId(), user);
      saveUserToken(savedUser, jwtToken);

      return AuthenticationResponse.builder() //Retorna o token, o id e a role do municipe, como resposta json.
      .id(savedUser.getId()) //Retorna o id
      .role(savedUser.getRole()) //Retorna a role
      .accessToken(jwtToken)
          .refreshToken(refreshToken)
      .build();

//Caso seja capturado o erro de IntegrityViolation (unique), do banco de dados, ele será enviado de forma personalizada
    } catch (DataIntegrityViolationException e) {
      String message = e.getMessage(); //Pega a mensagem de erro
      String fieldName = extractFieldName(message); //Pega a mensagem contendo a coluna que deu erro no banco de dados.
      if (fieldName != null) {
          if (fieldName.contains("EMAIL")) {
              throw new ConstraintException("O email já está em uso.");
          } else if (fieldName.contains("NUM_CPF")) {
              throw new ConstraintException("O CPF já está em uso.");
          } else {
              throw new ConstraintException("Erro de violação de constraint.");
          }
      } else {
          throw new ConstraintException("Erro de violação de constraint.");
      }
  }
}

//MÉTODO DE LOGIN
  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getSenha()
        )
    );
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user.getId(), user);
    var refreshToken = jwtService.generateRefreshToken(user.getId(), user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
        .id(user.getId())
        .role(user.getRole())
        .accessToken(jwtToken)
            .refreshToken(refreshToken)
        .build();
  }

  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }
    refreshToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(refreshToken);
    if (userEmail != null) {
      var user = this.repository.findByEmail(userEmail)
              .orElseThrow();
      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(user.getId(), user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        var authResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }

    
  }

  public boolean isTokenValid(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                boolean isTokenValid = tokenRepository.findByToken(token)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);
                return isTokenValid;
                
            }
        }
    }
    return false;
}


//Função criada para extrair o nome da mensagem de erro. Como a mensagem é muito grande
//Esse método ajuda a extrair uma parte determinada da mensagem, para o tratamento correto
private String extractFieldName(String errorMessage) {

  //Cria um padrão da mensagem de erro, para ser delimitado
  Pattern pattern = Pattern.compile("ON PUBLIC\\.MUNICIPE\\((.*?) ");
  //Verifica se o padrão foi encontrado e deliita a mensagem (remove mensagens que não tem haver com o padrão acima)
  Matcher matcher = pattern.matcher(errorMessage);
  
  //Se o padrão for encontrado e bater, retorna a String correpondente (no caso é email ou CPF)
  if (matcher.find()) {
      return matcher.group(1);
  } else {
      return null;
  }
}
}
