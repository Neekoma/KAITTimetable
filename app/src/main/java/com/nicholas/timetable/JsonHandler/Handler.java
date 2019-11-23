package com.nicholas.timetable.JsonHandler;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.nicholas.timetable.models.DayOfWeek;
import com.nicholas.timetable.models.Lesson;
import com.nicholas.timetable.models.Timetable;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Handler {
    private static final String[] groupsName = {"П-311"};
    private static final String[] daysName = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница"};
    private HashMap<String, List<DayOfWeek>> groups; // <имя группы, расписание>


    public Handler(){
        groups = new HashMap<>();
    }

    public void setGroups(String jsonString){
        try {
            JSONObject object = new JSONObject(jsonString); // Получить глобальный json-objeca
            Iterator<String> globalObjectKeys = object.keys(); // Получить список ключей каждой группы (П-311, ИСП-221)
            while(globalObjectKeys.hasNext()){
                int dayCounter = 0;
                List<DayOfWeek> days = new ArrayList<>();
                String groupName = globalObjectKeys.next();
                JSONObject groupObject = object.getJSONObject(groupName);
                Iterator<String> groupKeys = groupObject.keys(); // Получить список ключей каждого дня у каждой группы (понедельник, вторник)
                while(groupKeys.hasNext()){
                    DayOfWeek dayOfWeek = new DayOfWeek();
                    dayOfWeek.setDayName(groupKeys.next());
                    JSONArray daysArray = groupObject.getJSONArray(dayOfWeek.getDayName());
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<DayOfWeek>>(){}.getType();
                    List<DayOfWeek> da = gson.fromJson(daysArray.toString(), type);
                    da.get(0).setDayName(daysName[dayCounter]);
                    days.add(da.get(0));
                    dayCounter++;
                }
                groups.put(groupName, days);
            }

        }
        catch (JSONException e){
            e.printStackTrace();
        }
    printGroupsInLog();
    }

    private void printGroupsInLog(){
        Set<String> keySet = groups.keySet();
        for(String i : keySet){
            Log.d("JSON", i);
            List<DayOfWeek> day = groups.get(i);
            for(DayOfWeek j : day){
                Log.d("JSON", j.getDayName());
                Log.d("JSON", j.getLessons().get(0).getName());
            }
        }

    }

}
