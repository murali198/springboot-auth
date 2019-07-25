package com.murali.jwt.core;

import com.murali.jwt.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class JWTAuthManager {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtTokenUtil tokenUtil;

    @Autowired
    private UserDetailsService service;

    public String login(String user, String password) {
        Objects.requireNonNull(user, "User Empty!!");
        Objects.requireNonNull(password, "Password Empty!!");
        authManager.authenticate(new UsernamePasswordAuthenticationToken(user, password));
        UserDetails userDetails = service.loadUserByUsername(user);
        return tokenUtil.generateToken(userDetails.getUsername());
    }
}
