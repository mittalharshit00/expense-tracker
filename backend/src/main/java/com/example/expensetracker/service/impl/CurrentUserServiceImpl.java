package com.example.expensetracker.service.impl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.example.expensetracker.entity.User;
import com.example.expensetracker.exception.BadRequestException;
import com.example.expensetracker.service.CurrentUserService;
import com.example.expensetracker.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service 
@AllArgsConstructor 
public class CurrentUserServiceImpl implements CurrentUserService{
    
    private final UserRepository userRepository;

    @Override 
    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Jwt jwt = (Jwt)authentication.getPrincipal();
        String keycloakId = jwt.getSubject();

        User user = userRepository.findByKeycloakId(keycloakId)
                .orElseThrow(
                    () -> new BadRequestException("User doesn't exist")
                );
        
        return user;
    }
}
