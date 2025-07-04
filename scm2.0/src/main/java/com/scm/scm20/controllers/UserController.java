package com.scm.scm20.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/dashboard")
    public String userDashboard(){
        return "user/dashboard";
    }

    @GetMapping("/profile")
    public String userProfile(Principal principal){
        String name=principal.getName();
        return "user/profile";
    }

}