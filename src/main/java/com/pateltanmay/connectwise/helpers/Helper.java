package com.pateltanmay.connectwise.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

public class Helper {
    static Logger logger = LoggerFactory.getLogger(Helper.class);
        public static String getLoggedInUserEmail(Authentication authentication) {
            String userEmail = "";
            if(authentication instanceof OAuth2AuthenticationToken){
                var oauth2Token = (OAuth2AuthenticationToken) authentication;
                DefaultOAuth2User loggedInUser = (DefaultOAuth2User) authentication.getPrincipal();
                String authorizedClientRegistrationId = oauth2Token.getAuthorizedClientRegistrationId();
                if(authorizedClientRegistrationId.equalsIgnoreCase("google")){
                    userEmail = loggedInUser.getAttribute("email");
                }
                else if(authorizedClientRegistrationId.equalsIgnoreCase("github")){
                    userEmail = loggedInUser.getAttribute("email") != null ? loggedInUser.getAttribute("email") : loggedInUser.getAttribute("name")+"@gmail.com"; 
                }
            }else{
                userEmail = authentication.getName();
            }
            logger.info("Logged in user's Email ID: {}",userEmail);
        return userEmail;
    }
}
