package com.nicholas.timetable;


import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.nicholas.timetable.viewmodels.FragmentDialog;


public class TimetableFragment extends Fragment implements FragmentDialog {

    private static final String TAG = "TimetableFragment";

    private RecyclerView timetableRecyclerView;


    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);
        timetableRecyclerView = view.findViewById(R.id.timetableRecyclerView);
        context = view.getContext();
        return view;
    }


    @Override
    public void showLoadDialog(){
        Log.d("DEBUG", "LOAD");
    }
    @Override
    public void showErrorDialog(){
        Log.d("DEBUG", "ERROR");
    }
    @Override
    public void showSuccessDialog(){
        Log.d("DEBUG", "SUCCESS");
    }




}
