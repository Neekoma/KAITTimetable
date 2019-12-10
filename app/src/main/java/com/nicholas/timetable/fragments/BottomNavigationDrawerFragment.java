package com.nicholas.timetable.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;
import com.nicholas.timetable.MainActivity;
import com.nicholas.timetable.R;

public class BottomNavigationDrawerFragment extends BottomSheetDialogFragment implements NavigationView.OnNavigationItemSelectedListener {


    private NavigationView navView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottomsheet, container, false);
        navView = view.findViewById(R.id.navigation_view);
        navView.setNavigationItemSelectedListener(this);
        return view;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        MainActivity activity = (MainActivity) getActivity();
        switch (menuItem.getItemId()){
            case R.id.action_timetable:
                Navigation.findNavController(activity, R.id.content_fragment_container).navigate(R.id.timetableFragment);
                activity.showCallsFragment(true);
                return true;
            case R.id.action_news:
                activity.showCallsFragment(false);
                return true;
            case R.id.action_lunch_menu:
                activity.showCallsFragment(false);
                return true;

        }
        return false;
    }
}
