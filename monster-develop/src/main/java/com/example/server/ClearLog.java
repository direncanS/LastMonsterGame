package com.example.server;

import com.example.dao.ScoreBoardDAO;
import com.example.model.ScoreBoard;

public class ClearLog {
    public void start(){
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){
                new ScoreBoardDAO().deleteAll();
            }
        });


    }
}
