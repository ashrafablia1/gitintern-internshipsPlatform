package com.gitintern.internships.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;

// this class is used to configure Spring Security
@Configuration
@EnableWebSecurity
class SpringSecurity {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests((requests) -> requests
                        .requestMatchers("/registration/**").permitAll()
                        .requestMatchers("/login/**").permitAll()
                        .requestMatchers("/company/**").hasRole("COMPANY")
                        .requestMatchers("/intern/**").hasRole("INTERN")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/", true) // Redirect to root URL after login
                        .successHandler((request, response, authentication) -> {
                            String targetUrl = determineTargetUrl(authentication);
                            response.sendRedirect(targetUrl);
                        })
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll())
                .exceptionHandling().accessDeniedPage("/access-denied");

        return http.build();
    }

    private String determineTargetUrl(Authentication authentication) {
        // Get the authenticated user's authorities (roles)
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // Check the authorities to determine the target URL
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equalsIgnoreCase("ROLE_COMPANY")) {
                return "company/homepage";
            } else if (authority.getAuthority().equalsIgnoreCase("ROLE_INTERN")) {
                return "intern/homepage";
            }
        }

        // Fallback to a default URL if the role is not found
        return "/default/homepage";

    }
}

