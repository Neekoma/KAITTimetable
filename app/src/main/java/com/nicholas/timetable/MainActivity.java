package com.nicholas.timetable;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.nicholas.timetable.fragments.CallsFragment;


public class MainActivity extends AppCompatActivity{

    private ViewGroup contentContainer;
    private View timerFragmentContainer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initWidgets();
        initCallsFragment();
    }


    private void initWidgets() {
        toolbar = findViewById(R.id.app_toolbar);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return true;
    }
}
