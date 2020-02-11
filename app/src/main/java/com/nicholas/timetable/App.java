package com.nicholas.timetable;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class App extends Application {

    public static final String TIMETABLE_CHANGES_CHANNEL_ID = "NotificationTimetableChangesChannelID";
    public static final String TIMETABLE_CHANGES_CHANNEL_NAME = "Изменения в расписании";

    public static Context context;
    public static boolean isOnline = false;

    @Override
    public void onCreate(){
        super.onCreate();
        createNotificationChannel();
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(
                    TIMETABLE_CHANGES_CHANNEL_ID,
                    TIMETABLE_CHANGES_CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
