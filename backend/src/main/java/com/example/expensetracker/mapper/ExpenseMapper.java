package com.example.expensetracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.expensetracker.dto.request.ExpenseRequest;
import com.example.expensetracker.dto.response.ExpenseResponse;
import com.example.expensetracker.entity.Expense;

@Mapper(componentModel ="spring")
public interface ExpenseMapper {
    
    @Mapping(target ="id", ignore =true)
    @Mapping(target ="category", ignore =true)
    Expense toEntity(ExpenseRequest expenseRequest);

    @Mapping(target ="id", ignore =true)
    @Mapping(target ="category", ignore =true)
    void updateEntity(ExpenseRequest expenseRequest , @MappingTarget Expense expense);

    @Mapping(target ="categoryId", source = "category.id")
    ExpenseResponse toResponse(Expense expense);
}
