package com.nicholas.timetable.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.nicholas.timetable.App;
import com.nicholas.timetable.R;

public class NotificationReceiverService extends FirebaseMessagingService {

    private static final String TAG = "NotificationReceiver";

    public NotificationReceiverService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        sendTimetableChangesNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
    }


    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);
    }

    private void sendTimetableChangesNotification(String title, String messageBody) {
        Notification notification = null;
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification = new NotificationCompat.Builder(this, App.TIMETABLE_CHANGES_CHANNEL_ID)
                    .setSmallIcon(R.drawable.logo)
                    .setContentTitle(title)
                    .setContentText(messageBody)
                    .setAutoCancel(true)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                    .setLights(Color.RED, 3000, 3000)
                    .setSound(Uri.parse("uri://sadfasdfasdf.mp3"))
                    .build();
        } else {
            notification = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.logo)
                    .setContentTitle(title)
                    .setContentText(messageBody)
                    .setAutoCancel(true)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                    .setLights(Color.RED, 3000, 3000)
                    .setSound(Uri.parse("uri://sadfasdfasdf.mp3"))
                    .build();
        }
        notificationManager.notify(0, notification);
    }

}
