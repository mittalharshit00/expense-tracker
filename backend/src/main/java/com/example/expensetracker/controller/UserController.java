package com.example.expensetracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expensetracker.dto.request.UserCreateRequest;
import com.example.expensetracker.dto.request.UserUpdateRequest;
import com.example.expensetracker.service.UserService;
import com.example.expensetracker.dto.response.UserResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
 
@AllArgsConstructor 
@RestController 
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping 
    public ResponseEntity<UserResponse> createUser(
        @Valid @RequestBody UserCreateRequest userCreateRequest){
            UserResponse response = userService.createUser(userCreateRequest);

            return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);

    }

    @GetMapping("{userId}")
    public ResponseEntity<UserResponse> getUserById(
        @PathVariable Integer userId
    ){
        UserResponse response = userService.getUserById(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping 
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserResponse> response = userService.getAllUsers();
        return ResponseEntity.ok(response);
    }

    @PutMapping("{userId}")
    public ResponseEntity<UserResponse> updateUser(
        @PathVariable Integer userId,
        @Valid @RequestBody UserUpdateRequest request
    ){
        UserResponse response = userService.updateUser(userId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<Void> deleteUser(
        @PathVariable Integer userId
    ){
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
    
}
