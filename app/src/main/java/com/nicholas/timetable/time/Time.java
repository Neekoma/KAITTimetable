package com.nicholas.timetable.time;


import android.content.Context;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Time {

    private static final String TAG = "Time";
    private static final String FILENAME = "calls_time.json";


    private Context context;
    private TextView view;

    private DateFormat dateFormat;
    private String text;

    private List<Pair> pairs;

    public Time(Context context, TextView view){
        this.context = context;
        this.view = view;
        dateFormat = new SimpleDateFormat("HH:mm");
        pairs = getJsonAsset();
    }

    public void update(){
        Date date = new Date();
        Date[] dates = new Date[pairs.size()];
        for(int i = 0; i < pairs.size(); i++){
            try{dates[i] = dateFormat.parse(pairs.get(i).getTime());}
            catch (ParseException e){e.printStackTrace();}
        }
        DateFormat weekend = new SimpleDateFormat("EEEE");
        Date saturday = null;
        try{saturday = weekend.parse("Saturday");}
        catch (ParseException e){e.printStackTrace();}
        if(date.compareTo(saturday) > -1)
            text = "Следующий звонок в понедельник в 9:00";
        else{

        }
    }
    private List<Pair> getJsonAsset(){
        List<Pair> pairs = null;
        Type type = new TypeToken<List<Pair>>(){}.getType();
        Gson gson = new Gson();
        try {
            InputStreamReader reader = new InputStreamReader(context.getAssets().open(FILENAME));
            pairs = gson.fromJson(reader, type);
        }
        catch (IOException e){e.printStackTrace();}
        return pairs;
    }


    public enum CallType{
        NO_LESSONS, TO_LESSON, FROM_LESSON, TO_LUNCH
    }

}
