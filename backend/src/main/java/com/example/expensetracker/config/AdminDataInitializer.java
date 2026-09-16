package com.example.expensetracker.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.expensetracker.entity.User;
import com.example.expensetracker.enums.Role;
import com.example.expensetracker.repository.UserRepository;

import lombok.AllArgsConstructor;

@Component 
@AllArgsConstructor 
public class AdminDataInitializer implements CommandLineRunner{
    
    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;

    @Override 
    public void run(String... args){

        String adminEmail =  "admin@expensetracker.local";

        if(userRepository.existsByEmail(adminEmail)) return ;

        User admin = User.builder()
            .name("Expense Tracker Admin")
            .email(adminEmail)
            .passwordHash(passwordEncoder.encode("Admin@12345"))
            .role(Role.ADMIN)
            .build();

        userRepository.save(admin);
    }

}
