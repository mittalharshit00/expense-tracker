package com.example.expensetracker.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.expensetracker.dto.request.LoginRequest;
import com.example.expensetracker.dto.response.LoginResponse;
import com.example.expensetracker.security.JwtService;
import com.example.expensetracker.service.LoginService;

import lombok.AllArgsConstructor;

@Service 
@AllArgsConstructor 
public class LoginServiceImpl implements LoginService{
    
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request){
        Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(authenticationRequest);
        String username = authentication.getName();
        String token = jwtService.generateToken(username);

        return new LoginResponse(token);

    }
}
