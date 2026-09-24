package com.example.expensetracker.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.expensetracker.entity.Category;
import com.example.expensetracker.entity.Expense;
import com.example.expensetracker.entity.User;
import com.example.expensetracker.exception.AccessDeniedException;
import com.example.expensetracker.service.CurrentUserService;
import com.example.expensetracker.service.AuthorizationService;

import lombok.AllArgsConstructor;

@Service 
@AllArgsConstructor 
public class AuthorizationServiceImpl implements AuthorizationService{
    
    private final CurrentUserService currentUserService;

    private boolean isAdmin() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        return authentication.getAuthorities()
                .contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override 
    public void validateUserAccess(Integer userId){
        User authenticatedUser =currentUserService.getCurrentUser();
        if(!authenticatedUser.getId().equals(userId) && !isAdmin()){
            throw new AccessDeniedException(
                "You are not allowed to access this user");
        }
    }

    @Override 
    public void validateCategoryAccess(Category category){
        User authenticatedUser =currentUserService.getCurrentUser();
        if(!authenticatedUser.getId().equals(category.getUser().getId()) 
            && !isAdmin()){
            throw new AccessDeniedException(
                "You are not allowed to access this category");
        }
    }

    @Override 
    public void validateExpenseAccess(Expense expense){
        User authenticatedUser =currentUserService.getCurrentUser();
        if(!authenticatedUser.getId().equals(expense.getCategory().getUser().getId()) 
            && !isAdmin()){
            throw new AccessDeniedException(
                "You are not allowed to access this expense");
        }
    }
}
