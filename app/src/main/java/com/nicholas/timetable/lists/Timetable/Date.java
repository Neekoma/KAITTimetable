package com.nicholas.timetable.lists.Timetable;

import java.util.Calendar;

public abstract class Date {
    public enum DayOfWeek{
        MONDAY(1),
        TUESDAY(2),
        WEDNESDAY(3),
        THURSDAY(4),
        FRIDAY(5);

        private int dayNum;

        DayOfWeek(int i){
         dayNum = i;
        }

        public int getDayNum(){return dayNum;}



    }
}
