package com.example.trade;

import com.example.model.User;
import com.example.server.Response;

import java.util.List;

public interface TradingDeal_Interface {

    Response create(TradingDealEntity tradeDeal, int user_id);
    List<TradingDealEntity> check(User user);
    Response delete(String id);
}
