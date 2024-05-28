package com.example.metoring;

import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class restService {
    String url = "jdbc:mysql://localhost:3306/dtd?serverTimezone=UTC";


    void c(String userId, String password){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//드라이버 로드
            Connection conn = DriverManager.getConnection(url, "root", "ajswl3401");//권한주기, 커넥션 생성
            PreparedStatement stat = conn.prepareStatement("insert into dtd.user (name, password) value(?, ?);");//새그먼트 생성
            stat.setString(1, userId);
            stat.setString(2, password);
            stat.executeUpdate();//PreparedStatement 실행

        } catch (ClassNotFoundException e) {
            System.out.println(e);//드라이버 로드 실패
        } catch (SQLException e) {
            System.out.println(e);//sql문이 틀렸거나 커넥션 생성 실패
        }
    }
    userDTO r(int id){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, "root", "ajswl3401");
            PreparedStatement stat = conn.prepareStatement("select * from dtd.user where id = ?;");
            stat.setInt(1, id);
            ResultSet re = stat.executeQuery();

            if(!re.next()){
                return new userDTO(null, null);
            }
            return new userDTO(re.getString("name"), re.getString("password"));

        }catch (ClassNotFoundException e){
            System.out.println(e);
        }catch (SQLException e){
            System.out.println(e);
        }
        return new userDTO(null, null);

    }
    void u(int id, String newUserId, String newPassword){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, "root", "ajswl3401");

            PreparedStatement statUser = conn.prepareStatement("UPDATE dtd.user set name = ? WHERE id = ?");
            statUser.setString(1, newUserId);
            statUser.setInt(2, id);
            statUser.executeUpdate();

            PreparedStatement statPassword = conn.prepareStatement("UPDATE dtd.user set password = ? WHERE id = ?");
            statPassword.setString(1, newPassword);
            statPassword.setInt(2, id);
            statPassword.executeUpdate();

        }catch (ClassNotFoundException e){
            System.out.println(e);
        }
        catch (SQLException e){
            System.out.println(e);
        }

    }
    void d(int id){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, "root", "ajswl3401");

            PreparedStatement stat = conn.prepareStatement("DELETE FROM dtd.user WHERE id = ?");
            stat.setInt(1, id);
            stat.executeUpdate();


        }catch (ClassNotFoundException e){
            System.out.println(e);
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }
}
