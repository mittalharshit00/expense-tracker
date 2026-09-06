package com.example.expensetracker.service;

import java.util.List;

import com.example.expensetracker.dto.request.CategoryRequest;
import com.example.expensetracker.dto.response.CategoryResponse;

public interface CategoryService {
    
    CategoryResponse createCategory(CategoryRequest categoryRequest,Integer userId);

    List<CategoryResponse> getAllCategoriesForUser(Integer userId);

    CategoryResponse getCategoryById(Integer categoryId);

    CategoryResponse updateCategory(Integer categoryId,CategoryRequest categoryRequest);

    void deleteCategoryById(Integer categoryId);
}
