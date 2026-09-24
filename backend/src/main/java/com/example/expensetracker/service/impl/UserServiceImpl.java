package com.example.expensetracker.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.expensetracker.dto.request.UserCreateRequest;
import com.example.expensetracker.dto.request.UserUpdateRequest;
import com.example.expensetracker.dto.response.UserResponse;
import com.example.expensetracker.entity.User;
import com.example.expensetracker.exception.ConflictException;
import com.example.expensetracker.exception.ResourceNotFoundException;
import com.example.expensetracker.mapper.UserMapper;
import com.example.expensetracker.repository.UserRepository;
import com.example.expensetracker.service.AuthorizationService;
import com.example.expensetracker.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Service 
@AllArgsConstructor  
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthorizationService authorizationService;

    @Transactional 
    @Override
    public UserResponse createUser(UserCreateRequest userCreateRequest){
        if(userRepository.existsByEmail(userCreateRequest.getEmail())){
            throw new ConflictException("Email is already associated with another user");
        }
        User user = userMapper.toEntity(userCreateRequest);
        User savedUser =userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    @Transactional(readOnly = true)
    @Override
    public UserResponse getUserById(Integer userId){

        authorizationService.validateUserAccess(userId);
        User user = userRepository.findById(userId)
            .orElseThrow(
                () -> new ResourceNotFoundException("User not found"));
        

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
    public UserResponse updateUser(Integer userId, UserUpdateRequest userUpdateRequest){
        
        authorizationService.validateUserAccess(userId);
        User user = userRepository.findById(userId)
            .orElseThrow(
                () -> new ResourceNotFoundException("User not found"));
        

        if(userRepository.existsByEmailAndIdNot(userUpdateRequest.getEmail(),userId)){
            throw new ConflictException("Email is already associated with another user");
        }
        userMapper.updateEntity(userUpdateRequest, user);
        return userMapper.toResponse(user);
        

    }
    
    @Override
    @Transactional
    public void deleteUser(Integer userId){

        authorizationService.validateUserAccess(userId);
        User user = userRepository.findById(userId)
            .orElseThrow(
                () -> new ResourceNotFoundException("User not found"));

        userRepository.delete(user);
    }


}
