package com.example.expensetracker.repository;

import com.example.expensetracker.entity.Category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value="""
        SELECT *
        FROM categories
        WHERE user_id = :userId
            """, nativeQuery = true)
    List<Category> findCategoriesByUserId(@Param("userId") Integer userId);

    boolean existsByNameAndUserId(String name,Integer userId);

    boolean existsByNameAndUserIdAndIdNot(String name,Integer userId ,Integer categoryId);
}
