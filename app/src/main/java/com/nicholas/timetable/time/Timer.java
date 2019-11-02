package com.nicholas.timetable.time;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

public class Timer extends AsyncTask<Void, Void, Void> {

    private static final String TAG = "Timer";
    private Context context;
    private static final int TIME_FOR_SLEEP = 1000;
    private static Timer instance;

    private Time time;

    private Timer(Context context, TextView view) {
        this.context = context;
        time = new Time(context, view);
        this.doInBackground();
    }

    public Timer getInstance(Context context, TextView view) {
        if (instance == null)
            instance = new Timer(context, view);
        return instance;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        while (true) {
            try {
                Thread.currentThread().sleep(TIME_FOR_SLEEP);
                time.update();
            }
            catch (InterruptedException e){
                e.printStackTrace();
                return null;
            }
        }
    }


}
