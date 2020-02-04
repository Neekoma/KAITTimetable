package com.nicholas.timetable.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.nicholas.timetable.R;
import com.nicholas.timetable.Updateable;
import com.nicholas.timetable.dialogs.groupSelect.GroupSelector;
import com.nicholas.timetable.time.WeekWorker;
import com.nicholas.timetable.viewmodels.TimetableViewModel;

public class TimetableFragment extends Fragment implements Updateable {

    private static final String TAG = "TimetableFragment";
    private View parent;
    public static final String SELECT_GROUP_IN_PREFERENCES = "Выберите группу";
    private SharedPreferences preferences;
    private String sharedGroup;
    private TextView oddWeekTv;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    private WeekWorker weekWorker;
    private TextView dateToday;
    private TextView dateWeekRange;

    private RecyclerView timetableRecyclerView;

    GroupSelector selector;

   // private UpdatesChecker checker; Ошибка молодости

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);
        initCalendar(view);
        parent = view;
        initWidgets(view);
        initCallsFragment();
        preferences = getActivity().getSharedPreferences("MY_SHARED", Context.MODE_PRIVATE);
        sharedGroup = preferences.getString("SHARED_GROUP", SELECT_GROUP_IN_PREFERENCES);
        TimetableViewModel.getInstance().setCurrentGroupName(sharedGroup);
        initToolbar(view);
        openTimetable();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.appbar_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                showGroupList(true);
                return true;
        }
        return false;
    }

    private void openTimetable() {
        if (TimetableViewModel.getInstance().getGroups().containsKey(sharedGroup) && !sharedGroup.equals(SELECT_GROUP_IN_PREFERENCES))
            update();
        else
            showGroupList(false);
    }

    private void showGroupList(boolean mode) {
//        ListView groupsListView = new ListView(getContext());
//        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.alert_dialog_groupname_item, R.id.alertDialog_groupName_Tv, TimetableViewModel.getInstance().getGroupNames());
//        groupsListView.setAdapter(arrayAdapter);
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
//        dialogBuilder.setCancelable(mode);
//        dialogBuilder.setView(groupsListView);
//        final AlertDialog dialog = dialogBuilder.create();
//        groupsListView.setOnItemClickListener((parent, view, position, id) -> {
//            TimetableViewModel.getInstance().setCurrentGroupName(arrayAdapter.getItem(position));
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.putString("SHARED_GROUP", arrayAdapter.getItem(position));
//            editor.commit();
//            sharedGroup = preferences.getString("SHARED_GROUP", SELECT_GROUP_IN_PREFERENCES);
//            collapsingToolbarLayout.setTitle(sharedGroup);
//            update();
//            dialog.dismiss();
//        });
//        dialog.show();
        if(selector == null)
            selector = new GroupSelector();
        selector.showGroupSelector(this, mode);
    }

    private void initWidgets(View v) {
        ViewGroup contentContainer = v.findViewById(R.id.timetable_content_container);
        timetableRecyclerView = v.findViewById(R.id.timetableRecyclerView);
        timetableRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        timetableRecyclerView.setAdapter(TimetableViewModel.getInstance().getAdapter(getContext()));
        contentContainer.setNestedScrollingEnabled(true);
    }

    private void initCalendar(View v){
        oddWeekTv = v.findViewById(R.id.oddWeek);
        dateToday = v.findViewById(R.id.dateToday);
        dateWeekRange = v.findViewById(R.id.dateWeekRange);
        WeekWorker worker = new WeekWorker(getContext(), dateToday, dateWeekRange, oddWeekTv);
        worker.startWeekWorkerJob();
    }
    private void initToolbar(View v) {
        setHasOptionsMenu(true);
        collapsingToolbarLayout = v.findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorWhite));
        collapsingToolbarLayout.setExpandedTitleTypeface(Typeface.DEFAULT_BOLD);
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorWhite));
        collapsingToolbarLayout.setTitle(sharedGroup);
        collapsingToolbarLayout.setNestedScrollingEnabled(true);
        toolbar = v.findViewById(R.id.timetable_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

    private void initCallsFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        CallsFragment fragment = new CallsFragment();
        transaction.replace(R.id.timerFragment_container, fragment);
        transaction.commit();
    }


    @Override
    public void onPause() {
        super.onPause();
        ((AppCompatActivity) getActivity()).setSupportActionBar(null);
    }

    @Override
    public void update() {
        // В случае, если во время обновления расписания не обнаружилось ранее выбранной группы, принудительно открыть список выбора групп
        if (TimetableViewModel.getInstance().getGroups().containsKey(TimetableViewModel.getInstance().getCurrentGroupName())) {
            collapsingToolbarLayout.setTitle(TimetableViewModel.getInstance().getCurrentGroupName());
            TimetableViewModel.getInstance().getAdapter(getContext()).switchGroup(TimetableViewModel.getInstance().getCurrentGroupName());
        }
        else {
            showGroupList(false);
            collapsingToolbarLayout.setTitle(SELECT_GROUP_IN_PREFERENCES);
        }
    }

}
