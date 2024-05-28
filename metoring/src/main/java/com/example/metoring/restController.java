package com.example.metoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@RestController
public class restController {
    @Autowired
    restService service;

    @PostMapping()
    void c(String userId, String password){
        service.c(userId, password);
    }
    @GetMapping()
    userDTO r(int id){
        return service.r(id);
    }
    @PatchMapping()
    void u(int id, String userId, String password){
        service.u(id, userId, password);
    }
    @DeleteMapping()
    void d(int id){
        service.d(id);
    }


}
