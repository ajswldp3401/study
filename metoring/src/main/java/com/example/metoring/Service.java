package com.example.metoring;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    String url = "jdbc:mysql://localhost:3306/dtd?serverTimezone=UTC";
    PreparedStatement stat;
    Connection conn;
    Service(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, "root", "ajswl3401");

        }catch (SQLException e){
            System.out.println("생성자 "+e);
        }catch (ClassNotFoundException e){
            System.out.println(e);
        }
    }
    void sinUp(String name, String password){
        try {
            stat = conn.prepareStatement("insert into login.user (name, password) value(?, ?);");//새그먼트 생성
            stat.setString(1, name);
            stat.setString(2, password);
            stat.executeUpdate();//PreparedStatement 실행
        }catch (Exception e){
            System.out.println("sinUp:"+e);
        }
    }
    int logIn(String name, String password){
        try {
            stat = conn.prepareStatement("select id from login.user where name = ? and password = ?");
            stat.setString(1, name);
            stat.setString(2, password);
            ResultSet re = stat.executeQuery();


            if(!re.next())return -1;
            return re.getInt("id");
        }catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }
    List<board> getBoardList(){
        List<board> list= new ArrayList<board>();
        try {
            stat = conn.prepareStatement("select * from login.board");
            ResultSet re = stat.executeQuery();
            while(re.next()){
                list.add(0, new board(re.getString(1), re.getNString(2)));
            }
            return list;

        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }
    List<board> getMyBoardList(int id){
        List<board> list= new ArrayList<board>();
        try {
            stat = conn.prepareStatement("select * from login.board where id = ?");
            stat.setInt(1, id);
            ResultSet re = stat.executeQuery();
            while(re.next()){
                list.add(0, new board(re.getString(1), re.getNString(2)));
            }
            return list;

        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }
    void createBoard(int id, String title, String text){
        try {
            stat = conn.prepareStatement("insert into login.board (title, text, id) value (?, ?, ?)");
            stat.setString(1, title);
            stat.setString(2, text);
            stat.setInt(3, id);
            stat.execute();
        }catch (SQLException e){
            System.out.println(e);
        }

    }
}
