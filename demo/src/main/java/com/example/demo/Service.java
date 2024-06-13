package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    @Autowired

    UserRepository userRepository;
    void c(String name, String password){
        userRepository.save(new User(name, password));
    }
    User r(long id){
        return userRepository.findById(id);
    }
    List<User> r(){
        return userRepository.findAll();
    }
    void u(String name, String password, long id){
        User user = userRepository.findById(id);
        user.setName(name);
        user.setPassword(password);
        userRepository.save(user);
    }
    void d(int id){
        userRepository.deleteById(id);
    }
}
