package com.vsl700.onlineshopping.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vsl700.onlineshopping.data.UserRepository;
import com.vsl700.onlineshopping.data.models.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new RuntimeException("User with the username %s not found!".formatted(username)));

        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), List.of(new SimpleGrantedAuthority("user")));
    }
    
}
