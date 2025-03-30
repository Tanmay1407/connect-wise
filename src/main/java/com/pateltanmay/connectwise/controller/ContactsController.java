package com.pateltanmay.connectwise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller()
@RequestMapping("/user/contacts")
public class ContactsController {

    @RequestMapping("/add")
    public String requestMethodName() {
        return "user/add_contacts";
    }
    
  
}
