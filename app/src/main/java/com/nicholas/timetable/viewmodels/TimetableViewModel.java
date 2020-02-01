package com.nicholas.timetable.viewmodels;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.nicholas.timetable.MainActivity;
import com.nicholas.timetable.lists.Timetable.TimetableRecyclerViewAdapter;
import com.nicholas.timetable.models.DayOfWeek;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimetableViewModel{

    private static TimetableViewModel instance;

    private TimetableViewModel(){}

    private HashMap<String, List<DayOfWeek>> groups;

    private HashMap<String, List<DayOfWeek>> selectedGroup;

    private TimetableRecyclerViewAdapter timetableAdapter;

    private String[] groupNames;

    private String currentGroupName = null;


    public static TimetableViewModel getInstance(){
        if (instance == null) {
            instance = new TimetableViewModel();
        }
        return instance;
    }



    public String[] getGroupNames(){return groupNames;}


    public void setGroups(HashMap<String, List<DayOfWeek>> groups){
        this.groups = groups;
        groupNames = new String[groups.size()];
        int i = 0;
        for(Map.Entry<String, List<DayOfWeek>> entry : groups.entrySet()){
            groupNames[i] = entry.getKey();
            i++;
        }
    }


    public TimetableRecyclerViewAdapter getAdapter(Context context){
        if(timetableAdapter == null)
            timetableAdapter = new TimetableRecyclerViewAdapter(context);
        return timetableAdapter;
    }

    public HashMap<String, List<DayOfWeek>> getGroups(){return groups;}

    public void setCurrentGroupName(String groupName){
        currentGroupName = groupName;
    }
    public String getCurrentGroupName(){return currentGroupName;}




}
