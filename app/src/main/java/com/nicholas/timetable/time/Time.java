package com.nicholas.timetable.time;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Time {

    private static final String TAG = "Time";

    private long seconds;
    private int day;


    public Time(){
        Calendar cal = Calendar.getInstance();
        Log.d(TAG, String.format("Time: %s", cal.getTime()));
    }

}
