package com.nicholas.timetable;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {

    public static final String CHANNEL_ID = "TimetableNotificationChannel";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    void createNotificationChannel(){
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O){
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Timetable notification",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(serviceChannel);
        }
    }

}
