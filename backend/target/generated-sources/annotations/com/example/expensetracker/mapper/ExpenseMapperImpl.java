package com.example.expensetracker.mapper;

import com.example.expensetracker.dto.request.ExpenseRequest;
import com.example.expensetracker.dto.response.ExpenseResponse;
import com.example.expensetracker.entity.Category;
import com.example.expensetracker.entity.Expense;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-24T21:00:17+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.11-ea (Debian)"
)
@Component
public class ExpenseMapperImpl implements ExpenseMapper {

    @Override
    public Expense toEntity(ExpenseRequest expenseRequest) {
        if ( expenseRequest == null ) {
            return null;
        }

        Expense expense = new Expense();

        expense.setAmount( expenseRequest.getAmount() );
        expense.setDescription( expenseRequest.getDescription() );
        expense.setExpenseDate( expenseRequest.getExpenseDate() );

        return expense;
    }

    @Override
    public void updateEntity(ExpenseRequest expenseRequest, Expense expense) {
        if ( expenseRequest == null ) {
            return;
        }

        expense.setAmount( expenseRequest.getAmount() );
        expense.setDescription( expenseRequest.getDescription() );
        expense.setExpenseDate( expenseRequest.getExpenseDate() );
    }

    @Override
    public ExpenseResponse toResponse(Expense expense) {
        if ( expense == null ) {
            return null;
        }

        ExpenseResponse expenseResponse = new ExpenseResponse();

        expenseResponse.setCategoryId( expenseCategoryId( expense ) );
        expenseResponse.setId( expense.getId() );
        expenseResponse.setDescription( expense.getDescription() );
        expenseResponse.setAmount( expense.getAmount() );
        expenseResponse.setExpenseDate( expense.getExpenseDate() );

        return expenseResponse;
    }

    private Integer expenseCategoryId(Expense expense) {
        Category category = expense.getCategory();
        if ( category == null ) {
            return null;
        }
        return category.getId();
    }
}
