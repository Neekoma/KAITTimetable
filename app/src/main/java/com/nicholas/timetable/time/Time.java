package com.nicholas.timetable.time;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Time {

    private static final String TAG = "Time";

    private CallType callType;

    private DateFormat dateFormat;
    private String nextCallStr;

    public Time(){
        dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    }


    public enum CallType{
        NO_LESSONS, TO_LESSON, FROM_LESSON, TO_LUNCH
    }

}
