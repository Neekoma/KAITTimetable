package com.nicholas.timetable.models;

import java.util.ArrayList;

public class Timetable {

    private ArrayList<DayOfWeek> days;

    public Timetable(){
        days = new ArrayList<>();
    }

    public ArrayList<DayOfWeek> getDays(){return days;}

}
