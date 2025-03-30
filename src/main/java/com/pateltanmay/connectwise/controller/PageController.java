package com.pateltanmay.connectwise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pateltanmay.connectwise.entities.MessageType;
import com.pateltanmay.connectwise.entities.User;
import com.pateltanmay.connectwise.forms.RegisterForm;
import com.pateltanmay.connectwise.helpers.Message;
import com.pateltanmay.connectwise.services.impl.UserServiceImpl;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class PageController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/")
    public String indexString() {
        return "redirect:/home";
    }
    

    @RequestMapping("home")
    public String homePage(){
        return "home";
    }

    @RequestMapping("about")
    public String aboutPage() {
        return "about";
    }

    @RequestMapping("service")
    public String servicePage() {
        return "service";
    }

    @RequestMapping("login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("signup")
    public String signupPage(Model model) {
        RegisterForm registerForm = new RegisterForm();
        model.addAttribute("registerForm", registerForm);
        return "signup";
    }

    @RequestMapping("contact")
    public String contactPage() {
        return "contact";
    }

    @RequestMapping(path = "do-register", method=RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute RegisterForm registerForm, BindingResult rBindingResult, HttpSession httpSession) {
        
        if(rBindingResult.hasErrors()){
            return "signup";
        }

        User newUser = new User();
        newUser.setName(registerForm.getName());
        newUser.setEmail(registerForm.getEmail());
        newUser.setPassword(registerForm.getPassword());
        newUser.setPhoneNumber(registerForm.getPhoneNumber());
        newUser.setAbout(registerForm.getAbout());
        userServiceImpl.saveUser(newUser);

        Message message = Message.builder().content("Signup Successful").messageType(MessageType.green).build();
        httpSession.setAttribute("message", message);

        return "redirect:/signup";
    }
    
    
    
    
}
