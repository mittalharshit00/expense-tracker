package com.example.expensetracker.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expensetracker.dto.request.ExpenseRequest;
import com.example.expensetracker.dto.response.ExpenseResponse;
import com.example.expensetracker.service.ExpenseService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor 
public class ExpenseController {
    
    private final ExpenseService expenseService;

    @PostMapping("/categories/{categoryId}/expenses")
    public ResponseEntity<ExpenseResponse> createExpense(
        @Valid @RequestBody ExpenseRequest request,
        @PathVariable Integer categoryId
    ){
        ExpenseResponse response = expenseService.createExpense(request, categoryId);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }

    @GetMapping("/categories/{categoryId}/expenses")
    public ResponseEntity<List<ExpenseResponse>> getAllExpensesForCategory(
        @PathVariable Integer categoryId
    ){
        List<ExpenseResponse> response = expenseService.getAllExpensesForCategory(categoryId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/expenses/{expenseId}")
    public ResponseEntity<ExpenseResponse> getExpenseById(
        @PathVariable Integer expenseId
    ){
        ExpenseResponse response = expenseService.getExpenseById(expenseId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/expenses/{expenseId}")
    public ResponseEntity<ExpenseResponse> updateExpense(
        @PathVariable Integer expenseId,
        @Valid @RequestBody ExpenseRequest request
    ){
        ExpenseResponse response = expenseService.updateExpense(expenseId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/expenses/{expenseId}")
    public ResponseEntity<Void> deleteExpenseById(
        @PathVariable Integer expenseId
    ){
        expenseService.deleteExpenseById(expenseId);
        return ResponseEntity.noContent().build();
    }
}
