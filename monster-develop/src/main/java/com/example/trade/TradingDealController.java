package com.example.trade;

import com.example.controller.Controller;
import com.example.model.User;
import com.example.server.Response;
import com.example.util.ConstResponses;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public class TradingDealController extends Controller {
    TradinDealDao dao = new TradinDealDao();

    public Response getUserTradingDeals(User user) {
        List<TradingDealEntity> tradingDeals = this.dao.check(user);
//        if(user==null)
//            return new Response(
//                    HttpStatus.BAD_REQUEST,
//                    ContentType.JSON,
//                    "{ \"error\": \"List empty\", \"data\": null }");
        try {
            String jsonTradingDealList = getObjectMapper().writeValueAsString(tradingDeals);
            return ConstResponses.JSON_OK_RESPONSE(jsonTradingDealList);
        } catch (JsonProcessingException e) {
            return ConstResponses.JSON_CONVERT_ERROR("Jason convert error occured");
        }
    }


    public Response create(String body, User forUser) {
        try {
            System.out.println(body);
            TradingDealEntity newEntity = getObjectMapper().readValue(body, TradingDealEntity.class);//convert json to user
            return this.dao.create(newEntity,forUser.getId());

        } catch (JsonProcessingException e) {
            return ConstResponses.JSON_CONVERT_ERROR("ERR CODE: " + e.getMessage());
        }
    }

    public Response trade(User buyerUser, String buyerCardId, TradingDealEntity tradingDeal) {
        return new TradinDealDao().trade(buyerUser, buyerCardId, tradingDeal);
    }
}
