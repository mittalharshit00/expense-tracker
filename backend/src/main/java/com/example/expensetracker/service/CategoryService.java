package com.example.expensetracker.service;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.example.expensetracker.dto.request.CategoryRequest;
import com.example.expensetracker.dto.response.CategoryResponse;

public interface CategoryService {
    
    CategoryResponse createCategory(CategoryRequest categoryRequest,Integer userId);

    Page<CategoryResponse> getAllCategoriesForUser(Integer userId,Pageable pageable);

    CategoryResponse getCategoryById(Integer categoryId);

    CategoryResponse updateCategory(Integer categoryId,CategoryRequest categoryRequest);

    void deleteCategoryById(Integer categoryId);
}
