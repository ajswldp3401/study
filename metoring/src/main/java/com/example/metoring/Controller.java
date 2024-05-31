package com.example.metoring;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    Service service;

    @GetMapping("/")
    String mainBoard(
            @CookieValue(value = "user", required = false)
            Cookie coookie,
            String name,
            String password
    ){
        if(coookie==null) return "redirect:/sinUp";
        return "redirect:/board";

    }

    @ResponseBody
    @PostMapping("/sinUp")
    void sinUp(String name, String password){
        service.sinUp(name, password);
    }

    @ResponseBody
    @PostMapping("/logIn")
    String logIn(HttpServletResponse httpServletResponse, String name, String password){
        int id = service.logIn(name, password);
        if(-1 == id)return "실패";

        Cookie cookie = new Cookie("user", id+"");
        cookie.setMaxAge(60*60*24);
        cookie.setDomain("localhost");
        cookie.setPath("/");

        httpServletResponse.addCookie(cookie);

        return "시파이";
    }
    @ResponseBody
    @GetMapping("/board")
    List<board> board(@CookieValue(value = "user", required = false) Cookie coookie){
        if(coookie==null) return null;
        return service.getBoardList();
    }
    @ResponseBody
    @GetMapping("/myBoard")
    List<board> myBoard(@CookieValue(value = "user", required = false) Cookie coookie){
        if(coookie==null) return null;
        int id = Integer.parseInt(coookie.getValue());

        return service.getMyBoardList(id);
    }
    @ResponseBody
    @PostMapping("/createBoard")
    void createBoard(@CookieValue(value = "user", required = false) Cookie coookie,
                     String title, String text){
        if(coookie==null) return;
        int id = Integer.parseInt(coookie.getValue());
        service.createBoard(id, title, text);
    }
}
