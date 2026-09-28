package com.example.expensetracker.repository;

import com.example.expensetracker.entity.Expense;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    Page<Expense> findByCategoryId(Integer categoryId, Pageable pageable); 

}
