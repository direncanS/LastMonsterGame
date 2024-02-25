package com.example.server;

import com.example.enums.ContentType;
import com.example.enums.HttpStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.net.*;

@Getter
@Setter
public class Server implements Runnable {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private App app;
    private int port;

    public Server(App app, int port) {
        setApp(app);
        setPort(port);
    }

    public void start() throws IOException {
        System.out.println("Setting port: " + getPort());
        setServerSocket(new ServerSocket(getPort()));
        Thread thread = new Thread(this); // Create a thread with this Runnable instance
        thread.start(); // Start the threads
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) { // Run until interrupted
            try {
                handleRequest(getServerSocket().accept()); // Handle the request
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleRequest(Socket clientSocket) {
        try (
                BufferedReader inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter outputStream = new PrintWriter(clientSocket.getOutputStream(), true);
        ) {
            Request request = new Request(inputStream);
            Response response;
            if (request.getPathname() == null) {
                response = new Response(
                        HttpStatus.BAD_REQUEST,
                        ContentType.TEXT,
                        ""
                );
            } else {
                response = getApp().handleRequest(request);
            }
            outputStream.write(response.build());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}