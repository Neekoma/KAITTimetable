package com.nicholas.timetable.models;

import java.util.ArrayList;
import java.util.List;

public class DayOfWeek {

    private String dayName;
    private List<Pair> pairs;

    public DayOfWeek(){
        pairs = new ArrayList<>();
    }

    public void setDayName(String dayName){this.dayName = dayName;}
    public String getDayName(){return dayName;}
    public void setPairs(List<Pair> pairs){this.pairs = pairs;}
    public List<Pair> getPairs(){return pairs;}




}
