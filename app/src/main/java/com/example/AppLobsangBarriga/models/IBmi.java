package com.example.AppLobsangBarriga.models;

import java.util.Date;

public interface IBmi {
    long getId();
    Date getDate();
    double getWeight();
    double getCalculatedBmi();
    long getUserId();
}
