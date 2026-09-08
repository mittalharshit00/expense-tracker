package com.example.expensetracker.service.impl;

import java.util.List;

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

import lombok.AllArgsConstructor;

@Service 
@AllArgsConstructor 
public class ExpenseServiceImpl implements ExpenseService{

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    private final ExpenseMapper expenseMapper;
    

    @Override 
    @Transactional 
    public ExpenseResponse createExpense(ExpenseRequest expenseRequest,Integer categoryId){
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(
                () -> new ResourceNotFoundException("Category not found")
            );

        Expense expense = expenseMapper.toEntity(expenseRequest);
        expense.setCategory(category);
        Expense savedExpense = expenseRepository.save(expense);

        return expenseMapper.toResponse(savedExpense);
    }

    @Override 
    @Transactional(readOnly = true)
    public List<ExpenseResponse> getAllExpensesForCategory(Integer categoryId){
        List<Expense> expenses = expenseRepository.getAllExpensesForCategory(categoryId);
        return expenses.stream()
            .map(expenseMapper::toResponse)
            .toList();
    }

    @Override 
    @Transactional(readOnly = true)
    public ExpenseResponse getExpenseById(Integer expenseId){
        Expense expense = expenseRepository.findById(expenseId).
            orElseThrow(
                () -> new ResourceNotFoundException("Expense not found")
            );
        
        return expenseMapper.toResponse(expense);
    }

    @Override 
    @Transactional
    public ExpenseResponse updateExpense(Integer expenseId,ExpenseRequest expenseRequest){
        Expense expense = expenseRepository.findById(expenseId).
            orElseThrow(
                () -> new ResourceNotFoundException("Expense not found")
            );
        
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
        expenseRepository.delete(expense);
    }
}
