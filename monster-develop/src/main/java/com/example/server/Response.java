package com.example.server;


import com.example.enums.ContentType;
import com.example.enums.HttpStatus;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class Response  {

    @Getter
    private int statusCode;
    private String statusMessage;
    private String contentType;
    private String content;

    public Response(HttpStatus httpStatus, ContentType contentType, String content) {
        setStatusCode(httpStatus.getCode());
        setContentType(contentType.getType());
        setStatusMessage(httpStatus.getMessage());
        setContent(content);
    }

    public String build() {
        return "HTTP/1.1 " + getStatusCode() + " " + getStatusMessage() + "\n" +
                "Content-Type: " + getContentType() + "\n" +
                "Content-Length: " + getContent().length() + "\n" +
                "\n" +
                getContent();
    }

}