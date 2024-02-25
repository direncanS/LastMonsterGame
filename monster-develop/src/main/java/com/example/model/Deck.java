package com.example.model;

/*
    the best 4 cards are selected by the user to be used in the deck.
    the deck is used in the battles against other players.
    a battle is a request to the server to compete against another user with your currently defined deck
 */

import com.example.enums.Type;
import com.example.server.Response;
import com.example.util.ConstResponses;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;


public class Deck {
    @Getter
    private final List<Card> spellCards;
    @Getter
    private final List<Card> monsterCards;
    private final ObjectMapper objectMapper;
    public Deck() {
        //this.unconfiguredCards=new ArrayList<>();
        this.spellCards=new ArrayList<>();
        this.monsterCards=new ArrayList<>();
        objectMapper=new ObjectMapper();
    }




//    public Response addUnConfiguredCardsToDeck(List<Card> cards){
//        if(cards==null || cards.size()!=4)
//                return ConstResponses.JSON_BAD_REQUEST_RESPONSE("number of cards is not suitable");
//
//        this.unconfiguredCards.addAll(cards);
//        return ConstResponses.JSON_OK_RESPONSE("cards added to deck, deck size"+this.unconfiguredCards.size());
//    }



//    public Response get_unconfigured_deck(){
//        if(!this.unconfiguredCards.isEmpty()) {
//            try {
//                String data= objectMapper.writeValueAsString(this.unconfiguredCards);
//                return new Response(
//                        HttpStatus.OK,
//                        ContentType.JSON,
//                        "{\"error\": \"null\", \"data\":  "+data+"}");
//            } catch (JsonProcessingException e) {
//                return ConstResponses.JSON_CONVERT_ERROR;
//            }
//        }
//        return ConstResponses.JSON_OK_RESPONSE("deck is EMPTY");
//    }


    public void add_card_list_to_deck(List<Card> cards) {
        for (Card card : cards) {
            add_card_to_deck(card);
        }
    }



    public void add_card_to_deck(Card card ) {
             if (card.getName().contains("Spell"))
                this.spellCards.add(card);
            else this.monsterCards.add(card);
     }



    public void configure_cards(List<Card> cards){
        for (Card card : cards) {
            if (card.getName().contains("Dragon"))
                card.setElement_type(Type.FIRE);
            else if (card.getName().contains("Water"))
                card.setElement_type(Type.WATER);
            else if (card.getName().contains("Fire"))
                card.setElement_type(Type.FIRE);
            else card.setElement_type(Type.NORMAL);
        }
    }




    public Response g_configured_deck(){
        try {
            String monsterCards=objectMapper.writeValueAsString(this.monsterCards);
            String speelCards=objectMapper.writeValueAsString((this.spellCards));
            String data="MONSTERS: "+monsterCards+"\nSPELL: "+speelCards;
            return ConstResponses.JSON_OK_RESPONSE(data);
        } catch (JsonProcessingException e) {
            return ConstResponses.JSON_CONVERT_ERROR("Cant convert to object from your JSON statement");
        }
    }
}
