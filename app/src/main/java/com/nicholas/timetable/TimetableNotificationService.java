package com.nicholas.timetable;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.nicholas.timetable.networking.RequestSender;
import com.nicholas.timetable.networking.Sendable;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.nicholas.timetable.App.CHANNEL_ID;

public class TimetableNotificationService extends Service implements Sendable {

    @Override
    public void onCreate(){
        super.onCreate();
    }

    public void onDestroy(){
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        Observable<Object> dispose = dataSource().
                subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Доступно обновленное расписание")
                .setSmallIcon(R.drawable.logo)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void getSendCallbackResult(boolean result) {
        if(result){
            TimetableBinder.writeNewTimetableJson(getApplicationContext(), RequestSender.getInstance().getLastJson());
        }
        stopSelf(1);
    }


    private PendingIntent createOnDismissedIntent(Context context, int notificationId) {
        Intent intent = new Intent(context, NotificationDismissedReceiver.class);
        intent.putExtra("com.my.app.notificationId", notificationId);

        PendingIntent pendingIntent =
                PendingIntent.getBroadcast(context.getApplicationContext(),
                        notificationId, intent, 0);
        return pendingIntent;
    }

    private Observable<Object> dataSource(){
        return null;
    }

}
