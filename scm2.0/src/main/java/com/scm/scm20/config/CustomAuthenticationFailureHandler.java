package com.scm.scm20.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
            throws ServletException, IOException {

        System.out.println("❌ OAuth2 login failed: " + exception.getMessage());
        request.getSession().setAttribute("error.message", exception.getMessage());
        response.sendRedirect("/login?error=oauth2");
    }
}
