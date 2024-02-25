package com.example.server;

import com.example.controller.Battle;
import com.example.controller.PackageManager;
import com.example.controller.UserController;
import com.example.enums.ContentType;
import com.example.enums.HttpStatus;
import com.example.model.User;
import com.example.trade.TradinDealDao;
import com.example.trade.TradingDealController;
import com.example.trade.TradingDealEntity;
import com.example.util.ConstResponses;

public class POST {

    UserController userController;
    TradingDealController tradingDealController;
    PackageManager packagesController = new PackageManager();

    //constructure
    public POST() {
        userController = new UserController();
        tradingDealController = new TradingDealController();
    }

    static Battle battle = new Battle();

    public Response createPostResponse(Request request) {
        return switch (request.getPathname()) {
            case "/battles" -> createBattle(request);
            case "/users" -> this.userController.createUser(request.getBody());
            case "/sessions" -> this.userController.isHave(request.getBody());
            case "/packages" -> createPackages(request);
            case "/transactions/packages" -> createTransactionPackages(request);
            case "/tradings" -> createTradings(request);
            default ->
                    new Response(HttpStatus.NOT_FOUND, ContentType.JSON, "{ \"error\": \"Not Found\", \"data\": null }");
        };

    }

    public Response createBattle(Request request) {
        User usr = Sessions.isValidUser(request.getAuthorization());
        if (usr == null) {
            return ConstResponses.JSON_NOT_FOUND_RESPONSE("User Not Have Session");
        }
        return battle.start_battle(usr);

    }

    public Response createTradings(Request request) {
        String[] paths = request.getPathname().split("/");
        if (paths.length > 2 && paths[2].length() == 36) {
            final User buyerUser = Sessions.isValidUser(request.getAuthorization());
            if (buyerUser == null) return
                    ConstResponses.AUTHORIZATION_FAIL_RESPONSE("User not have session. ");
            final TradingDealEntity tradingDeal = new TradinDealDao().getByID(paths[2]);
            if (tradingDeal == null) return
                    ConstResponses.JSON_NOT_FOUND_RESPONSE("Tradin Deal with your ID not in Database");
            if (buyerUser.getId() == tradingDeal.getUser_id())
                return ConstResponses.AUTHORIZATION_FAIL_RESPONSE("You cant trade with yourself");
            String buyerCardId = request.getBody();
            return new TradingDealController().trade(buyerUser, buyerCardId, tradingDeal);
        } else {
            User usrT = Sessions.isValidUser(request.getAuthorization());
            if (usrT == null)
                return ConstResponses.JSON_NOT_FOUND_RESPONSE("User Not Have Session");
            else {
                return this.tradingDealController.create(request.getBody(), usrT);
            }
        }
    }

    public Response createPackages(Request request) {
        if (Sessions.isAdmin(request.getAuthorization())) {
            return this.packagesController.create_new_package(request.getBody());
        }
        return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, "{\"error\":\"unauthorized access request\",\"data\":\"null}");
    }

    public Response createTransactionPackages(Request request) {
        User user = Sessions.isValidUser(request.getAuthorization());
        if (user != null) {
            String result = this.packagesController.buy_package(user);
            if (result.equals("SUCCESSFUL")) {
                return new Response(HttpStatus.OK, ContentType.JSON, "{ \"result\": \"" + result + "\", \"data\": " + request.getAuthorization() + " }");
            }
            return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, "{ \"error\": \"" + result + "\", \"data\": null }");

        }
        return new Response(HttpStatus.NOT_FOUND, ContentType.JSON, "{ \"error\": \"Not Valid User\", \"data\": null }");

    }


}
