package com.example.expensetracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expensetracker.dto.request.ChangePasswordRequest;
import com.example.expensetracker.dto.request.LoginRequest;
import com.example.expensetracker.dto.response.LoginResponse;
import com.example.expensetracker.service.LoginService;
import com.example.expensetracker.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController 
@RequestMapping("/api")
@AllArgsConstructor 
public class AuthController {

    private final LoginService loginService;
    private final UserService userService;
    
    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(
        @Valid @RequestBody LoginRequest request
    ){
        LoginResponse response = loginService.login(request);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/account/password")
    public ResponseEntity<Void> changePassword(
        @Valid @RequestBody ChangePasswordRequest request
    ){
        userService.changePassword(request);

        return ResponseEntity.ok().build();
    }
    
}
