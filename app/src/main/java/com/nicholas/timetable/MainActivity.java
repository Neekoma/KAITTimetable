package com.nicholas.timetable;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.nicholas.timetable.fragments.CallsFragment;
import com.nicholas.timetable.fragments.TimetableFragment;
import com.nicholas.timetable.models.DayOfWeek;
import com.nicholas.timetable.networking.RequestSender;
import com.nicholas.timetable.networking.Sendable;
import com.nicholas.timetable.viewmodels.TimetableViewModel;

import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity implements Sendable, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {


    private ViewGroup loadedDataContainer;
    private ViewGroup loadingDialogContainer;
    private ViewGroup loadingErrorDialogContainer;
    private TextView refreshTv;

    private SwipeRefreshLayout swipeRefresh;

    private ViewGroup contentContainer;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private TimetableFragment timetableFragment;
    private View selectGroupSpinner;

  //  private boolean loaded;


    private SharedPreferences preferences;
    private String sharedGroup;
    public static final String SELECT_GROUP_IN_PREFERENCES = "Выберите группу";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        preferences = getSharedPreferences("MY_SHARED", Context.MODE_PRIVATE);
        sharedGroup = preferences.getString("SHARED_GROUP", SELECT_GROUP_IN_PREFERENCES);
        TimetableViewModel.getInstance().setCurrentGroupName(this, sharedGroup);
        initWidgets();
        initCallsFragment();

        if(TimetableBinder.haveSave(getApplicationContext())){
            loadByLocalData();
            Toast.makeText(this, "Загружено сохраненное расписание", Toast.LENGTH_LONG).show();
        }

        RequestSender.getInstance().update(this);
    }


    private void initWidgets() {
        swipeRefresh = findViewById(R.id.swipeToRefresh);
        loadedDataContainer = findViewById(R.id.loadedDataContainer);
        loadingDialogContainer = findViewById(R.id.loadDialogContainer);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorWhite));
        collapsingToolbarLayout.setExpandedTitleTypeface(Typeface.DEFAULT_BOLD);
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar = findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        contentContainer = findViewById(R.id.content_fragment_container);
        loadingErrorDialogContainer = findViewById(R.id.loadingError);
        refreshTv = findViewById(R.id.refreshClickableTv);
        refreshTv.setOnClickListener(this);
        selectGroupSpinner = findViewById(R.id.select_group_spinner);
        contentContainer.setNestedScrollingEnabled(true);

        swipeRefresh.setOnRefreshListener(this);
    }

    //Ициализировать фрагмент с расписанием звонков
    private void initCallsFragment() {
        CallsFragment fragment = new CallsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.timerFragment_container, fragment);
        transaction.commit();
    }

    private void openTimetableFragment() {
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
        switch (item.getItemId()) {
            case R.id.action_settings:
                ListView groupsListView = new ListView(this);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.alert_dialog_groupname_item, R.id.alertDialog_groupName_Tv, TimetableViewModel.getInstance().getGroupNames());
                groupsListView.setAdapter(arrayAdapter);
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                dialogBuilder.setView(groupsListView);
                final AlertDialog dialog = dialogBuilder.create();
                groupsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TimetableViewModel.getInstance().setCurrentGroupName(MainActivity.this, arrayAdapter.getItem(position));
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("SHARED_GROUP", arrayAdapter.getItem(position));
                        editor.commit();
                        sharedGroup = preferences.getString("SHARED_GROUP", SELECT_GROUP_IN_PREFERENCES);
                        selectGroupSpinner.setVisibility(View.GONE);
                        refreshTimetable();
                        dialog.dismiss();
                        Log.d("DEBUG-1", "Item selected " + sharedGroup);
                    }
                });
                dialog.show();
                return true;
        }
        return false;
    }

    @Override
    public void getSendCallbackResult(boolean result) {
        if (result) {
            loadingDialogContainer.setVisibility(View.GONE);
            refreshTimetable();
            loadedDataContainer.setVisibility(View.VISIBLE);
            TimetableBinder.writeNewTimetableJson(getApplicationContext(), RequestSender.getInstance().getLastJson());
            Toast.makeText(this, "Расписание обновлено", Toast.LENGTH_LONG).show();
//            if(!loaded) {
//                startTimetableService();
//                loaded = true;
//            }

        } else {
            if(TimetableBinder.haveSave(getApplicationContext()))
                loadByLocalData();
            else{
                showLoadErrorDialog();
            }
        }

        if(swipeRefresh.isRefreshing())
            swipeRefresh.setRefreshing(false);

    }

    private void showFragment(){
        loadingDialogContainer.setVisibility(View.GONE);
        loadedDataContainer.setVisibility(View.VISIBLE);
    }

    public void refreshTimetable() {
        TimetableViewModel.getInstance().setSelectedGroup(sharedGroup);
        if (TimetableViewModel.getInstance().getGroups().containsKey(sharedGroup) && sharedGroup != SELECT_GROUP_IN_PREFERENCES) {
            collapsingToolbarLayout.setTitle(sharedGroup);
            openTimetableFragment();
        } else {
            collapsingToolbarLayout.setTitle(SELECT_GROUP_IN_PREFERENCES); // Выберите группу
            selectGroupSpinner.setVisibility(View.VISIBLE);
        }
    }

    private void loadByLocalData() {
        HashMap<String, List<DayOfWeek>> localGroups = TimetableBinder.getLocalGroups(getApplicationContext());
        if (localGroups != null) {
            TimetableViewModel.getInstance().setGroups(localGroups);
            TimetableViewModel.getInstance().setGroups(TimetableBinder.getLocalGroups(getApplicationContext()));
            refreshTimetable();
            showFragment();
        }

    }


    private void showLoadErrorDialog(){
        loadedDataContainer.setVisibility(View.GONE);
        loadingDialogContainer.setVisibility(View.GONE);
        loadingErrorDialogContainer.setVisibility(View.VISIBLE);
    }
    private void showLoadDialog(){
        loadedDataContainer.setVisibility(View.GONE);
        loadingErrorDialogContainer.setVisibility(View.GONE);
        loadingDialogContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.refreshClickableTv:
                    RequestSender.getInstance().update(this);
                    showLoadDialog();
                break;

        }
    }

    @Override
    public void onRefresh() {
        RequestSender.getInstance().update(this);
    }

//    private void startTimetableService(){
//        Intent intent = new Intent(this, TimetableNotificationService.class);
//        startService(intent);
//    }
//
//    private void stopTimetableService(){
//        Intent intent = new Intent(this, TimetableNotificationService.class);
//        startService(intent);
//    }


}
