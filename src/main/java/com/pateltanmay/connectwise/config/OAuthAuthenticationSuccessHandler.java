package com.pateltanmay.connectwise.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.pateltanmay.connectwise.entities.Providers;
import com.pateltanmay.connectwise.entities.User;
import com.pateltanmay.connectwise.helpers.AppConstants;
import com.pateltanmay.connectwise.repositories.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);
    @Autowired
    UserRepo userRepo;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
                logger.info("OAuthAuthenticationSuccessHandler: Recevived the Successful Login");
                var oauth2Token = (OAuth2AuthenticationToken) authentication;
                String authorizedClientRegistrationId = oauth2Token.getAuthorizedClientRegistrationId();
                DefaultOAuth2User oauthUser = (DefaultOAuth2User) authentication.getPrincipal();

                oauthUser.getAttributes().forEach((key,value) -> {
                    logger.info("{} -> {}",key,value);
                });

                User newUser = new User();
                newUser.setPassword("authorized-login-no-password");
                newUser.setId(UUID.randomUUID().toString());
                newUser.setRoleList(List.of(AppConstants.ROLE_USER ));
                newUser.setAbout("Please describe youself...");
                newUser.setEmailVerified(true);
                newUser.setEnabled(true);

                if(authorizedClientRegistrationId.equalsIgnoreCase("google")){
                    if(oauthUser.getAttribute("email") == null){
                        new DefaultRedirectStrategy().sendRedirect(request, response, "/login?error=Your email is private, please make it public to use Google Login");
                        return;
                    }

                    newUser.setEmail(oauthUser.getAttribute("email"));
                    newUser.setProfilePic(oauthUser.getAttribute("picture"));
                    newUser.setName(oauthUser.getAttribute("name"));
                    newUser.setProvider(Providers.GOOGLE);
                    newUser.setProviderId(oauthUser.getName());

                    User userExists = userRepo.findByEmail(newUser.getEmail()).orElse(null);
                    if(userExists == null) userRepo.save(newUser);
                    
                }else if (authorizedClientRegistrationId.equalsIgnoreCase("github")){
                    if(oauthUser.getAttribute("email") == null){
                    new DefaultRedirectStrategy().sendRedirect(request, response, "/login?error=Your email is private, please make it public to use GitHub Login (via GitHub Account Settings)");
                    return;
                    }
                    newUser.setEmail(oauthUser.getAttribute("email"));
                    newUser.setProfilePic(oauthUser.getAttribute("avatar_url"));
                    newUser.setName(oauthUser.getAttribute("login"));
                    newUser.setProvider(Providers.GITHUB);
                    newUser.setProviderId(oauthUser.getName());

                    User userExists = userRepo.findByEmail(newUser.getEmail()).orElse(null);
                    if(userExists == null) userRepo.save(newUser);

                }else logger.info("OAuthAuthenticationSuccessHandler: Unknown Authorizor");

                new DefaultRedirectStrategy().sendRedirect(request, response, "/user/dash");
    }

    

}
