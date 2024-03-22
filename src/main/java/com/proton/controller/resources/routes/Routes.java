package com.proton.controller.resources.routes;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Routes {
    
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
    public String home() {
        return "";
    }
}
    
