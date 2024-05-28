package com.example.metoring;

import lombok.Getter;

@Getter
public class userDTO {
    private final String userId;
    private final String password;
    userDTO(String userId, String password){
        this.userId = userId;
        this.password = password;
    }
}
