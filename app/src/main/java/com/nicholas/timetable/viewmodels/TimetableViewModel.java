package com.nicholas.timetable.viewmodels;

import android.util.Log;

import androidx.annotation.Nullable;

import com.nicholas.timetable.MainActivity;
import com.nicholas.timetable.models.DayOfWeek;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimetableViewModel{

    private static TimetableViewModel instance;

  //  private static final String FILENAME = "cache.json";


    private TimetableViewModel(){}

    private HashMap<String, List<DayOfWeek>> groups;

    private HashMap<String, List<DayOfWeek>> selectedGroup;


    private String[] groupNames;

    private String currentGroupName = null;


    public static TimetableViewModel getInstance(){
        if (instance == null)
            instance = new TimetableViewModel();
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
        //Вывести список групп (имена)
        for(String j : groupNames)
            Log.d("DEBUG", j);


    }
    public HashMap<String, List<DayOfWeek>> getGroups(){return groups;}

    public void setCurrentGroupName(MainActivity activity, String groupName){
        currentGroupName = groupName;
        activity.refreshTimetable();
    }
    public String getCurrentGroupName(){
        if(currentGroupName == null)
            return "Все группы";
        return currentGroupName;
    }

    public HashMap<String, List<DayOfWeek>> getSelectedGroup(){return selectedGroup;}

    public void setSelectedGroup(String group){
        if(group != "Все группы"){
            selectedGroup= new HashMap<>();
            selectedGroup.put(group, groups.get(group));
        }
        else
            selectedGroup = getGroups();
    }




}
