package com.example.expensetracker.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
public class ChangePasswordRequest {
    
    @NotBlank 
    private String CurrPassword;

    @NotBlank 
    private String NewPassword;
}
