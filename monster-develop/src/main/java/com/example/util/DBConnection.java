package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final String url="jdbc:postgresql://localhost:5432/";
    private final String dbName="monster";
    private final String user= "postgres";
    private final String password= "1234";
    private String connectionString = url+dbName+"?user="+user+"&password="+password;
    public Connection conn;

    //Constructure
    public DBConnection() {
        try{
            conn=DriverManager.getConnection(connectionString);
        }catch (SQLException e) {
            System.out.println(e.getErrorCode()+": error code has occured");

        }
    }


}
