package com.pateltanmay.connectwise.helpers;


import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

@Component
public class SessionHelper {

    public static void removeMessage(){
        try {
            System.out.println("Removing message from Session");
            HttpSession httpSession = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
            httpSession.removeAttribute("message");
        } catch (Exception e) {
            System.out.println("Error in Session Helper: "+e);
        }
    }
    
}
