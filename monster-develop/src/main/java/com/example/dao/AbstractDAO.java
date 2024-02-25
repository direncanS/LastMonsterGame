package com.example.dao;

import com.example.util.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;


public class AbstractDAO {

    private Connection conn;


    protected Connection getConn(){
        return this.conn==null?this.conn=new DBConnection().conn:this.conn;
    }

    protected void closeConnection(){
        try {
            this.conn.close();
        } catch (SQLException e) {
        }
    }
}
