package com.example.expensetracker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter 
@Setter 
public class LoginResponse {
    
    private String accessToken;
}
