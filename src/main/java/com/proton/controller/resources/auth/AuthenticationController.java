package com.proton.controller.resources.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.proton.services.user.AuthenticationService;

import java.io.IOException;

@RestController
@RequestMapping("/protoon/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(service.register(request));
  }

  @PostMapping("/register/municipe")
  public ResponseEntity<AuthenticationResponse> registerMunicipe(
    @RequestBody RegisterRequestMunicipe request,
    HttpServletResponse httpResponse // Inject HttpServletResponse
) {
    AuthenticationResponse authenticationResponse = service.registerMunicipe(request);

    // Set the access token as an HttpOnly cookie in the response
    Cookie tokenCookie = new Cookie("token", authenticationResponse.getAccessToken());
    tokenCookie.setHttpOnly(true); // Set HttpOnly flag
    tokenCookie.setPath("/"); // Set cookie path as needed
    httpResponse.addCookie(tokenCookie);

    // Optionally, you can also set the refresh token as a separate HttpOnly cookie if needed

    return ResponseEntity.ok(authenticationResponse);
}

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request,
      HttpServletResponse httpResponse
  ) {
      AuthenticationResponse authenticationResponse = service.authenticate(request);
  
      // Set the access token as an HttpOnly cookie in the response
      Cookie tokenCookie = new Cookie("token", authenticationResponse.getAccessToken());
      tokenCookie.setHttpOnly(true); // Set HttpOnly flag
      tokenCookie.setPath("/"); // Set cookie path as needed
      httpResponse.addCookie(tokenCookie);
  
      // Optionally, you can also set the refresh token as a separate HttpOnly cookie if needed
  
      return ResponseEntity.ok(authenticationResponse);
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }

  @GetMapping("/esta-logado")
public ResponseEntity<Boolean> isTokenValid(HttpServletRequest request) {
    boolean isTokenValid = service.isTokenValid(request);
    return ResponseEntity.ok(isTokenValid);
}


}
