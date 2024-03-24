package com.proton.controller.resources.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proton.security.AuthenticationService;

@RestController
public class Routes {
    @Autowired
  private AuthenticationService authenticationService;
    
    @GetMapping("/welcomeUser")
    @PreAuthorize("hasRole('MUNICIPE')")
    public String welcomeUser() {
        return "welcomeUser";
    }

    @GetMapping("/welcomeAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public String welcomeAdmin() {
        return "welcomeAdmin";
    }

    @GetMapping("/authenticate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> authenticate(Authentication authentication) {
        String token = authenticationService.authenticate(authentication);
        return ResponseEntity.ok(token);
    }

}
    
