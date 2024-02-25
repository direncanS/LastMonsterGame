package com.example.model;

import com.example.server.Request;
import com.example.server.Response;
import com.example.util.ConstResponses;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

@Getter
@Setter
public class User implements Serializable {
    private int id;
    @JsonSetter("Username")
    private String username;
    @JsonSetter("Password")
    private String password;
    @JsonSetter("Name")
    private String name;
    @JsonSetter("Bio")
    private String bio;
    @JsonSetter("Image")
    private String image;
    private int coin;
    //the deck is used in the battles against other players.
    @Getter(AccessLevel.NONE)
    private Deck deck; // best 4 cards are selected by the user to be used in the deck.
    private List<APackage> packages;
    @Getter(AccessLevel.NONE)
    private Stats stats;
    TreeSet<ScoreBoard> scoreBoards;
    @Setter(AccessLevel.NONE)
    int currentELO;
    @Getter(AccessLevel.NONE)
    public int games_played;


    public User() {
        this.scoreBoards=new TreeSet<>();
        this.coin = 20;
        this.packages = new ArrayList<>();
        this.currentELO=100;
        games_played=0;
    }



    public User(String Username, String Password) {
        this();
        this.username = Username;
        this.password = Password;
    }


    public void updateCurrentELO(int value){
            this.currentELO+=value;
    }

    public Stats getStats(){
           if(this.stats==null){
               stats=new Stats();
           }
           return stats;
    }



    public Deck getDeck() {
        if(deck==null)
            deck=new Deck();
        return deck;
    }

    public List<Card> getAllCardList(){
        List<Card> userCards=new ArrayList<>();
        for (APackage apackage : packages){
            for (Card card:apackage.getCards()) {
                userCards.add(card);
            }
        }
        return userCards;
    }


    /**
     *
     * @param token : card token
     * @return if cant find card then  null else return Card
     */
    public Card getCardInPackageByTOKEN(String token){
        for (APackage apackage : packages){
            for (Card card:apackage.getCards()) {
                if(card.getId().equals(token)){
                    return card;
                }
            }
        }
        return null;
    }





    public void remove_cards_from_package(List<Card> cards){
            for (Card card:cards) {
                remove_a_card_from_package(card);
            }
     }

    public void remove_a_card_from_package(Card card){
        for (APackage apackage : packages){
            apackage.getCards().remove(card);
        }
    }




    public Response update_deck(Request request) {
        List<Card> cards=new ArrayList<>();
        try {
            String[] cardTokens=new ObjectMapper().readValue(request.getBody(),String[].class);
            if(cardTokens.length!=4)
                return ConstResponses.JSON_BAD_REQUEST_RESPONSE("cant update deck because COUNT is wrong");

            for (int i=0;i<cardTokens.length;i++){
                Card current_card=getCardInPackageByTOKEN(cardTokens[i]);
                if(current_card==null)
                    return ConstResponses.JSON_BAD_REQUEST_RESPONSE("cant update deck because card token is not in package");
                cards.add(current_card);
            }
            //firstly configure deck: WATER, FIRE, NORMAL
            this.getDeck().configure_cards(cards);
            //set MONSTER OR SPELL CARD
            this.getDeck().add_card_list_to_deck(cards);
            //remove cards from package
            remove_cards_from_package(cards);

            return ConstResponses.JSON_OK_RESPONSE(request.getAuthorization()+" deck is updated");
        } catch (JsonProcessingException e) {
            return ConstResponses.JSON_CONVERT_ERROR("Cant convert to OBJECT");
        }
    }





    public void addPackage(APackage aPackage){
        this.packages.add(aPackage);
    }


    public Response update(String body) {
        try {
            User usr=new ObjectMapper().readValue(body, User.class);
            if(usr==null)
                return ConstResponses.JSON_BAD_REQUEST_RESPONSE("User not have session");
            this.setName(usr.getName());
            this.setBio(usr.getBio());
            this.setImage(usr.getImage());
            return ConstResponses.JSON_OK_RESPONSE(username+ " profile updated...");
        } catch (JsonProcessingException e) {
            return ConstResponses.JSON_CONVERT_ERROR("your JSON statements Cant convert to USER");
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                ", image='" + image + '\'' +
                ", coin=" + coin +
                '}';
    }


}