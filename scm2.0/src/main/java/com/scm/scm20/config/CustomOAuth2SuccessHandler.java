package com.scm.scm20.config;

import com.scm.scm20.entities.Providers;
import com.scm.scm20.entities.User;
import com.scm.scm20.helper.AppConstants;
import com.scm.scm20.repositories.UserRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserRepo userRepo;
    private static final Logger logger= LoggerFactory.getLogger(CustomOAuth2SuccessHandler.class);
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public CustomOAuth2SuccessHandler() {
        System.out.println("✅ CustomOAuth2SuccessHandler bean created");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        System.out.println("✅ OAuth2 login successful for user: " );
        var oAuth2AuthenticationToken=(OAuth2AuthenticationToken)authentication;
        String authorizedClientRegistrationId=oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
        logger.info(authorizedClientRegistrationId);

        var oauthUser=(DefaultOAuth2User)authentication.getPrincipal();
        oauthUser.getAttributes().forEach((key,value)->{
            logger.info("{}=>{}",key,value);
        });

        User user=new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        user.setEmailVerified(true);
        user.setEnabled(true);
        user.setPassword("dummy");
         if(authorizedClientRegistrationId.equalsIgnoreCase("google")){
user.setEmail(oauthUser.getAttribute("email").toString());
user.setProfilePic(oauthUser.getAttribute("picture").toString());
user.setName(oauthUser.getAttribute("name").toString());
user.setProviderUserId(oauthUser.getName());
user.setProvider(Providers.GOOGLE);
user.setAbout("This account is logged in using google");
         }
        else if(authorizedClientRegistrationId.equalsIgnoreCase("github")){
        String email=oauthUser.getAttribute("email")!=null?oauthUser.getAttribute("email").toString():oauthUser.getAttribute("login").toString()+"@gmail.com";
        String picture=oauthUser.getAttribute("avatar_url").toString();
        String name=oauthUser.getAttribute("login").toString();
        String providerId=oauthUser.getName();
        user.setEmail(email);
        user.setProfilePic(picture);
        user.setName(name);
        user.setProviderUserId(providerId);
             user.setProvider(Providers.GITHUB);
             user.setAbout("This account is logged in using github");

        }
        else {
            logger.info("Unknown provider");
         }
//        DefaultOAuth2User user=(DefaultOAuth2User)authentication.getPrincipal();
////        logger.info(user.getName());
////        user.getAttributes().forEach((key,value)->{
////            logger.info("{}=>{}",key,value);
////        });
////        logger.info(user.getAuthorities().toString());
//        String email=user.getAttribute("email").toString();
//        String name=user.getAttribute("name").toString();
//        String picture=user.getAttribute("picture").toString();
//
//        User user1=new User();
//        user1.setEmail(email);
//        user1.setName(name);
//        user1.setProfilePic(picture);
//        user1.setPassword("password");
//        user1.setUserId(UUID.randomUUID().toString());
//        user1.setProvider(Providers.GOOGLE);
//        user1.setEnabled(true);
//        user1.setEmailVerified(true);
//        user1.setProviderUserId(user.getName());
//        user1.setRoleList(List.of(AppConstants.ROLE_USER));
//        user1.setAbout("This account is created by google");
//        User user2=userRepo.findByEmail(email).orElse(null);
//        if(user2==null){
//            userRepo.save(user);
//            logger.info("User saved");
//        }
//        */
        User user2=userRepo.findByEmail(user.getEmail()).orElse(null);
        if(user2==null){
            userRepo.save(user);
            logger.info("User saved");
        }
        redirectStrategy.sendRedirect(request, response, "/user/profile");
    }
}
