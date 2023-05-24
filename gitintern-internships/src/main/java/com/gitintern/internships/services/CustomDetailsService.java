package com.gitintern.internships.services;


import com.gitintern.internships.models.Role;
import com.gitintern.internships.models.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CustomDetailsService implements UserDetailsService {


    private final UserService userService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findUserByEmail(email);

        if (user != null && user.getEnabled()) {

            List<GrantedAuthority> authorities = new ArrayList<>();

            // Add roles to the authorities list based on the user's role value
            Role userRole = user.getRole();
            if (userRole.name().equals("COMPANY")) {
                authorities.add(new SimpleGrantedAuthority("COMPANY"));
            } else if (userRole.name().equals("INTERN")) {
                authorities.add(new SimpleGrantedAuthority("INTERN"));
            }


            return org.springframework.security.core.userdetails.User.builder()

                    .username(user.getEmail())
                    .password(user.getPassword())
                    .roles(user.getRole().name())
                    .build();

        } else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }


}

