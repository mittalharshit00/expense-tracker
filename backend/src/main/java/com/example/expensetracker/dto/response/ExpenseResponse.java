package com.example.expensetracker.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseResponse {
    
    private Integer id;

    private String description;

    private BigDecimal amount;

    private LocalDate expenseDate;

    private Integer categoryId;
}
