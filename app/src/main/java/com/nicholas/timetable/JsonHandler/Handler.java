package com.nicholas.timetable.JsonHandler;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.nicholas.timetable.models.DayOfWeek;
import com.nicholas.timetable.models.Lesson;
import com.nicholas.timetable.models.Pair;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Handler {
    private static final String[] groupsName = {"П-311"};
    private static final String[] daysName = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница"};
    private Map<String, List<DayOfWeek>> groups; // <имя группы, расписание>


    public Handler(){
        groups = new HashMap<>();
    }

    public void setGroups(String jsonString){
        try {
            Gson gson = new Gson();
            JSONObject globalObject = new JSONObject(jsonString);
            Iterator<String> groupsKeys = globalObject.keys();
            while(groupsKeys.hasNext()){
                List<DayOfWeek> days = new ArrayList<>();
                String groupName = groupsKeys.next();
                Iterator<String> groupDaysKeys = globalObject.getJSONObject(groupName).keys();
                while(groupDaysKeys.hasNext()){
                    DayOfWeek dayOfWeek = new DayOfWeek();
                    dayOfWeek.setDayName(groupDaysKeys.next());
                    JSONArray dayArray = globalObject.getJSONObject(groupName).getJSONArray(dayOfWeek.getDayName());
                    for(int i = 0; i < dayArray.length(); i++){
                        JSONObject o = dayArray.getJSONObject(i);
                        Type type = new TypeToken<Pair>(){}.getType();
                        dayOfWeek.getPairs().add((Pair)gson.fromJson(o.toString(), type));
                    }
                    days.add(dayOfWeek);
                }
                groups.put(groupName, days);
            }

        }
        catch (JSONException e){
            e.printStackTrace();
        }
        printGroups();
    }
    private void printGroups(){
        for(Map.Entry<String, List<DayOfWeek>> entry : groups.entrySet()){
            Log.d("JSON", entry.getKey());

            for(DayOfWeek i : entry.getValue()){
                Log.d("JSON", i.getDayName());
                for(Pair j : i.getPairs()){
                    for(Lesson k : j.lessons){
                        Log.d("JSON", Integer.toString(j.number) +" " + k.getName());
                    }
                }
            }
        }

    }


}
