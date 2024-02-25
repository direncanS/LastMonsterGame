package com.example.server;

import com.example.controller.ScoreBoardController;
import com.example.controller.UserController;
import com.example.dao.ScoreBoardDAO;
import com.example.enums.ContentType;
import com.example.enums.HttpStatus;
import com.example.model.User;
import com.example.trade.TradingDealController;
import com.example.util.ConstResponses;

public class GET {

    private final UserController userController;
    private final TradingDealController tradingDealController;
    public GET() {
        userController = new UserController();
        tradingDealController=new TradingDealController();
    }

    //FOR GET REQUESTS
    public Response createResponse(Request request) {
        String body=  request.getBody();
        String path=request.getPathname();
        if(path.equals("/cards"))
            return this.userController.getUserCards(request.getAuthorization());


        if(path.equals("/deck")){
            User user =Sessions.isValidUser(request.getAuthorization()) ;
            if(user==null)
                return ConstResponses.JSON_NOT_FOUND_RESPONSE("User Not Found");
            return user.getDeck().g_configured_deck();
        }

        if(path.contains("/users")){
            User user =Sessions.isValidUser(request.getAuthorization()) ;
            if(user==null)
                return ConstResponses.JSON_NOT_FOUND_RESPONSE("User Not Found");
            if(!path.endsWith(request.getAuthorization()))
                return ConstResponses.AUTHORIZATION_FAIL_RESPONSE("User Not Authorized for this operation");
            return ConstResponses.JSON_OK_RESPONSE(user.toString());
        }


        if(path.contains("/stats")){
            User user =Sessions.isValidUser(request.getAuthorization()) ;
            if(user==null)
                return ConstResponses.JSON_NOT_FOUND_RESPONSE("User Not Have Session");
            return ConstResponses.JSON_OK_RESPONSE(user.getStats().toString());
        }

        if(path.contains("/tradings")){
            User user =Sessions.isValidUser(request.getAuthorization()) ;
            if(user==null)
                return ConstResponses.JSON_NOT_FOUND_RESPONSE("User Not Have Session");
            return this.tradingDealController.getUserTradingDeals(user);
        }


        if(path.contains("/scoreboard")){
            User user =Sessions.isValidUser(request.getAuthorization()) ;
            if(user==null)
                return ConstResponses.JSON_NOT_FOUND_RESPONSE("User Not Have Session");
            return new ScoreBoardController().getScoreBoards(user);
        }


        return new Response(HttpStatus.NOT_FOUND, ContentType.JSON, "{ \"error\": \"Not Found\", \"data\": null }");

    }
}
