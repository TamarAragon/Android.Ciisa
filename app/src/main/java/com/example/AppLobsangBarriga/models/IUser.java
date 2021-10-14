package com.example.AppLobsangBarriga.models;

import java.util.Date;

public interface IUser {
    long getId();
    String getUsername();
    String getFirstName();
    String getLastName();
    Date getDateOfBirth();
    double getHeight();
    String getEmail();
    String getPassword();
}
