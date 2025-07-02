package com.scm.scm20.config;

import com.scm.scm20.services.Impl.SecurityUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails userDetails=User
//                .withUsername("admin")
//                .password("admin123")
//                .roles("ADMIN","USER")
//                .build();
//        return new InMemoryUserDetailsManager(userDetails);
//    }
    @Autowired
   private SecurityUserDetailService userDetailService;
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(authorize->{
//            authorize.requestMatchers("/home").permitAll();
             authorize.requestMatchers("/user/**").authenticated();
             authorize.anyRequest().permitAll();
        });
        httpSecurity.formLogin(formLogin->{
        formLogin.loginPage("/login")
                .loginProcessingUrl("/authenticate")
                .defaultSuccessUrl("/user/dashboard")
                .failureUrl("/login?error=true")
                .usernameParameter("email")
                .passwordParameter("password");

        });
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.logout(logoutForm->{
            logoutForm.logoutUrl("/logout");
        });
      return httpSecurity.build();
    }
}
