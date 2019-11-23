package com.nicholas.timetable.viewmodels;

public class ReplacementsViewModel {

    private static ReplacementsViewModel instance;

    public static ReplacementsViewModel getInstance(){
        if(instance == null)
            instance = new ReplacementsViewModel();
        return instance;
    }

    public void setData(Object x){

    }


}
