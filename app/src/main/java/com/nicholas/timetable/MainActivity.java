package com.nicholas.timetable;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.messaging.FirebaseMessaging;

import static android.os.Debug.stopMethodTracing;


public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        stopMethodTracing();
        super.onCreate(savedInstanceState);
        App.context = getApplicationContext();
        FirebaseMessaging.getInstance().subscribeToTopic("timetable");
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }


    @Override
    public void onBackPressed() {

    }

}
