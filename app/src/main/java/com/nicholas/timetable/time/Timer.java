package com.nicholas.timetable.time;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nicholas.timetable.R;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * @version 1.0
 * @date 21.11.2019
 */
public class Timer extends AsyncTask<Void, String, String> {

    private static final int TIME_FOR_SLEEP = 1000 * 8; // Проверка раз в 8 секунд
    private static final int FIRST_CALL = 0; // Если время до первого звонка
    private static final int LAST_CALL = 1; // Если время после последнего звонка
    private static final String CALLS_ASSET_FILENAME = "calls_time.json";


    private static final String TYPE_TO_LESSON = "На занятие";
    private static final String TYPE_FROM_LESSON = "На перемену";
    private static final String TYPE_AT_LUNCH = "На обед";
    private static final String TYPE_LAST = "Последний";

    private Context mContext;
    private TextView mCallTv, mGroupsTv;
    private ArrayList<Pair> pairs;
    private DateFormat dateFormat;


    private ViewGroup mContainer;



    private static final String[] CALLS_TIME = new String[] {"9:00", "10:40", "12:50", "14:50", "16:30"};

    public Timer(Context context, ViewGroup container, TextView callTv, TextView groupsTv){
        mContext = context;
        mCallTv = callTv;
        mGroupsTv = groupsTv;
        dateFormat = new SimpleDateFormat("HH:mm");
        mContainer = container;
        loadCallsAsset();
    }

    public void start(){
        mGroupsTv.setText("");
        this.execute();
        Log.d("DEBUG", "Timer thread started");
    }


    public void update(){
        Log.d("DEBUG", "publish results");
        Date currentDate = new Date();
        if(currentDate.getDay() == 6 || currentDate.getDay() == 0) {
            mCallTv.setText(mContext.getString(R.string.nextCallInMonday));
            mGroupsTv.setText("");
            return;
        }

        String currentTimeStr = dateFormat.format(currentDate);
        Date currentTime = null;
        try{currentTime = dateFormat.parse(currentTimeStr);}
        catch (ParseException e){e.printStackTrace();}

        if(checkFirstAndLastCall(currentTime) == FIRST_CALL){
            mCallTv.setText(mContext.getString(R.string.nextCallInNine));
            return;
        }
        else if(checkFirstAndLastCall(currentTime) == LAST_CALL)
        {
            if(currentDate.getDay() >= 5) // Если сегодня пятница или суббота
                mCallTv.setText(mContext.getString(R.string.nextCallInMonday));
            else
                mCallTv.setText(mContext.getString(R.string.nextCallInTomorrow)); // Если сегодня будний день или воскресенье
            return;
        }

        for(int i = 0; i < pairs.size(); i++){
            mGroupsTv.setText("");
            Date date1 = null;
            Date date2 = null;
            try{date1 = dateFormat.parse(pairs.get(i).getTime());
                date2 = dateFormat.parse(pairs.get(i + 1).getTime());
            }
            catch (ParseException e){e.printStackTrace();}
            catch (IndexOutOfBoundsException e1){}
            if(currentTime.compareTo(date1) == 1 && currentTime.compareTo(date2) == -1 || currentTime.compareTo(date2) == 0){
                mCallTv.setText(String.format("Следующий звонок в %s\n(%s)", pairs.get(i + 1).getTime(), pairs.get(i + 1).getType()));
                // Фоновое изображение (switch не работает)
                if(pairs.get(i + 1).getType().equals(TYPE_TO_LESSON))
                    mContainer.setBackground(mContext.getResources().getDrawable(R.drawable.ucheb));
                else if(pairs.get(i + 1).getType().equals(TYPE_FROM_LESSON))
                    mContainer.setBackground(mContext.getResources().getDrawable(R.drawable.shkol));
                else if(pairs.get(i + 1).getType().equals(TYPE_AT_LUNCH))
                    mContainer.setBackground(mContext.getResources().getDrawable(R.drawable.stol));
                else if(pairs.get(i + 1).getType().equals(TYPE_LAST))
                    mContainer.setBackground(mContext.getResources().getDrawable(R.drawable.time));

                if(pairs.get(i +1).getGroupsAtLunch() != null)
                    mGroupsTv.setText(String.format("Обедают группы: %s", pairs.get(i + 1).getGroupsAtLunch()));
                else if(pairs.get(i).getGroupsAtLunch() != null)
                    mGroupsTv.setText(String.format("Сейчас обедают группы: %s", pairs.get(i).getGroupsAtLunch()));
                else{
                    mGroupsTv.setText("");
                }
                return;
            }
        }
        Log.d("DEBUG", "Timer thread updated");
    }
    private int checkFirstAndLastCall(Date currentDate){
        Date firstCall = null;
        Date lastCall = null;
        try {
            firstCall = dateFormat.parse("9:00");
            lastCall = dateFormat.parse("18:00");
        }
        catch (ParseException e){}
        if(currentDate.compareTo(firstCall) == -1 || currentDate.compareTo(firstCall) == 0)
            return FIRST_CALL;
        else if(currentDate.compareTo(lastCall) == 1 || currentDate.compareTo(lastCall) == 0)
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

//    public static int getCurrentPairNumber(){
//
//        Calendar calendar = Calendar.getInstance();
//
//        if(calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY){
//            DateFormat format = new SimpleDateFormat("HH:mm");
//            try {
//                Date date = format.parse(Integer.toString(calendar.get(Calendar.DATE)));
//
//                for(int i = 0; i < CALLS_TIME.length; i++){
//                    if(date.compareTo(format.parse(CALLS_TIME[i])) == -1)
//
//                }
//
//
//            }
//            catch (ParseException e){
//
//            }
//        }
//        return -1;
//    }



}
