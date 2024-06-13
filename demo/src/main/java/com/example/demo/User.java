package com.example.demo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(schema = "jpacrud")
@Getter
public class User {
    @Setter
    @Column(nullable = false)
    String name;

    @Setter
    @Column(nullable = false)
    String password;

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public User() {}
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
