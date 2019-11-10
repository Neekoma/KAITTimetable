package com.nicholas.timetable.viewmodels;


import android.util.Log;

public class TimetableFragmentViewModel implements TimetableViewModel{

    private static final String TAG = "TimetableViewModel";

    private static TimetableFragmentViewModel instance;

    private TimetableFragmentViewModel(){

    }

    public static TimetableFragmentViewModel getInstance(){
        if(instance == null)
            instance = new TimetableFragmentViewModel();
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
