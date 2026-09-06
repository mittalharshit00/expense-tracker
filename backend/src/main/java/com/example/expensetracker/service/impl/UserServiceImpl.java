package com.example.expensetracker.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.expensetracker.dto.request.UserRequest;
import com.example.expensetracker.dto.response.UserResponse;
import com.example.expensetracker.entity.User;
import com.example.expensetracker.mapper.UserMapper;
import com.example.expensetracker.repository.UserRepository;
import com.example.expensetracker.service.UserService;

import lombok.AllArgsConstructor;

@Service 
@AllArgsConstructor  
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional 
    @Override
    public UserResponse createUser(UserRequest userRequest){
        User user = userMapper.toEntity(userRequest);
        User savedUser =userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    @Transactional(readOnly = true)
    @Override
    public UserResponse getUserById(Integer userId){
        User user = userRepository.findById(userId)
            .orElseThrow(
                () -> new RuntimeException("User not found"));
        return userMapper.toResponse(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserResponse> getAllUsers(){
        List<User> users = userRepository.findAll();
        return  users.stream()
            .map(userMapper::toResponse)
            .toList();
    }

    @Transactional
    @Override 
    public UserResponse updateUser(Integer userId, UserRequest userRequest){
        User user = userRepository.findById(userId)
            .orElseThrow(
                () -> new RuntimeException("User not found"));
        userMapper.updateEntity(userRequest, user);
        return userMapper.toResponse(user);
        

    }
    
    @Transactional
    public void deleteUser(Integer userId){
        User user = userRepository.findById(userId)
            .orElseThrow(
                () -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}
