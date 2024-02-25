package com.example.server;

import com.example.model.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Sessions {
    private static final List<User> userList=new ArrayList<>();;



    public synchronized static void  addUserToSession(User user){
        if(!isUserHasSession(user))
            userList.add(user);
        System.out.println(user+" User session added");
    }

    public synchronized static void closeUserSession(User user){
        userList.remove(user);
        System.out.println(user+" User session closed");
    }
    public synchronized static boolean  isUserHasSession(User user){
        return userList.stream().filter(usr->usr.equals(user)).findFirst().orElse(null)!=null;
    }

//    public synchronized static User  findCard(String id){
//        for (User user:userList) {
//             //user.getDeck().getMonsterCards().stream().filter()
//        }
//        return null;
//    }




    public synchronized static User  isValidUser(String authorize){
        for (User user:userList) {
            if (user.getUsername().equalsIgnoreCase(authorize)) return user;
        }
        return null;
    }


    public synchronized static boolean  isAdmin(String authorize){
        return authorize.equalsIgnoreCase("admin");
    }
}
