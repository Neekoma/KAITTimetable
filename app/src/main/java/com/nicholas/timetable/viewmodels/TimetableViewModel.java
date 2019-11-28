package com.nicholas.timetable.viewmodels;

import com.nicholas.timetable.adapters.TimetableRecyclerViewAdapter;
import com.nicholas.timetable.models.DayOfWeek;

import java.util.HashMap;
import java.util.List;

public class TimetableViewModel {

    private static TimetableViewModel instance;

    private HashMap<String, List<DayOfWeek>> days;
    private TimetableRecyclerViewAdapter adapter;
    private TimetableViewModel(){
        adapter = new TimetableRecyclerViewAdapter();
    }

    public static TimetableViewModel getInstance(){
        if (instance == null)
            instance = new TimetableViewModel();
        return instance;
    }

    public TimetableRecyclerViewAdapter getRecyclerViewAdapter(){
        return adapter;
    }


}
