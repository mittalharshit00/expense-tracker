package com.example.expensetracker.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.expensetracker.entity.User;
import com.example.expensetracker.exception.BadRequestException;
import com.example.expensetracker.security.CustomUserDetails;
import com.example.expensetracker.service.CurrentUserService;

@Service 
public class CurrentUserServiceImpl implements CurrentUserService{
    
    @Override 
    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null ||
            !authentication.isAuthenticated() ||
            !(authentication.getPrincipal() instanceof CustomUserDetails)
        ){
            throw new BadRequestException("No authenticated user found");
        }

        CustomUserDetails userDetails = (CustomUserDetails)authentication.getPrincipal();

        return userDetails.getUser();
    }
}
