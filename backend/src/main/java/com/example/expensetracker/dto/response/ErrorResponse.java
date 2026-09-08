package com.example.expensetracker.dto.response;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.*;

@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
public class ErrorResponse {
    
    private LocalDateTime timestamp;

    private int status;
    
    private String error;

    private String message;

    private String path;

    private Map<String ,String> validationErrors;
}
