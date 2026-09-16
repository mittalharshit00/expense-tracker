package com.example.expensetracker.service;

import com.example.expensetracker.dto.request.LoginRequest;
import com.example.expensetracker.dto.response.LoginResponse;

public interface LoginService {
    
    LoginResponse login(LoginRequest request);
}
