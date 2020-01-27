package com.nicholas.timetable.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nicholas.timetable.R;
import com.nicholas.timetable.time.Timer;


public class CallsFragment extends Fragment {


    private TextView callTv, groupsTv;

    private Timer timer;


    public CallsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calls, container, false);
        ViewGroup callsContainer = view.findViewById(R.id.callsMainContainer);
        callTv = view.findViewById(R.id.call_time_tv);
        groupsTv = view.findViewById(R.id.groups_at_lunch_tv);
        timer = new Timer(callsContainer, callTv, groupsTv);
        timer.start();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(callTv.length() == 0)
            timer.update();
    }
}
