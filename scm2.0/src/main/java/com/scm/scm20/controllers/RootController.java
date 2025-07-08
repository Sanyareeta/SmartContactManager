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
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class RootController {
    private  final Logger logger= LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication) {
        if(authentication==null)return;
        String email = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(email);

       logger.info(String.valueOf(user));

        logger.info(user.getName());
        logger.info(user.getEmail());
        model.addAttribute("loggedInUser", user);
    }
    }

