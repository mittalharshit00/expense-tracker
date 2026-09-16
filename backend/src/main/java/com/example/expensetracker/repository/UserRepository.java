package com.example.expensetracker.repository;

import com.example.expensetracker.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email,Integer userId);

    Optional<User> findByEmail(String email);
}
