package com.pateltanmay.connectwise.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pateltanmay.connectwise.entities.User;
import com.pateltanmay.connectwise.helpers.Helper;
import com.pateltanmay.connectwise.services.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;

    @RequestMapping(value = "/dash", method=RequestMethod.GET)
    public String userDashboard() {
        return new String("user/dashboard");
    }

    @RequestMapping(value = "/profile", method=RequestMethod.GET)
    public String userProfile() {
        return new String("user/profile");
    }
    
}
