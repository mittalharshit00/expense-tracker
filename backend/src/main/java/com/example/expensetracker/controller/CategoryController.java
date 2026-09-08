package com.example.expensetracker.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.expensetracker.dto.request.CategoryRequest;
import com.example.expensetracker.dto.response.CategoryResponse;
import com.example.expensetracker.service.CategoryService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor 
@RequestMapping("/api")
@RestController 
public class CategoryController {
    
    private final CategoryService categoryService;

    @PostMapping("/users/{userId}/categories")
    public ResponseEntity<CategoryResponse> createCategory(
        @Valid @RequestBody CategoryRequest request,
        @PathVariable Integer userId
    ){
        CategoryResponse response = categoryService.createCategory(request, userId);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }

    @GetMapping("/users/{userId}/categories")
    public ResponseEntity<List<CategoryResponse>> getAllCategoriesForUser(
        @PathVariable Integer userId
    ){
        List<CategoryResponse> response = categoryService.getAllCategoriesForUser(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<CategoryResponse> getCategoryById(
        @PathVariable Integer categoryId
    ){
        CategoryResponse response = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/categories/{categoryId}")
    public ResponseEntity<CategoryResponse> updateCategory(
        @PathVariable Integer categoryId,
        @Valid @RequestBody CategoryRequest request
    ){
        CategoryResponse response = categoryService.updateCategory(categoryId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<Void> deleteCategory(
        @PathVariable Integer categoryId
    ){
        categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.noContent().build();
    }
}
