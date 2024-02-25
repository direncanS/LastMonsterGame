package com.example.dao;

import com.example.model.APackage;
import com.example.controller.PackageManager;

import java.util.List;

public class PackageDao extends AbstractDAO implements DaoI<APackage>{


    public String createList(APackage[] entityList){
        for (APackage aPackage:entityList) {
            create(aPackage);
        }
        return "SUCCEED";
    }



    @Override
    public String create(APackage entity) {
        PackageManager packageManager=new PackageManager();
        packageManager.addPackage(entity);
        return "SUCCEED";
//        final String sql="insert into package (name, damage) values(?,?)";
//        try {
//            PreparedStatement pst=getConn().prepareStatement(sql);
//            pst.setString(1,entity.getName());
//            pst.setString(2,entity.getDamage());
//            pst.execute();
//            getConn().close();
//            return "SUCCEED";
//        } catch (SQLException e) {
//            closeConnection();
//            return e.getMessage();
//        }
    }



    @Override
    public APackage read(int id) {
        return null;
    }

}
