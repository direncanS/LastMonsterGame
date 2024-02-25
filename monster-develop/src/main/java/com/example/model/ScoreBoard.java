package com.example.model;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ScoreBoard implements Comparable<ScoreBoard> {

    //especially ELO value (+3 points for win, -5 for loss,; higher sophisticated ELO system welcome)
    private int id;
    private int player1ELO;
    private int player2ELO;
    private int winnerELO;
    private User player1;
    private User player2;
    private User winner;
    private Card player1Cards;
    private Card player2Cards;
    private double game_score;
    private String data;
    private double player1Damage;
    private double player2Damage;

    public Card getPlayer1Cards() {
        if(this.player1Cards==null)
            player1Cards=new Card();
        return player1Cards;
    }

    public Card getPlayer2Cards() {
        if(this.player2Cards==null)
            player2Cards=new Card();
        return player2Cards;
    }

    //constructure
    public ScoreBoard() {
    }


    public User getPlayer1() {
        if(this.player1==null)
            player1=new User();
        return player1;
    }

    public User getPlayer2() {
        if(this.player2==null)
            player2=new User();
        return player2;
    }

    public User getWinner() {
        if(this.winner==null)
            winner=new User();
        return winner;
    }


    @Override
    public int compareTo(ScoreBoard o) {
        if(o.player1ELO>this.player1ELO) return -1;
        if(o.player1ELO<this.player1ELO) return 1;
        return 0;
    }



    @Override
    public String toString() {
        return "Player1: " + getPlayer1().getName()
                +" <- vs -> "
                +"Player2: "+getPlayer2().getName()
                + " (winner: "+getWinner().getName()
                +", ELO: "
                + player1ELO +")";
    }
}
