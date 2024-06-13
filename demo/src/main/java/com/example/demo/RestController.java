package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    Service service;
    @PostMapping("/c")
    void c(String name, String password){
        service.c(name, password);
    }
    @GetMapping("/r")
    User r(long id){
        return service.r(id);
    }
    @GetMapping("/rAll")
    List<User> rAll(){
        return service.r();
    }
    @PutMapping("/u")
    void u(String name, String password, long id){
        service.u(name, password, id);
    }
    @DeleteMapping("/d")
    void d(int id){
        service.d(id);
    }
}
