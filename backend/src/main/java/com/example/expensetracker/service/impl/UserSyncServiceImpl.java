package com.example.expensetracker.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.expensetracker.dto.response.UserResponse;
import com.example.expensetracker.entity.User;
import com.example.expensetracker.mapper.UserMapper;
import com.example.expensetracker.repository.UserRepository;
import com.example.expensetracker.service.UserSyncService;

import lombok.AllArgsConstructor;

@Service 
@AllArgsConstructor  
public class UserSyncServiceImpl implements UserSyncService {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    private User createUser(Jwt jwt){
        User user = User.builder()
            .keycloakId(jwt.getSubject())
            .name(jwt.getClaimAsString("given_name"))
            .email(jwt.getClaimAsString("email"))
            .build();

        return user;
    }

    @Transactional 
    @Override
    public UserResponse syncUser(){
        
        Authentication authentication = SecurityContextHolder
            .getContext()
            .getAuthentication();
        
        Jwt jwt = (Jwt)authentication.getPrincipal();

        String keycloakId = jwt.getSubject();

        User user = userRepository.findByKeycloakId(keycloakId)
            .orElseGet(() -> createUser(jwt));

        userRepository.save(user);

        return userMapper.toResponse(user);

    }
}
