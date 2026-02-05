package com.busbooking.security;

import com.busbooking.entity.User;
import com.busbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        // 1. Find user by email from database
        User user = userRepository.findByEmail(email);

        // 2. If user not found → error
        if (user == null) {
            throw new UsernameNotFoundException(
                    "User not found with email: " + email);
        }

        // 3. Return user details to Spring Security
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())   // login username
                .password(user.getPassword())    // encrypted password
                .roles(user.getRole())           // ADMIN / PASSENGER
                .build();
    }
}
