package com.example.expensetracker.service.impl;

import com.example.expensetracker.dto.request.CategoryRequest;
import com.example.expensetracker.dto.response.CategoryResponse;
import com.example.expensetracker.entity.Category;
import com.example.expensetracker.entity.User;
import com.example.expensetracker.exception.ConflictException;
import com.example.expensetracker.exception.ResourceNotFoundException;
import com.example.expensetracker.mapper.CategoryMapper;
import com.example.expensetracker.repository.CategoryRepository;
import com.example.expensetracker.repository.UserRepository;
import com.example.expensetracker.service.CategoryService;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional 
    public CategoryResponse createCategory(CategoryRequest categoryRequest,Integer userId){
        User user = userRepository.findById(userId)
            .orElseThrow(
                () -> new ResourceNotFoundException("User not found"));
        
        if(categoryRepository.existsByNameAndUserId(categoryRequest.getName(), userId)){
            throw new ConflictException("Category already exists for this user");
        }
        Category category = categoryMapper.toEntity(categoryRequest);
        category.setUser(user);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(savedCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> getAllCategoriesForUser(Integer userId){
        List<Category> categories = categoryRepository.findCategoriesByUserId(userId);
        return categories.stream()
            .map(categoryMapper::toResponse)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse getCategoryById(Integer categoryId){
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(
                () -> new ResourceNotFoundException("category not found")
            );

        return categoryMapper.toResponse(category);
    }

    @Override
    @Transactional
    public CategoryResponse updateCategory(Integer categoryId,CategoryRequest categoryRequest){
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(
                () -> new ResourceNotFoundException("category not found")
            );
        
        Integer userId = category.getUser().getId();

        if(categoryRepository.existsByNameAndUserIdAndIdNot(categoryRequest.getName(), userId,categoryId)){
            throw new ConflictException("Category already exists for this user");
        }
        
        categoryMapper.updateEntity(categoryRequest,category);
        return categoryMapper.toResponse(category);

    }

    @Override
    @Transactional
    public void deleteCategoryById(Integer categoryId){
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(
                () -> new ResourceNotFoundException("category not found")
            );
    
        categoryRepository.delete(category);
    }
}
