package com.murali.jwt.core;


import com.murali.jwt.repo.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Primary
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        return repository.findByUser(user)
                .map( usr -> {
                    return new User(usr.getUser(), usr.getPassword(), new ArrayList<>());
                })
        .orElseThrow(() -> new UsernameNotFoundException("Invalid User!!"));
    }
}
