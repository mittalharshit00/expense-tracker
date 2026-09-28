package com.example.expensetracker.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.expensetracker.dto.request.ExpenseRequest;
import com.example.expensetracker.dto.response.ExpenseResponse;
import com.example.expensetracker.entity.Category;
import com.example.expensetracker.entity.Expense;
import com.example.expensetracker.exception.ResourceNotFoundException;
import com.example.expensetracker.mapper.ExpenseMapper;
import com.example.expensetracker.repository.CategoryRepository;
import com.example.expensetracker.repository.ExpenseRepository;
import com.example.expensetracker.service.ExpenseService;
import com.example.expensetracker.service.AuthorizationService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;

@Service 
@AllArgsConstructor 
public class ExpenseServiceImpl implements ExpenseService{

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final ExpenseMapper expenseMapper;
    private final AuthorizationService authorizationService;
    

    @Override 
    @Transactional 
    public ExpenseResponse createExpense(ExpenseRequest expenseRequest,Integer categoryId){
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(
                () -> new ResourceNotFoundException("Category not found")
            );

        authorizationService.validateCategoryAccess(category);

        Expense expense = expenseMapper.toEntity(expenseRequest);
        expense.setCategory(category);
        Expense savedExpense = expenseRepository.save(expense);

        return expenseMapper.toResponse(savedExpense);
    }

    @Override 
    @Transactional(readOnly = true)
    public Page<ExpenseResponse> getAllExpensesForCategory(Integer categoryId , Pageable pageable){
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(
                () -> new ResourceNotFoundException("Category not found")
            );

        authorizationService.validateCategoryAccess(category);

        Page<Expense> expenses = expenseRepository.findByCategoryId(categoryId,pageable);
        return expenses.map(expenseMapper::toResponse);
    }

    @Override 
    @Transactional(readOnly = true)
    public ExpenseResponse getExpenseById(Integer expenseId){
        Expense expense = expenseRepository.findById(expenseId).
            orElseThrow(
                () -> new ResourceNotFoundException("Expense not found")
            );
        
        authorizationService.validateExpenseAccess(expense);

        return expenseMapper.toResponse(expense);
    }

    @Override 
    @Transactional
    public ExpenseResponse updateExpense(Integer expenseId,ExpenseRequest expenseRequest){
        Expense expense = expenseRepository.findById(expenseId).
            orElseThrow(
                () -> new ResourceNotFoundException("Expense not found")
            );
        
        authorizationService.validateExpenseAccess(expense);

        expenseMapper.updateEntity(expenseRequest,expense);

        return expenseMapper.toResponse(expense);
    }

    @Override 
    @Transactional
    public void deleteExpenseById(Integer expenseId){
        Expense expense = expenseRepository.findById(expenseId).
            orElseThrow(
                () -> new ResourceNotFoundException("Expense not found")
            );
        
        authorizationService.validateExpenseAccess(expense);

        expenseRepository.delete(expense);
    }
}
