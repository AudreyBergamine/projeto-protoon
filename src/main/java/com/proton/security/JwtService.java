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
<<<<<<< HEAD
    long expiry = 1000L;
=======
<<<<<<< HEAD
    long expiry = 1500L; // 100 segundos
=======
    long expiry = 150000L; // 100 segundos
>>>>>>> eb5b9779278359f1bcbc9e1c17597cc2f55f8c28
>>>>>>> fd4b251f26ff4a7c282aabbc2a3c064042ea2f2a

    String scope = authentication
        .getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors
            .joining(" "));

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
