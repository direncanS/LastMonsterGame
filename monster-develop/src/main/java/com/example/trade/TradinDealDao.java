package com.example.trade;

import com.example.dao.AbstractDAO;
import com.example.dao.UserDAO;
import com.example.model.Card;
import com.example.model.User;
import com.example.server.Response;
import com.example.server.Sessions;
import com.example.util.ConstResponses;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TradinDealDao extends AbstractDAO implements TradingDeal_Interface {

    @Override
    public Response create(TradingDealEntity tradeDeal, int user_id) {
        String insertStmt = "insert into trading_deal(id,cardToTrade,card_type,minimumDamage,user_id)values(?,?,?,?,?);";
        try {
            PreparedStatement preparedStatement = getConn().prepareStatement(insertStmt);
            preparedStatement.setString(1, tradeDeal.getId());
            preparedStatement.setString(2, tradeDeal.getCardToTrade_id());
            preparedStatement.setString(3, tradeDeal.getType());
            preparedStatement.setDouble(4, tradeDeal.getMinimumDamage());
            preparedStatement.setDouble(5, user_id);
            preparedStatement.execute();
            closeConnection();
            return ConstResponses.JSON_CREATE_RESPONSE("trading deal created...");
        } catch (SQLException e) {
            return ConstResponses.JSON_INTERNAL_SERVER_RESPONSE(e.getMessage());
        }
    }


    @Override
    public List<TradingDealEntity> check(User user) {
        List<TradingDealEntity> deals = null;
        String sql = "select * from trading_deal where user_id=?";
        try {
            PreparedStatement pst = getConn().prepareStatement(sql);
            pst.setInt(1, user.getId());
            ResultSet rs = pst.executeQuery();
            deals = new ArrayList<>();
            while (rs.next()) {

                TradingDealEntity dealEntity = new TradingDealEntity();
                dealEntity.setId(rs.getString("id"));
                dealEntity.setType(rs.getString("card_type"));
                dealEntity.setCardToTrade_id(rs.getString("cardToTrade"));
                dealEntity.setMinimumDamage(rs.getDouble("minimumDamage"));
                dealEntity.setUser_id(rs.getInt("user_id"));
                deals.add(dealEntity);
            }
            rs.close();
            closeConnection();

        } catch (SQLException e) {
            e.getMessage();
        }
        return deals;
    }


    public TradingDealEntity getByID(String id) {
        String sql = "select * from trading_deal where id=?";
        TradingDealEntity dealEntity = null;
        try {
            PreparedStatement pst = getConn().prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                dealEntity = new TradingDealEntity();
                dealEntity.setId(rs.getString("id"));
                dealEntity.setType(rs.getString("card_type"));
                dealEntity.setCardToTrade_id(rs.getString("cardToTrade"));
                dealEntity.setMinimumDamage(rs.getDouble("minimumDamage"));
                dealEntity.setUser_id(rs.getInt("user_id"));
            }
            rs.close();
            closeConnection();

        } catch (SQLException e) {
            e.getMessage();
        }
        return dealEntity;
    }


    @Override
    public Response delete(String id) {
        String sql = "DELETE FROM trading_deal WHERE id = ?;";
        try (PreparedStatement pst = getConn().prepareStatement(sql)) {
            pst.setString(1, id); // String ID'yi Integer'a dönüştür
            pst.executeUpdate();
            closeConnection();
            return ConstResponses.JSON_OK_RESPONSE("Trading deal DELETED");
        } catch (SQLException e) {
            e.getMessage();
            return ConstResponses.JSON_INTERNAL_SERVER_RESPONSE(e.getMessage());
        }
    }


    public Response trade(User buyerUser, String buyerCardId, TradingDealEntity tradingDeal) {
        if (buyerUser.getId() == tradingDeal.getUser_id()) {
            return ConstResponses.JSON_BAD_REQUEST_RESPONSE("Cannot trade with yourself");
        }
        delete(String.valueOf(tradingDeal.getId())); //clean log
        User sellerUser = new UserDAO().read(tradingDeal.getUser_id());
        Card sellerCard = sellerUser.getCardInPackageByTOKEN(tradingDeal.getCardToTrade_id());
        Card buyerCard = buyerUser.getCardInPackageByTOKEN(buyerCardId);
        if (sellerCard == null || buyerCard == null)
            return ConstResponses.JSON_NOT_FOUND_RESPONSE("Card not found in user package");
        //remove card from package
        buyerUser.remove_a_card_from_package(buyerCard);
        sellerUser.remove_a_card_from_package(sellerCard);
        //add card to user package
        buyerUser.getPackages().get(0).addCard(sellerCard);
        sellerUser.getPackages().get(0).addCard(buyerCard);
        return ConstResponses.JSON_OK_RESPONSE("cards changed");
    }
}
