package com.example.expensetracker.mapper;

import com.example.expensetracker.dto.request.UserRequest;
import com.example.expensetracker.dto.response.UserResponse;
import com.example.expensetracker.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-08T22:03:19+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.11-ea (Debian)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        User user = new User();

        user.setName( userRequest.getName() );
        user.setEmail( userRequest.getEmail() );

        return user;
    }

    @Override
    public void updateEntity(UserRequest userRequest, User user) {
        if ( userRequest == null ) {
            return;
        }

        user.setName( userRequest.getName() );
        user.setEmail( userRequest.getEmail() );
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
