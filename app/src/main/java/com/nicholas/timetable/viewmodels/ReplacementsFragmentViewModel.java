package com.nicholas.timetable.viewmodels;

import android.util.Log;


public class ReplacementsFragmentViewModel implements TimetableViewModel{

    private static final String TAG = "ReplacementsViewModel";

    private static ReplacementsFragmentViewModel instance;


    private ReplacementsFragmentViewModel(){}

    public static ReplacementsFragmentViewModel getInstance(){
        if (instance == null)
            instance = new ReplacementsFragmentViewModel();
        return instance;
    }

    @Override
    public boolean load() {
        Log.d(TAG, "load");
        return false;
    }

    @Override
    public boolean update() {
        return false;
    }
}
