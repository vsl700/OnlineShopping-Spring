package com.vsl700.onlineshopping.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vsl700.onlineshopping.data.UserRepository;
import com.vsl700.onlineshopping.data.models.User;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AuthenticationManager authManager;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepo;


    @Override
    public void login(String username, String password) {
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Override
    public void register(String firstName, String lastName, String username, String email, String password) {
        if(userRepo.existsByEmail(email)){
            throw new RuntimeException("User with such email already exists!");
        }

        if(userRepo.existsByUsername(username)){
            throw new RuntimeException("User with such username already exists!");
        }

        User user = new User(firstName, lastName, username, email, passwordEncoder.encode(password));
        userRepo.save(user);
    }

    @Override
    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
    
}
