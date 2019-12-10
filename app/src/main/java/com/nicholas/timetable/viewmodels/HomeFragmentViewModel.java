package com.nicholas.timetable.viewmodels;

public class HomeFragmentViewModel {

    private static HomeFragmentViewModel instance;

    public static boolean logged = false;

    public static String login = "";

    private HomeFragmentViewModel(){

    }

    public static HomeFragmentViewModel getInstance(){
        if(instance == null)
            instance = new HomeFragmentViewModel();
        return instance;
    }



}
