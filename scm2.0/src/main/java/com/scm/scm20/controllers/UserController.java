package com.scm.scm20.controllers;

import com.scm.scm20.entities.User;
import com.scm.scm20.helper.Helper;
import com.scm.scm20.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private  final Logger logger=LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public String userDashboard(){
        return "user/dashboard";
    }

    @GetMapping("/profile")
    public String userProfile(Model model, Authentication authentication){
//        if (authentication != null) {
//            String email = Helper.getEmailOfLoggedInUser(authentication);
//            User user = userService.getUserByEmail(email);
//            model.addAttribute("loggedInUser", user); // ✅ Add it directly here to be sure
//        }
        return "user/profile";
    }

}