package com.example;

import com.example.server.App;
import com.example.server.Server;

import java.io.IOException;

public class Main  {

    public static void main(String[] args) {

        App app = new App();
        Server server = new Server(app, 10001);
        try {
            System.out.println("Server starting...");
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}