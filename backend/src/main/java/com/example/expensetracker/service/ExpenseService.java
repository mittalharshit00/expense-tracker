package com.example.expensetracker.service;

import java.util.List;

import com.example.expensetracker.dto.request.ExpenseRequest;
import com.example.expensetracker.dto.response.ExpenseResponse;

public interface ExpenseService {
    
    ExpenseResponse createExpense(ExpenseRequest expenseRequest,Integer categoryId);

    List<ExpenseResponse> getAllExpensesForCategory(Integer categoryId);

    ExpenseResponse getExpenseById(Integer expenseId);

    ExpenseResponse updateExpense(Integer expenseId,ExpenseRequest expenseRequest);  

    void deleteExpenseById(Integer expenseId);
}
