package com.example.expensetracker.service;

import com.example.expensetracker.entity.Category;
import com.example.expensetracker.entity.Expense;

public interface AuthorizationService {
    void validateUserAccess(Integer userId);

    void validateCategoryAccess(Category category);

    void validateExpenseAccess(Expense expense);
}
