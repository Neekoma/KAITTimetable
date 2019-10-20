package com.nicholas.timetable.viewmodels;

import java.util.ArrayList;

public class TimetableFragmentViewModel {

    private ArrayList<String> groupsTitles;


    public TimetableFragmentViewModel(){
        groupsTitles = new ArrayList<>();
        groupsTitles.add("П-311");
        groupsTitles.add("ПиВ-211");
    }


    public ArrayList<String> getGroupsTitles(){return groupsTitles;}

}
