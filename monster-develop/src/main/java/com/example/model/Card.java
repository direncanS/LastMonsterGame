package com.example.model;


import com.example.enums.Type;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

// interface sınıflarda public, final, static
@Getter
@Setter
public class Card implements Serializable {
    @JsonSetter("Id")
    private String id;
    @JsonSetter("Name")
    private String name;
    @JsonSetter("Damage")
    public double damage;
    public Type element_type=null;


    public Card() {
    }

    public Card(String id, String name, double damage) {
        this.id = id;
        this.name = name;
        this.damage = damage;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(id, card.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return    "("+element_type+") "+name + ": " +  damage +" damage";
    }
}


//    public static void main(String[] args) {
//        Card card=new MonsterCard();
//        card.element_type= ElementType.FIRE;
//        card.damage=10d;
//        Card vCard=new MonsterCard();
//        vCard.element_type= ElementType.WATER;
//        vCard.damage=20d;
//        vCard.atackEnemy(card);
//        //WATER 40<-->FIRE 5
//    }

