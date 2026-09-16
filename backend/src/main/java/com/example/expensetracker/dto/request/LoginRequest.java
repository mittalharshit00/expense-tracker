package com.example.expensetracker.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor 
public class LoginRequest {
    
    @Email(message = "Must be a valid email")
    @NotBlank(message = "email must be present")
    private String email;

    @NotBlank(message ="Password must be present")
    private String password;
}
