package com.example.controller;

import com.example.enums.ContentType;
import com.example.enums.HttpStatus;
import com.example.model.APackage;
import com.example.model.Card;
import com.example.model.User;
import com.example.server.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.ArrayList;
import java.util.List;

public class PackageManager extends Controller {
    private static List<APackage> packages=new ArrayList<>();

    public PackageManager() {
    }
    public void addPackage(APackage aPackage){
        PackageManager.packages.add(aPackage);
    }

    public String buy_package(User user){
        if(PackageManager.packages.isEmpty())
            return "no package";

        if(user.getCoin() >= 5){
            user.setCoin(user.getCoin() - 5);
            // getFirst() yerine get(0) kullanılıyor.
            APackage aPackage = PackageManager.packages.get(0);
            user.addPackage(aPackage);
            PackageManager.packages.remove(0);
            return "SUCCESSFUL";
        } else {
            return "user has only " + user.getCoin() + " coins";
        }
    }

    public Response create_new_package(String body){
          try {
              Card[] cards =getObjectMapper().readValue(body, Card[].class);//
              APackage aPackage=new APackage();
              for (Card card: cards) {
                  aPackage.addCard(card);
              }
              addPackage(aPackage);
              return new Response(HttpStatus.CREATED, ContentType.JSON,"{ \"result\": \"SUCCEED\", \"data\": 5 card created in package }");
          } catch (
                  JsonProcessingException e) {
              return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON,"{ \"error\": \"Can't create new package\", \"data\": null }");
          }
      }

}
