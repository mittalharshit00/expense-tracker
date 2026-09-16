package com.example.expensetracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expensetracker.dto.request.LoginRequest;
import com.example.expensetracker.dto.response.LoginResponse;
import com.example.expensetracker.service.LoginService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController 
@RequestMapping("/api/auth")
@AllArgsConstructor 
public class AuthController {

    private final LoginService loginService;
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
        @Valid @RequestBody LoginRequest request
    ){
        LoginResponse response = loginService.login(request);

        return ResponseEntity.ok(response);
    }
}
