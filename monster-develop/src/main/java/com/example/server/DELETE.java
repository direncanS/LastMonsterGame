package com.example.server;

import com.example.controller.UserController;
import com.example.model.User;
import com.example.trade.TradinDealDao;
import com.example.util.ConstResponses;

public class DELETE {
    UserController userController;

    public DELETE() {
        userController = new UserController();
    }

    public Response createResponse(Request request) {
        String path = request.getPathname();
        String[] pathArray = path.split("/");
        if (path.startsWith("/users")) {
            return deleteUser(pathArray[2], request);
        } else if (path.startsWith("/tradings")) {
            return new TradinDealDao().delete(pathArray[2]);
        }
        return ConstResponses.JSON_BAD_REQUEST_RESPONSE(request.getPathname() + " not found");

    }

    private Response deleteUser(String path, Request request) {
        User user = Sessions.isValidUser(request.getAuthorization());
        if (user == null)
            return ConstResponses.JSON_NOT_FOUND_RESPONSE("User Not Found");
        if (!path.endsWith(request.getAuthorization()))
            return ConstResponses.AUTHORIZATION_FAIL_RESPONSE("User Not Authorized for this operation");
        return this.userController.deleteUser(Integer.parseInt(request.getParams()));

    }
}
