package com.example.expensetracker.repository;

import com.example.expensetracker.entity.Expense;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    @Query(value = """
            SELECT * FROM expenses WHERE category_id = :categoryId;
            """,nativeQuery = true)
    List<Expense> getAllExpensesForCategory(@Param("categoryId") Integer categoryId); 

}
