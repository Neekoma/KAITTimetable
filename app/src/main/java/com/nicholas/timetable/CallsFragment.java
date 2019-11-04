package com.nicholas.timetable;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        callTv = view.findViewById(R.id.call_time_tv);
        groupsTv = view.findViewById(R.id.groups_at_lunch_tv);
        timer = new Timer(callTv, groupsTv);
        timer.start();
        return view;
    }

}
