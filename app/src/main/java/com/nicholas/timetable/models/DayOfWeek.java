package com.nicholas.timetable.models;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DayOfWeek {

    private String dayName;
    @SerializedName("number")
    private int number;
    @SerializedName("type")
    private int type;
    @SerializedName("lessons")
    private List<Lesson> lessons;


    public void setDayName(String dayName){this.dayName = dayName;}
    public String getDayName(){return dayName;}
    public List<Lesson> getLessons(){return lessons;}

}
