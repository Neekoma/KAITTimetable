package com.nicholas.timetable.fragments;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.RecyclerView;

import com.nicholas.timetable.R;
import com.nicholas.timetable.models.DayOfWeek;
import com.nicholas.timetable.viewmodels.FragmentDialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.nicholas.timetable.JsonHandler.Handler;


public class TimetableFragment extends Fragment implements FragmentDialog {

    private static final String TAG = "TimetableFragment";
    private LinearLayout tableContainer;

   // private RecyclerView timetableRecyclerView;

    private HashMap<String, List<DayOfWeek>> groups;


    private Context context;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);
        groups = new HashMap<>();
        tableContainer = view.findViewById(R.id.tableContainer);
        context = view.getContext();
        initGroups();
        return view;
    }



    private void initGroups() {
        String res= "";
        Handler handler = new Handler();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open("timetable.json")));
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNext())
                res += scanner.next();
            groups = handler.setGroups(res);

            for(Map.Entry<String, List<DayOfWeek>> entry : groups.entrySet()){

                for(DayOfWeek i : entry.getValue()){
                    View dayTitleView = getLayoutInflater().inflate(R.layout.day_of_week, tableContainer, false);

                }

            }











        }
        catch (IOException e){

        }
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
