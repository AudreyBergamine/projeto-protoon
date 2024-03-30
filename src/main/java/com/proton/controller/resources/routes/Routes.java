package com.proton.controller.resources.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Routes {

    @GetMapping("/")
    // @PreAuthorize("hasRole('MUNICIPE')")
    public String home() {
        return "/homePage";
    }

    @GetMapping("/welcomeUser")
    // @PreAuthorize("hasRole('MUNICIPE')")
    public String welcomeUser() {
        return "welcomeUser";
    }

    @GetMapping("/welcomeAdmin")
    // @PreAuthorize("hasRole('ADMIN')")
    public String welcomeAdmin() {
        return "welcomeAdmin";
    }
}
