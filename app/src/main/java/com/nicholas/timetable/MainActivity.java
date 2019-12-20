package com.nicholas.timetable;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.nicholas.timetable.fragments.CallsFragment;
import com.nicholas.timetable.fragments.TimetableFragment;
import com.nicholas.timetable.networking.RequestSender;
import com.nicholas.timetable.networking.Sendable;


public class MainActivity extends AppCompatActivity implements Sendable {

    private ViewGroup contentContainer;
    private View timerFragmentContainer;
   // private TimetableFragment timetableFragment;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_layout);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        RequestSender.getInstance().update(this);
    }


    private void initWidgets() {
        toolbar = findViewById(R.id.app_toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar(toolbar);
        contentContainer = findViewById(R.id.content_fragment_container);
        contentContainer.setNestedScrollingEnabled(true);
        timerFragmentContainer = findViewById(R.id.timerFragment_container);
    }

    //Ициализировать фрагмент с расписанием звонков
    private void initCallsFragment() {
        CallsFragment fragment = new CallsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.timerFragment_container, fragment);
        transaction.commit();
    }

    private void openTimetableFragment(){

        TimetableFragment timetableFragment = new TimetableFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_fragment_container, timetableFragment);
        transaction.commit();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.action_settings:
                return true;
        }
        return false;
    }

    @Override
    public void getSendCallbackResult(boolean result) {
        if(result){
            setContentView(R.layout.activity_main);
            initWidgets();
            initCallsFragment();
            openTimetableFragment();
        }
    }
}
