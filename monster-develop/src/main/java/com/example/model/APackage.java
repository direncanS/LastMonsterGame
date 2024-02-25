package com.example.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
/*
a package consists of 5 cards and can be acquired from the server by paying 5 virtual coins.
every user has 20 coins to buy (4) packages.
 */
@Getter
public class APackage {

    private List<Card> cards;//a package consist 5 card

    public APackage( ) {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card){
       this.cards.add(card);
    }

}
