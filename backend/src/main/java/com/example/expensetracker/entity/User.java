package com.example.expensetracker.entity;


import java.util.ArrayList;
import java.util.List;

import com.example.expensetracker.enums.Role;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder 
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 100, nullable = false )
    private String name;

    @Column(name = "email", length = 255,nullable =false , unique = true)
    private String email;

    @Column(name ="password_hash",length =100,nullable =false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(name ="role",length = 50,nullable = false)
    private Role role;

    @Column(name ="enabled",length =5 ,nullable = false)
    private boolean enabled;

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Category> categories = new ArrayList<>();
}
