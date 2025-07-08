//package com.scm.scm20.helper;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
//
//import java.security.Principal;
//
//public class Helper {
//    public static String getEmailOfLoggedInUser(Authentication authentication){
//        Principal principal=(Principal) authentication.getPrincipal();
//        if(principal instanceof OAuth2AuthenticatedPrincipal){
//           var oAuth2AuthenticationToken= (OAuth2AuthenticationToken)authentication;
//           var clientId=oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
//           if(clientId.equalsIgnoreCase("google")){
//               System.out.println("Google");
//           }
//            else if(clientId.equalsIgnoreCase("github")){
//               System.out.println("github");
//            }
//        }
//        else {
//            System.out.println("Getting from self");
//
//            return principal.getName();
//
//        }
//        return "";
//    }
//}

package com.scm.scm20.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {

    public static String getEmailOfLoggedInUser(Authentication authentication) {
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauth2Token = (OAuth2AuthenticationToken) authentication;
            String clientId = oauth2Token.getAuthorizedClientRegistrationId();

            OAuth2User principal = oauth2Token.getPrincipal();

            if (clientId.equalsIgnoreCase("google") && principal instanceof OidcUser oidcUser) {
                System.out.println("Google");
                return oidcUser.getEmail(); // Safe for Google
            } else if (clientId.equalsIgnoreCase("github")) {
                System.out.println("GitHub");
                // GitHub doesn't always provide email reliably; fallback:
                return principal.getAttribute("email");
            }
        }

        // For non-OAuth2 users (form login, basic auth, etc.)
        System.out.println("Getting from self");
        return authentication.getName(); // This is safe and correct
    }
}

