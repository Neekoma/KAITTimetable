package com.nicholas.timetable.time;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Timer extends AsyncTask<Void, String, String> {

    private static final int TIME_FOR_SLEEP = 3500;
    private static final String TYPE_TO_LESSON = "На занятие";
    private static final String TYPE_FROM_LESSON = "На перемену";
    private static final String TYPE_TO_LUNCH = "На обед";
    private static final int FIRST_CALL = 0;
    private static final int LAST_CALL = 1;
    private static final String CALLS_ASSET_FILENAME = "calls_time.json";
    private Context mContext;
    private TextView mCallTv, mGroupsTv;
    private ArrayList<Pair> pairs;
    private DateFormat dateFormat;



    public Timer(TextView callTv, TextView groupsTv){
        mCallTv = callTv;
        mGroupsTv = groupsTv;
        dateFormat = new SimpleDateFormat("HH:mm");
        mContext = callTv.getContext();
        loadCallsAsset();
    }

    public void start(){
        this.execute();
    }


    private void update(){
        Date currentDate = new Date();
//        try{currentDate = dateFormat.parse("11:10");}
//        catch (ParseException e){}
        if(currentDate.getDay() >= 6) {
            mCallTv.setText("Следующий звонок в понедельник в 9:00");
            mGroupsTv.setText("");
            return;
        }

        if(checkFirstAndLastCall(currentDate) == FIRST_CALL){
            mCallTv.setText("Следующий звонок в 9:00");
            return;
        }
        else if(checkFirstAndLastCall(currentDate) == LAST_CALL)
        {
            mCallTv.setText("Следующий звонок завтра в 9:00");
            return;
        }


        for(int i = 0; i < pairs.size(); i++){
            Date date1 = null;
            Date date2 = null;
            try{date1 = dateFormat.parse(pairs.get(i).getTime());
                date2 = dateFormat.parse(pairs.get(i + 1).getTime());
            }
            catch (ParseException e){e.printStackTrace();}
            catch (IndexOutOfBoundsException e1){}
            if(currentDate.compareTo(date1) == 1 && currentDate.compareTo(date2) == -1){
                mCallTv.setText(String.format("Следующий звонок в %s (%s)", pairs.get(i + 1).getTime(), pairs.get(i + 1).getType()));
                if(pairs.get(i +1).getGroupsAtLunch() != null)
                    mGroupsTv.setText(String.format("Обедают группы: %s", pairs.get(i + 1).getGroupsAtLunch()));
                else{
                    mGroupsTv.setText("");
                }
                return;
            }
        }
    }
    private int checkFirstAndLastCall(Date currentDate){
        Date firstCall = null;
        Date lastcall = null;
        try {
            firstCall = dateFormat.parse("9:00");
            lastcall = dateFormat.parse("18:00");
        }
        catch (ParseException e){}
        if(currentDate.compareTo(firstCall) == -1)
            return FIRST_CALL;
        else if(currentDate.compareTo(lastcall) == 1)
            return LAST_CALL;
        return -1;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            while (true) {
                publishProgress();
                Thread.currentThread().sleep(TIME_FOR_SLEEP);
            }
        }
        catch (InterruptedException e){

        }
       return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        update();
    }

    private void loadCallsAsset(){
        if(mContext != null){
            InputStreamReader reader = null;
            try{ reader = new InputStreamReader(mContext.getAssets().open(CALLS_ASSET_FILENAME));}
            catch (IOException e){e.printStackTrace();}
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Pair>>(){}.getType();
            pairs = gson.fromJson(reader, type);
        }
    }



}
