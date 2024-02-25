package com.example.util;

import com.example.enums.ContentType;
import com.example.enums.HttpStatus;
import com.example.server.Response;

public class ConstResponses {

    public static Response JSON_CONVERT_ERROR(String errMSG){
        return new Response(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ContentType.JSON,
                "{\"error\": \""+errMSG+"\", \"data\": null }");
    }
    public static Response JSON_OK_RESPONSE(String data){
        return new Response(
                HttpStatus.OK,
                ContentType.JSON,
                "{\"error\": \"null\", \"data\":  "+data+"}");
    }
    public static Response JSON_CREATE_RESPONSE(String data){
        return new Response(
                HttpStatus.CREATED,
                ContentType.JSON,
                "{\"error\": \"null\", \"data\":  "+data+"}");
    }

    public static Response JSON_INTERNAL_SERVER_RESPONSE(String errMSG){
        return new Response(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ContentType.JSON,
                "{\"error\": \""+errMSG+"\", \"data\":  null}");
    }

    public static Response JSON_BAD_REQUEST_RESPONSE(String data){
        return new Response(
                HttpStatus.BAD_REQUEST,
                ContentType.JSON,
                "{\"error\": \""+data+"\", \"data\": null}");
    }
    public static Response AUTHORIZATION_FAIL_RESPONSE(String errMSG){
        return new Response(
                HttpStatus.BAD_REQUEST,
                ContentType.JSON,
                "{\"error\": \""+errMSG+"\", \"data\": null}");
    }
    public static Response JSON_NOT_VALID_USER_RESPONSE(String userName){
        return new Response(
                HttpStatus.BAD_REQUEST,
                ContentType.JSON,
                "{\"error\": \""+userName+" is Not Valid User\", \"data\": null}");
    }
    public static Response JSON_NOT_FOUND_RESPONSE(String data){
        return new Response(HttpStatus.NOT_FOUND, ContentType.JSON, "{ \"error\": \""+data+"\", \"data\": null }");
    }

}
