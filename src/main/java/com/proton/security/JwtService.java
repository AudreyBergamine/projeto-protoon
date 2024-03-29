package com.proton.security;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
  private final JwtEncoder encoder;

  public JwtService(JwtEncoder encoder) {
    this.encoder = encoder;
  }

  public String generateToken(Authentication authentication) {
    Instant now = Instant.now();
    long expiry = 100L; // 100 segundos

    String scope = authentication
        .getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .map(authority -> "SCOPE_" + authority) // Adiciona "SCOPE_" antes da autoridade
        .collect(Collectors.joining(" "));


    JwtClaimsSet claims = JwtClaimsSet.builder()
        .issuer("spring-security-jwt")
        .issuedAt(now)
        .expiresAt(now.plusSeconds(expiry))
        .subject(authentication.getName())
        .claim("scope", scope)
        .build();

    return encoder.encode(
        JwtEncoderParameters.from(claims))
        .getTokenValue();
  }

}
