package com.example.controller;

import com.example.dao.ScoreBoardDAO;
import com.example.model.ScoreBoard;
import com.example.model.User;
import com.example.server.Response;
import com.example.util.ConstResponses;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public class ScoreBoardController extends Controller{
    public Response getScoreBoards(User user) {
        List<ScoreBoard> userScoreBoards= new ScoreBoardDAO().getList(user.getId());
        if(userScoreBoards==null) ConstResponses.JSON_NOT_FOUND_RESPONSE(user.getName()+" scores not in database");
        try {
            //String data=getObjectMapper().writeValueAsString(userScoreBoards);
            String data= getObjectMapper().writer().writeValueAsString(userScoreBoards);
            return ConstResponses.JSON_OK_RESPONSE(data);
        } catch (JsonProcessingException e) {
            return ConstResponses.JSON_CONVERT_ERROR(e.getMessage());
        }
    }
}
