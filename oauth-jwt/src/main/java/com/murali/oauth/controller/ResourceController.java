package com.murali.oauth.controller;

import com.murali.oauth.domain.RandomCity;
import com.murali.oauth.domain.User;
import com.murali.oauth.repository.RandomCityRepository;
import com.murali.oauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ResourceController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RandomCityRepository cityRepository;

    @RequestMapping(value ="/cities")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public List<RandomCity> getUser(){
        return cityRepository.findAll();
    }

    @RequestMapping(value ="/users", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public List<User> getUsers(){
        return userRepository.findAll();
    }
}
