package com.example.controller;

import com.example.dao.ScoreBoardDAO;
import com.example.enums.Type;
import com.example.model.ScoreBoard;
import com.example.model.Card;
import com.example.model.User;
import com.example.server.Response;
import com.example.util.ConstResponses;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Battle {
    private User player1;
    private User player2;

    //bitiminde playerleri null yapmayı unutma
    public Response start_battle(User user) {
        ScoreBoard scoreBoard=new ScoreBoard();
        int total_card = get_player_card_count(user);
        if (total_card < 4)
            return ConstResponses.JSON_BAD_REQUEST_RESPONSE("user not have enough card in deck. " +
                    "user has only " + total_card + " card");
        if (player1 == null) {
            player1 = user;
            return ConstResponses.JSON_OK_RESPONSE("Player 1 setted as " + user.getName());
        } else if (player2 == null){
            player2 = user;
            //Game Start
            Card player2Card=selectRandomlyACard(player1);
            Card player1Card=selectRandomlyACard(player2);
            //play a round
            play_a_round(player1Card, player2Card, scoreBoard);
            save_score_to_database(scoreBoard);

            //add scoreboard to user list
            player1.getScoreBoards().add(scoreBoard);
            player2.getScoreBoards().add(scoreBoard);
            return ConstResponses.JSON_OK_RESPONSE("A round finished");
        }
        else {
            return ConstResponses.JSON_BAD_REQUEST_RESPONSE("all players already setted");

        }

    }

    public String save_score_to_database(ScoreBoard scoreBoard){
        return new ScoreBoardDAO().create(scoreBoard);
    }


    public Card selectRandomlyACard(User user){
        Card card=null;
        List <Card> speelCards=null;
        List <Card> monsterCards=null;
        boolean monster=false;
        do{
            int num1 = ThreadLocalRandom.current().nextInt()%2;
            if(num1==0){//mean monster card
                monsterCards=user.getDeck().getMonsterCards();
                if(monsterCards==null ||monsterCards.isEmpty()) break;
                int selectNum = ThreadLocalRandom.current().nextInt() % monsterCards.size();
                System.out.println("Monster Card selectNum: "+selectNum);
                card=monsterCards.get(Math.abs(selectNum));

            }else{//mean spell card
                speelCards=user.getDeck().getMonsterCards();
                if(speelCards==null ||speelCards.isEmpty()) break;
                int selectNum = ThreadLocalRandom.current().nextInt() % speelCards.size();
                card=speelCards.get(Math.abs(selectNum));

            }
        }
        while(card==null);
        if(monsterCards!=null)
            monsterCards.remove(card);
        if(speelCards!=null)
            speelCards.remove(card);
        return card;
    }





    private int get_player_card_count(User user) {
        int monstercard_size = user.getDeck().getMonsterCards().size();
        int spellcard_size = user.getDeck().getSpellCards().size();
        int total_card_count = monstercard_size + spellcard_size;
        return total_card_count;
    }



    public void play_a_round(Card player1Card, Card player2Card, ScoreBoard scoreBoard) {

        String data= "Player 1 "+player1Card.getName()+"(damage: "+player1Card.getDamage()+") vs "+
                "Player 2 "+player2Card.getName()+"(damage: "+player2Card.getDamage()+") => "+
                player1Card.getDamage()+" vs "+player2Card.getDamage()+"--> ("+player1Card.getElement_type()+" vs "+player2Card.element_type+")";
        double player1CardDamage=player1Card.getDamage();
        double player2CardDamage=player2Card.getDamage();


        switch (player2Card.element_type) {
            case WATER:
                if (player1Card.element_type == Type.FIRE) {
                    player2CardDamage *= 2;
                    player1CardDamage /= 2;
                }
                if (player1Card.element_type == Type.NORMAL) {
                    player2CardDamage /= 2;
                    player1CardDamage *= 2;
                }
                break;
            case FIRE:
                if (player1Card.element_type == Type.WATER) {
                    player2CardDamage /= 2;
                    player1CardDamage *= 2;
                }
                if (player1Card.element_type == Type.NORMAL) {
                    player2CardDamage *= 2;
                    player1CardDamage /= 2;
                }
                break;
            case NORMAL:
                if (player1Card.element_type == Type.FIRE) {
                    player2CardDamage /= 2;
                    player1CardDamage *= 2;
                }
                if (player1Card.element_type == Type.WATER) {
                    player2CardDamage *= 2;
                    player1CardDamage /= 2;
                }
                break;
        }
        // SET BATTLE DETAİLS

        //take-over cards after loss of a round
        if(player1CardDamage>player2CardDamage){
            getPlayer1().updateCurrentELO(5);
            getPlayer2().updateCurrentELO(-3);
            scoreBoard.setWinnerELO(getPlayer1().getCurrentELO());
            scoreBoard.setWinner(getPlayer1());
            getPlayer1().getDeck().add_card_to_deck(player1Card);
            getPlayer2().getDeck().add_card_to_deck(player2Card);

        }
        //take-over cards after loss of a round
        if(player2CardDamage>player1CardDamage){
            getPlayer2().updateCurrentELO(5);
            getPlayer1().updateCurrentELO(-3);
            scoreBoard.setWinner(getPlayer2());
            scoreBoard.setWinnerELO(getPlayer2().getCurrentELO());
            getPlayer2().getDeck().add_card_to_deck(player1Card);
            getPlayer2().getDeck().add_card_to_deck(player2Card);
        }
        getPlayer1().setGames_played(getPlayer1().games_played++);
        getPlayer1().setGames_played(getPlayer2().games_played++);
        scoreBoard.setPlayer1ELO(getPlayer1().getCurrentELO());
        scoreBoard.setPlayer2ELO(getPlayer2().getCurrentELO());
        scoreBoard.setPlayer1(getPlayer1());
        scoreBoard.setPlayer2(getPlayer2());
        scoreBoard.setPlayer1Cards(player1Card);
        scoreBoard.setPlayer2Cards(player2Card);
        data+=player1CardDamage+" vs "+player2CardDamage;
        scoreBoard.setPlayer1Damage(player1CardDamage);
        scoreBoard.setPlayer2Damage(player2CardDamage);
        scoreBoard.setData(data);


    }

    public User getPlayer1() {
        if(player1==null)
            player1=new User();
        return player1;
    }

    public User getPlayer2() {
        if(player2==null)
            player2=new User();
        return player2;
    }
}

 
