package com.example.dao;

import com.example.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO implements DaoI<User> {



     public String create(User entity) {
        String insertStmt = "insert into users(username,passw,coins,elo,gamesplayed)values(?,?,?,?,?);";

        try {
            PreparedStatement preparedStatement = getConn().prepareStatement(insertStmt);
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setInt(3, entity.getCoin());
            preparedStatement.setInt(4, 2);
            preparedStatement.setInt(5,2);
            preparedStatement.execute();
            closeConnection();
            return "SUCCEED";
         } catch (SQLException e) {
            return e.getMessage();
        }
    }



    public User getUserIfUserInDatabase(User entity) {
        String insertStmt = "select * from users where username=? and passw=?;";
        User user=null;
        try {
            PreparedStatement preparedStatement = getConn().prepareStatement(insertStmt);
            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setString(2, entity.getPassword());
            ResultSet rs=preparedStatement.executeQuery();
            if(rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setPassword(rs.getString("passw"));
                user.setUsername(rs.getString("username"));
                user.setCoin(rs.getInt("coins"));
                //user.setELO(resultSet.getInt("elo"));
                //user.setScoreBoard(resultSet.getInt("gamesplayed"));closeConnection();
            }
            closeConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }




     public List<User> readAll() {
        List<User> userList = new ArrayList<>();
        String insertStmt = "SELECT * from users;";
        try {
            PreparedStatement preparedStatement = getConn().prepareStatement(insertStmt);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                User user=new User();
                user.setId(resultSet.getInt("id"));
                user.setPassword(resultSet.getString("passw"));
                user.setUsername(resultSet.getString("username"));
                user.setCoin(resultSet.getInt("coins"));
                //user.setELO(resultSet.getInt("elo"));
                //user.setScoreBoard(resultSet.getInt("gamesplayed"));
                userList.add(user);
            }
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList.isEmpty()?null:userList;
    }




    @Override
    public User read(int id) {
        String insertStmt = "select * from users where id=?;";
        User user=null;
        try {
            PreparedStatement preparedStatement = getConn().prepareStatement(insertStmt);
            preparedStatement.setInt(1,id);
             ResultSet rs=preparedStatement.executeQuery();
            if(rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setPassword(rs.getString("passw"));
                user.setUsername(rs.getString("username"));
                user.setCoin(rs.getInt("coins"));
                user.setName(rs.getString("name"));
                user.setGames_played(rs.getInt("games_played"));
                user.setBio(rs.getString("bio"));
                user.setBio(rs.getString("image"));
            }
            closeConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }



     public boolean delete(int id) {
        String delSQL="select from users where id=?";
        try{
            PreparedStatement pst=getConn().prepareStatement(delSQL);
            return pst.execute();
        }catch (SQLException ex){
            ex.getMessage();
            return false;
        }
    }
}
