package com.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

public class Controller {

        @Getter
        private ObjectMapper objectMapper;

        public Controller() {
            this.objectMapper=new ObjectMapper();
        }

}
