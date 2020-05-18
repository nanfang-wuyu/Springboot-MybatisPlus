package com.example.springboot.entity;

import lombok.Data;

@Data
public class Passenger {

    String name;

    String cardId;

    boolean isStudent;

    public Passenger(String name, String cardId, boolean isStudent){
        this.name = name;
        this.cardId = cardId;
        this.isStudent = isStudent;
    }

}
