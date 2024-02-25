package com.example.server;

import com.example.controller.UserController;
import com.example.enums.ContentType;
import com.example.enums.HttpStatus;
import lombok.AccessLevel;
import lombok.Setter;

public class App implements ServerApp {

    @Setter(AccessLevel.PRIVATE)
    private UserController userController;

    public App() {
        new ClearLog().start();//Clear all log when Platform exit
        setUserController(new UserController());
    }


    @Override
    public synchronized Response handleRequest(Request request) {
        switch (request.getMethod()) {
            case GET: {
                return new GET().createResponse(request);
            }
            case POST: {
                return new POST().createPostResponse(request);
            }
            case PUT: {
                return new PUT().createPutResponse(request);
            }
            case DELETE: {
                return new DELETE().createResponse(request);
            }
        }
        return new Response(HttpStatus.NOT_FOUND, ContentType.JSON, "{ \"error\": \"Not Found\", \"data\": null }");
    }

}
