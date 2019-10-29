package com.nicholas.timetable.time;



public class Timer implements Runnable{

    private static final String TAG = "Timer";
    private static final int TIME_FOR_SLEEP = 1000;
    private static Timer instance;

    private Thread timerThread;

    private Time time;

    private Timer(){
        time = new Time();
        timerThread = new Thread(this);
    }

    public Timer getInstance(){
        if(instance == null)
            instance = new Timer();
        return instance;
    }


    @Override
    public void run() {

    }
}
