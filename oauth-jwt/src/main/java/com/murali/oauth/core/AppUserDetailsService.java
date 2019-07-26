package com.murali.oauth.core;

import com.murali.oauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        return userRepository.findByUsername(userName)
                .map(user -> {

                    List<GrantedAuthority> authorities = new ArrayList<>();
                    user.getRoles().forEach(role -> {
                        authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
                    });

                    UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
                    return userDetails;
                })
                .orElseThrow(() -> new UsernameNotFoundException(String.format("The username %s doesn't exist", userName)));
    }
}
