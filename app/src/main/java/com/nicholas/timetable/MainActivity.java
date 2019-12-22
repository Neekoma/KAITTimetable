package com.nicholas.timetable;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.nicholas.timetable.fragments.CallsFragment;
import com.nicholas.timetable.fragments.TimetableFragment;
import com.nicholas.timetable.networking.RequestSender;
import com.nicholas.timetable.networking.Sendable;
import com.nicholas.timetable.viewmodels.TimetableViewModel;


public class MainActivity extends AppCompatActivity implements Sendable {


    private ViewGroup loadedDataContainer;
    private ViewGroup loadingDialogContainer;

    private ViewGroup contentContainer;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private TimetableFragment timetableFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initWidgets();
        initCallsFragment();
        RequestSender.getInstance().update(this);
    }


    private void initWidgets() {
        loadedDataContainer = findViewById(R.id.loadedDataContainer);
        loadingDialogContainer = findViewById(R.id.loadDialogContainer);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorPrimary));
        toolbar = findViewById(R.id.app_toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        contentContainer = findViewById(R.id.content_fragment_container);
        contentContainer.setNestedScrollingEnabled(true);
    }

    //Ициализировать фрагмент с расписанием звонков
    private void initCallsFragment() {
        CallsFragment fragment = new CallsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.timerFragment_container, fragment);
        transaction.commit();
    }

    private void openTimetableFragment(){
        timetableFragment = new TimetableFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_fragment_container, timetableFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                ListView groupsListView = new ListView(this);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.alert_dialog_groupname_item,R.id.alertDialog_groupName_Tv, TimetableViewModel.getInstance().getGroupNames());
                groupsListView.setAdapter(arrayAdapter);
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                dialogBuilder.setView(groupsListView);
                final AlertDialog dialog = dialogBuilder.create();
                groupsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TimetableViewModel.getInstance().setCurrentGroupName(MainActivity.this, arrayAdapter.getItem(position));
                        Log.d("DEBUG", "onClick: " + arrayAdapter.getItem(position));
                        refreshTimetable();
                        dialog.dismiss();
                    }
                });
                dialog.show();
                return true;
        }
        return false;
    }

    @Override
    public void getSendCallbackResult(boolean result) {
        if(result){
            loadingDialogContainer.setVisibility(View.GONE);
            openTimetableFragment();
            TimetableViewModel.getInstance().setSelectedGroup("Все группы");
            loadedDataContainer.setVisibility(View.VISIBLE);
        }
    }


    public void refreshTimetable(){
        String newGroup = TimetableViewModel.getInstance().getCurrentGroupName();
        collapsingToolbarLayout.setTitle(newGroup);
        TimetableViewModel.getInstance().setSelectedGroup(TimetableViewModel.getInstance().getCurrentGroupName());
        openTimetableFragment();
    }

}
