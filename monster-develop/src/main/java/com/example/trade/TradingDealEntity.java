package com.example.trade;

import com.example.model.Card;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TradingDealEntity  {
    @JsonSetter("Id") private String  id;
    @JsonSetter("CardToTrade")private String cardToTrade_id;
    @JsonSetter("Type")private String type;
    @JsonSetter("MinimumDamage")private double minimumDamage;
    private int user_id;
    public TradingDealEntity( ) {
     }

    public TradingDealEntity(String id, String cardToTrade_id, String type, double minimumDamage) {
        this.id = id;
        this.cardToTrade_id = cardToTrade_id;
        this.type = type;
        this.minimumDamage = minimumDamage;
    }


}
