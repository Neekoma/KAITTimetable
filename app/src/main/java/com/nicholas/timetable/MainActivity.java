package com.nicholas.timetable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nicholas.timetable.fragments.BottomNavigationDrawerFragment;
import com.nicholas.timetable.fragments.CallsFragment;
import com.nicholas.timetable.users.Auth;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FloatingActionButton homeFab;
    private ViewGroup contentContainer;
    private BottomAppBar appBar;
    private View timerFragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initWidgets();
        initCallsFragment();
        appBar = findViewById(R.id.bottom_app_bar);
        setSupportActionBar(appBar);
        Auth.fillUsersAuthData();
    }


    private void initWidgets() {
        contentContainer = findViewById(R.id.content_fragment_container);
        contentContainer.setNestedScrollingEnabled(true);
        homeFab = findViewById(R.id.home_fab);
        timerFragmentContainer = findViewById(R.id.timerFragment_container);
        timerFragmentContainer.setVisibility(View.GONE);
        homeFab.setOnClickListener(this);
    }

    //Ициализировать фрагмент с расписанием звонков
    private void initCallsFragment() {
        CallsFragment fragment = new CallsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.timerFragment_container, fragment);
        transaction.commit();
    }

    public void showCallsFragment(boolean state){
        if(state)
            timerFragmentContainer.setVisibility(View.VISIBLE);
        else
            timerFragmentContainer.setVisibility(View.GONE);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       //getMenuInflater().inflate(R.menu.bottom_appbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                BottomNavigationDrawerFragment drawer = new BottomNavigationDrawerFragment();
                drawer.show(getSupportFragmentManager(), drawer.getTag());
                return true;
            case R.id.action_settings:
                Navigation.findNavController(this, contentContainer.getId()).navigate(R.id.settingsFragment);
                showCallsFragment(false);
                return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_fab:
                Navigation.findNavController(this, contentContainer.getId()).navigate(R.id.homeFragment);
                showCallsFragment(false);
                break;

        }
    }


}
