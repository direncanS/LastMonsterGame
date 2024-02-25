package com.example.server;

import com.example.enums.Method;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Locale;


@Getter
@Setter(AccessLevel.PROTECTED)
public class Request {
    private Method method;
    private String pathname;
    private String authorization;
    private String params;
    private String contentType;
    private int contentLength;
    private String body = "";
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private final String CONTENT_TYPE = "Content-Type: ";
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private final String CONTENT_LENGTH = "Content-Length: ";

    public Request(BufferedReader inputStream) {
        buildRequest(inputStream);
    }

    private void buildRequest(BufferedReader inputStream) {
        try {
            String line = inputStream.readLine();
           // System.out.println("Line: "+line);
           // System.out.println(line);
            if (line != null) {
                String[] splitFirstLine = line.split(" ");
                 Boolean hasParams = splitFirstLine[1].indexOf("?") != -1;


                setMethod(getMethodFromInputLine(splitFirstLine));
                setPathname(getPathnameFromInputLine(splitFirstLine, hasParams));
                setParams(getParamsFromInputLine(splitFirstLine, hasParams));

                while (!line.isEmpty()) {
                    line = inputStream.readLine();
                    if (line.startsWith(CONTENT_LENGTH)) {
                        setContentLength(getContentLengthFromInputLine(line));
                    }
                    if (line.startsWith(CONTENT_TYPE)) {
                        setContentType(getContentTypeFromInputLine(line));
                    }
                    if(line.startsWith("Authorization")){
                        setAuthorization(getAuthorizationFromInputLine(line));
                    }
                }

                if (getMethod() == Method.POST || getMethod() == Method.PUT) {
                    int asciChar;
                    for (int i = 0; i < getContentLength(); i++) {
                        asciChar = inputStream.read();
                        String body = getBody();
                        setBody(body + ((char) asciChar));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getAuthorizationFromInputLine(String  line) {
        String[] splitLine=line.split(" ");
        return splitLine[2].split("-")[0];
    }

    private Method getMethodFromInputLine(String[] splitFirstLine) {
        return Method.valueOf(splitFirstLine[0].toUpperCase(Locale.ROOT));
    }

    private String getPathnameFromInputLine(String[] splitFirstLine, Boolean hasParams) {
        if (hasParams) {
            return splitFirstLine[1].split("\\?")[0];
        }

        return splitFirstLine[1];
    }


    private String getParamsFromInputLine(String[] splittedFirstLine, Boolean hasParams) {
        if (hasParams) {
            return splittedFirstLine[1].split("\\?")[1];
        }

        return "";
    }

    private Integer getContentLengthFromInputLine(String line) {
        return Integer.parseInt(line.substring(CONTENT_LENGTH.length()));
    }

    private String getContentTypeFromInputLine(String line) {
        return line.substring(CONTENT_TYPE.length());
    }
}
