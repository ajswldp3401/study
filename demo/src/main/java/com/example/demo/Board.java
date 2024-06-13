package com.example.demo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "jpacrud")
@Getter
public class Board {
    @Setter
    @Column(nullable = false)
    String title;

    @Setter
    @Column(nullable = false)
    String text;

    @Id
    @Column(nullable = false)
    private Long userId;

    public Board(String title, String text, Long userId) {
        this.title = title;
        this.text = text;
        this.userId = userId;
    }
    public Board(){}

}
