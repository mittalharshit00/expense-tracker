package com.example.expensetracker.service;

import com.example.expensetracker.dto.request.ExpenseRequest;
import com.example.expensetracker.dto.response.ExpenseResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface ExpenseService {
    
    ExpenseResponse createExpense(ExpenseRequest expenseRequest,Integer categoryId);

    Page<ExpenseResponse> getAllExpensesForCategory(Integer categoryId, Pageable pageable);

    ExpenseResponse getExpenseById(Integer expenseId);

    ExpenseResponse updateExpense(Integer expenseId,ExpenseRequest expenseRequest);  

    void deleteExpenseById(Integer expenseId);
}
