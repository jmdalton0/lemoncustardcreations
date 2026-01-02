package com.lemoncustardcreations.catalog.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    @Value("${app.security.username}")
    private String username;

    @Value("${app.security.password}")
    private String password;

    private final PasswordEncoder passwordEncoder;

    SecurityUserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!username.equals(this.username)) {
            throw new UsernameNotFoundException(username);
        }

        return User
            .withUsername(this.username)
            .password(passwordEncoder.encode(this.password))
            .build();
    }
    
}
