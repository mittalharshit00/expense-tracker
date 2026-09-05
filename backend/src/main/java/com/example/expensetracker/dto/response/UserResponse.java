package com.example.expensetracker.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    
    private Integer id;

    private String name;

    private String email;
}
