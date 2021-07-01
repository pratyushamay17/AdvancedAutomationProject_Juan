package com.planittesting.automation.model.data;

public record ContactData(String forename,
                          String surname,
                          String email,
                          String telephone,
                          String message){
    public ContactData {
        if(forename == null) forename ="";
        if(surname == null) surname ="";
        if(email == null) email ="";
        if(telephone == null) telephone ="";
        if(message == null) message ="";
    }
}