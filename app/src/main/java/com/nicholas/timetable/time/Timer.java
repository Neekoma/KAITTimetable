package com.nicholas.timetable.time;



public class Timer implements Runnable {

    private static final String TAG = "Timer";

    private static Timer instance;

    private static final int TIME_FOR_SLEEP = 1000;
    private Thread timerThread;


    private Timer() {

    }

    public static Timer getInstance() {
        if (instance == null)
            instance = new Timer();
        return instance;
    }

    public void startTimer() {
        new Time();
        timerThread = new Thread(this);
        timerThread.run();
    }

    @Override
    public void run() {
        try {
            while (!timerThread.isInterrupted()) {
                timerThread.sleep(TIME_FOR_SLEEP);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
