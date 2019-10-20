package com.nicholas.timetable;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nicholas.timetable.viewmodels.TimetableFragmentViewModel;


public class TimetableFragment extends Fragment {

    private static final String TAG = "TimetableFragment";

    private static TimetableFragmentViewModel viewModel = null;

    private RecyclerView timetableRecyclerView;


    public TimetableFragment() {
        if (viewModel == null)
            viewModel = new TimetableFragmentViewModel();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);
        timetableRecyclerView = view.findViewById(R.id.timetable_recyclerview);
        return view;
    }

}
