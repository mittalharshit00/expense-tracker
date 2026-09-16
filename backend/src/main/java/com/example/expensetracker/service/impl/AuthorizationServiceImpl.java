package com.example.expensetracker.service.impl;

import org.springframework.stereotype.Service;

import com.example.expensetracker.entity.Category;
import com.example.expensetracker.entity.Expense;
import com.example.expensetracker.entity.User;
import com.example.expensetracker.enums.Role;
import com.example.expensetracker.exception.AccessDeniedException;
import com.example.expensetracker.service.CurrentUserService;
import com.example.expensetracker.service.AuthorizationService;

import lombok.AllArgsConstructor;

@Service 
@AllArgsConstructor 
public class AuthorizationServiceImpl implements AuthorizationService{
    
    private final CurrentUserService currentUserService;

    @Override 
    public void validateUserAccess(Integer userId){
        User authenticatedUser =currentUserService.getCurrentUser();
        if(!authenticatedUser.getId().equals(userId) && authenticatedUser.getRole() != Role.ADMIN){
            throw new AccessDeniedException("You are not allowed to access this user");
        }
    }

    @Override 
    public void validateCategoryAccess(Category category){
        User authenticatedUser =currentUserService.getCurrentUser();
        if(!authenticatedUser.getId().equals(category.getUser().getId()) && authenticatedUser.getRole() != Role.ADMIN){
            throw new AccessDeniedException("You are not allowed to access this category");
        }
    }

    @Override 
    public void validateExpenseAccess(Expense expense){
        User authenticatedUser =currentUserService.getCurrentUser();
        if(!authenticatedUser.getId().equals(expense.getCategory().getUser().getId()) && authenticatedUser.getRole() != Role.ADMIN){
            throw new AccessDeniedException("You are not allowed to access this expense");
        }
    }
}
