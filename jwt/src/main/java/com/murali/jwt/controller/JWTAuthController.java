package com.murali.jwt.controller;

import com.murali.jwt.core.JWTAuthManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JWTAuthController {

    @Autowired
    private JWTAuthManager manager;

    @GetMapping("/login")
    public String login(@RequestHeader("x-userName") String userName, @RequestHeader("x-password") String password) {
        return manager.login(userName, password);
    }
}
