package com.nicholas.timetable;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.nicholas.timetable.networking.RequestSender;


public class TimetableFragment extends Fragment {

    private static final String TAG = "TimetableFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);
        RequestSender.getInstance().update();
        return view;
    }


}
