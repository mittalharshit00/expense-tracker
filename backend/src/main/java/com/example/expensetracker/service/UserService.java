package com.example.expensetracker.service;

import java.util.List;

import com.example.expensetracker.dto.request.UserCreateRequest;
import com.example.expensetracker.dto.request.UserUpdateRequest;
import com.example.expensetracker.dto.response.UserResponse;

public interface UserService {
    
    UserResponse createUser(UserCreateRequest userCreateRequest);

    UserResponse getUserById(Integer userId);

    List<UserResponse> getAllUsers();

    UserResponse updateUser(Integer userId, UserUpdateRequest userUpdateRequest);

    void deleteUser(Integer userId);


}
