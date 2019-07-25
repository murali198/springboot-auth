package com.murali.jwt.controller;

import com.murali.jwt.entity.AppUser;
import com.murali.jwt.repo.AppUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class TestController {

    @Autowired
    private AppUserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/api/user")
    public List<AppUser> getUser() {
        log.debug("find all user");
        return repository.findAll();
    }

    @GetMapping("/api/user/{id}")
    public AppUser getUserById(@PathVariable("id") Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!!"));
    }

    @PostMapping("/api/user")
    public AppUser save(@RequestBody AppUser user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }
}
