package com.example.expensetracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.expensetracker.dto.request.UserCreateRequest;
import com.example.expensetracker.dto.request.UserUpdateRequest;
import com.example.expensetracker.dto.response.UserResponse;
import com.example.expensetracker.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target ="id", ignore =true)
    @Mapping(target ="categories", ignore =true)
    @Mapping(target ="keycloakId",ignore =true)
    User toEntity(UserCreateRequest userCreateRequest);

    @Mapping(target ="id", ignore =true)
    @Mapping(target ="categories", ignore =true)
    @Mapping(target ="keycloakId",ignore =true)
    void updateEntity(UserUpdateRequest userUpdateRequest, @MappingTarget User user);

    UserResponse toResponse(User user);
    
}
