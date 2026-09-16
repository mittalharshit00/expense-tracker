package com.example.expensetracker.mapper;

import com.example.expensetracker.dto.request.UserCreateRequest;
import com.example.expensetracker.dto.request.UserUpdateRequest;
import com.example.expensetracker.dto.response.UserResponse;
import com.example.expensetracker.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-16T20:43:20+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.11-ea (Debian)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserCreateRequest userCreateRequest) {
        if ( userCreateRequest == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.name( userCreateRequest.getName() );
        user.email( userCreateRequest.getEmail() );

        return user.build();
    }

    @Override
    public void updateEntity(UserUpdateRequest userUpdateRequest, User user) {
        if ( userUpdateRequest == null ) {
            return;
        }

        user.setName( userUpdateRequest.getName() );
        user.setEmail( userUpdateRequest.getEmail() );
    }

    @Override
    public UserResponse toResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( user.getId() );
        userResponse.setName( user.getName() );
        userResponse.setEmail( user.getEmail() );

        return userResponse;
    }
}
