package com.example.expensetracker.service;

import java.util.List;

import com.example.expensetracker.dto.request.UserRequest;
import com.example.expensetracker.dto.response.UserResponse;

public interface UserService {
    
    public UserResponse createUser(UserRequest userRequest);

    public UserResponse getUserById(Integer userId);

    public List<UserResponse> getAllUsers();

    public UserResponse updateUser(Integer userId, UserRequest userRequest);

    public void deleteUser(Integer userId);


}
