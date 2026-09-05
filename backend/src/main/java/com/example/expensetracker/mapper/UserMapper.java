package com.example.expensetracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.expensetracker.dto.request.UserRequest;
import com.example.expensetracker.dto.response.UserResponse;
import com.example.expensetracker.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target ="id", ignore =true)
    @Mapping(target ="categories", ignore =true)
    User toEntity(UserRequest userRequest);

    UserResponse toResponse(User user);
    
}
