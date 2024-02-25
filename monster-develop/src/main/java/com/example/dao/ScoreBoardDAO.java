package com.example.dao;

import com.example.model.Card;
import com.example.model.ScoreBoard;
import com.example.model.User;
import lombok.Getter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScoreBoardDAO extends AbstractDAO implements DaoI<ScoreBoard> {


    @Override
    public String create(ScoreBoard entity) {
        String insertStmt = "insert into score_board(player1ELO, player2ELO, winnerELO, player1_id, player2_id, winner_id, player1Card_id, player2Card_id, game_score, data_, player1Damage, player2Damage)values(?,?,?,?,?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement preparedStatement = getConn().prepareStatement(insertStmt);
            preparedStatement.setInt(1, entity.getPlayer1ELO());
            preparedStatement.setInt(2, entity.getPlayer2ELO());
            preparedStatement.setInt(3, entity.getWinnerELO());
            preparedStatement.setInt(4, entity.getPlayer1().getId());
            preparedStatement.setInt(5, entity.getPlayer2().getId());
            preparedStatement.setInt(6, entity.getWinner().getId());
            preparedStatement.setString(7, entity.getPlayer1Cards().getId());
            preparedStatement.setString(8, entity.getPlayer2Cards().getId());
            preparedStatement.setDouble(9, entity.getGame_score());
            preparedStatement.setString(10, entity.getData());
            preparedStatement.setDouble(11, entity.getPlayer1Damage());
            preparedStatement.setDouble(12, entity.getPlayer2Damage());
            preparedStatement.execute();
            preparedStatement.close();
            closeConnection();
            return "SUCCEED";
        } catch (
                SQLException e) {
            return e.getMessage();
        }
    }

    @Override
    public ScoreBoard read(int id) {
        return null;
    }


    public List<ScoreBoard> getList(int id) {
         List<ScoreBoard> scoreBoards=null;
         String sql="select * from score_board where player1_id=? or player2_id=?";
        try {
            PreparedStatement pst=getConn().prepareStatement(sql);
            pst.setInt(1,id);
            pst.setInt(2,id);
            ResultSet rs = pst.executeQuery();
            scoreBoards=new ArrayList<>();
            while(rs.next()){
                ScoreBoard scoreBoard=new ScoreBoard();
                scoreBoard.setId(rs.getInt("id"));
                scoreBoard.setPlayer1ELO(rs.getInt("player1ELO"));
                scoreBoard.setPlayer2ELO(rs.getInt("player2ELO"));
                scoreBoard.setWinnerELO(rs.getInt("winnerELO"));
                scoreBoard.setPlayer1(new UserDAO().read(rs.getInt("player1_id")));
                scoreBoard.setPlayer2(new UserDAO().read(rs.getInt("player2_id")));
                scoreBoard.setWinner(new UserDAO().read(rs.getInt("winner_id")));
                // scoreBoard.getPlayer1Cards(rs.getString("player1Card_id"));
                //scoreBoard.setPlayer2Cards(rs.getString("player2Card_id"));
                scoreBoard.setGame_score(rs.getDouble("game_score"));
                scoreBoard.setData(rs.getString("data_"));
                scoreBoard.setPlayer1Damage(rs.getDouble("player1Damage"));
                scoreBoard.setPlayer2Damage(rs.getDouble("player2Damage"));
                scoreBoards.add(scoreBoard);
            }
            rs.close();
            closeConnection();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        return scoreBoards;
    }

    public void deleteAll() {
        try{

            PreparedStatement pst=getConn().prepareStatement("delete from score_board");
            pst.execute();
            System.out.println("LOGs cleared\nDetails:\n-----------------");
            System.out.println("        score_board cleared");
            pst=getConn().prepareStatement("delete from users");
            pst.execute();
            System.out.println("        users cleared");
            pst=getConn().prepareStatement("delete from trading_deal");
            pst.execute();
            System.out.println("        trading_deal cleared");
            closeConnection();

        }catch (SQLException ex){
            System.out.println(ex.getMessage());

        }
    }
}
