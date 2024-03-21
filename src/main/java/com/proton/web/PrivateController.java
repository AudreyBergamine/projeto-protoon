package com.proton.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proton.security.AuthenticationService;

@RestController
@RequestMapping("private") //O token é passado para esta rota se precisar pra algo
public class PrivateController {

  @Autowired
  private AuthenticationService authenticationService;

  @GetMapping
  public String getMessage(Authentication authentication) {
    String token = authenticationService.authenticate(authentication);
    return "Você tem acesso Privado " + token;
  }
}
