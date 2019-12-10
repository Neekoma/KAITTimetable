package com.nicholas.timetable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentTransaction;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nicholas.timetable.fragments.CallsFragment;


public class MainActivity extends AppCompatActivity {

    private FloatingActionButton homeFab;
    private ViewGroup contentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initWidgets();
        initCallsFragment();
    }


    private void initWidgets(){
        contentContainer = findViewById(R.id.content_fragment_container);
        contentContainer.setNestedScrollingEnabled(true);
        homeFab = findViewById(R.id.home_fab);

    }

    //Ициализировать фрагмент с расписанием звонков
    private void initCallsFragment(){
        CallsFragment fragment = new CallsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.timerFragment_container, fragment);
        transaction.commit();
    }


}
