package com.example.controller;

import com.example.dao.UserDAO;
import com.example.enums.ContentType;
import com.example.enums.HttpStatus;
import com.example.model.User;
import com.example.server.Response;
import com.example.server.Sessions;
import com.example.util.ConstResponses;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class UserController extends Controller {

    @Setter
    @Getter
    private UserDAO userDao;

    public UserController() {
        setUserDao(new UserDAO());
    }


    public Response getUserCards(String auth) {

        try {
            User user = Sessions.isValidUser(auth);
            if (user == null)
                return new Response(
                        HttpStatus.BAD_REQUEST,
                        ContentType.JSON,
                        "{ \"error\": \"User Not Have Any Session\", \"data\": null }");
            String jsonUserList = getObjectMapper().writeValueAsString(user.getAllCardList());
            return new Response(
                    HttpStatus.OK,
                    ContentType.JSON,
                    "{ \"data\": " + jsonUserList + ", \"error\": null }"
            );
        } catch (JsonProcessingException e) {
            return new Response(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ContentType.JSON,
                    "{ \"error\": \"Internal Server Error\", \"data\": null }"
            );
        }
    }


    // POST /user
    public Response createUser(String body) {
        try {
            System.out.println(body);
            User newUser = getObjectMapper().readValue(body, User.class);//convert json to user
            String op_result = getUserDao().create(newUser);
            if (op_result.equals("SUCCEED"))
                return ConstResponses.JSON_CREATE_RESPONSE(body);
            else return ConstResponses.JSON_BAD_REQUEST_RESPONSE(op_result);
        } catch (JsonProcessingException e) {
            return ConstResponses.JSON_CONVERT_ERROR("ERR CODE: " + e.getMessage());
        }
    }

    public Response isHave(String body) {
        try {
            User newUser = getObjectMapper().readValue(body, User.class);

            User dbUSER = getUserDao().getUserIfUserInDatabase(newUser);
            if (dbUSER != null) {
                Sessions.addUserToSession(dbUSER);
                return new Response(HttpStatus.OK, ContentType.JSON, body);
            } else
                return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, "{ \"error\": \"" + newUser.getUsername() + " not in DB\"\", \"data\": null }");
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, "{ \"error\": \"JSON Error. \", \"data\": null }");
        }
    }

    public Response update(String body, User currentUser) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            User updatedUser = objectMapper.readValue(body, User.class);
            if (currentUser.getUsername().equals(updatedUser.getUsername())) {
                // Burada veritabanı güncelleme işlemleri yap

                return ConstResponses.JSON_OK_RESPONSE("User updated successfully");
            } else {
                return ConstResponses.JSON_BAD_REQUEST_RESPONSE("Unauthorized user update attempt");
            }
        } catch (Exception e) {
            return ConstResponses.JSON_CONVERT_ERROR(e.getMessage());
        }
    }

    public Response deleteUser(int id) {
        try {
            boolean isDeleted = userDao.delete(id);
            if (isDeleted) {
                return ConstResponses.JSON_OK_RESPONSE("User successfully deleted");
            } else {
                return ConstResponses.JSON_NOT_FOUND_RESPONSE("User not found");
            }
        } catch (Exception e) {
            return ConstResponses.JSON_INTERNAL_SERVER_RESPONSE("Internal server error: " + e.getMessage());
        }
    }

}
