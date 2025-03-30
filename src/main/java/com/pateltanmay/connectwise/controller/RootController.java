package com.pateltanmay.connectwise.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.pateltanmay.connectwise.entities.User;
import com.pateltanmay.connectwise.helpers.Helper;
import com.pateltanmay.connectwise.services.UserService;

@ControllerAdvice
public class RootController {
Logger logger = LoggerFactory.getLogger(RootController.class);
    @Autowired
    UserService userService;

    @ModelAttribute
    public void addLoggedInUser(Model model, Authentication authentication) {
        if(authentication == null) return;
        User loggedUser = userService.getUserByEmail(Helper.getLoggedInUserEmail(authentication));
        if(loggedUser == null) logger.info("No User is currently logged in");
        else logger.info("Logged In User: {}", loggedUser);
        model.addAttribute("loggedInUser", loggedUser);
    }
}
