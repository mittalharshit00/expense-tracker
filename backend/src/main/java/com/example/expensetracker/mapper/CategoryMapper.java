package com.example.expensetracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.expensetracker.dto.request.CategoryRequest;
import com.example.expensetracker.dto.response.CategoryResponse;
import com.example.expensetracker.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    
    @Mapping(target ="id", ignore =true)
    @Mapping(target ="user", ignore =true)
    @Mapping(target ="expenses", ignore =true)
    Category toEntity(CategoryRequest categoryRequest);

    @Mapping(target ="id", ignore =true)
    @Mapping(target ="user", ignore =true)
    @Mapping(target ="expenses", ignore =true)
    void updateEntity(CategoryRequest categoryRequest, @MappingTarget Category category);


    CategoryResponse toResponse(Category category);
}
