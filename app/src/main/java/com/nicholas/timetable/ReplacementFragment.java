package com.nicholas.timetable;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nicholas.timetable.networking.RequestSender;


public class ReplacementFragment extends Fragment {

    private static final String TAG = "ReplacementFragment";

    RequestSender sender;

    public ReplacementFragment() {
        Log.d(TAG, "ReplacementFragment: constructor");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_replacement, container, false);

    }

}
