package com.example.server;

import com.example.controller.UserController;
import com.example.model.User;
import com.example.trade.TradingDealController;
import com.example.util.ConstResponses;

public class PUT {
    UserController userController;
    TradingDealController tradingDealController;

    public PUT() {
        userController = new UserController();
        tradingDealController = new TradingDealController();
    }

    //FOR PUT REQUESTS
    public Response createPutResponse(Request request) {
        final String path = request.getPathname();
        return switch (path) {
            case "/users" -> updateUser(path, request);
            case "/deck" -> updateDeck(request);
            default -> ConstResponses.JSON_BAD_REQUEST_RESPONSE(request.getPathname() + " not found");
        };
    }

    private Response updateUser(String path, Request request) {
        User user = Sessions.isValidUser(request.getAuthorization());
        if (user == null)
            return ConstResponses.JSON_NOT_FOUND_RESPONSE("User Not Found");
        if (!path.endsWith(request.getAuthorization()))
            return ConstResponses.AUTHORIZATION_FAIL_RESPONSE("User Not Authorized for this operation");
        return this.userController.update(request.getBody(), user);

    }

    private Response updateDeck(Request request){
        User user = Sessions.isValidUser(request.getAuthorization());
        if (user == null)
            return ConstResponses.JSON_NOT_FOUND_RESPONSE("User Not Found");
        return user.update_deck(request);
    }

}
