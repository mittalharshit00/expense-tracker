package com.example.expensetracker.repository;

import com.example.expensetracker.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
    Page<Category> findCategoriesByUserId(Integer userId,Pageable pageable);

    boolean existsByNameAndUserId(String name,Integer userId);

    boolean existsByNameAndUserIdAndIdNot(String name,Integer userId ,Integer categoryId);
}
