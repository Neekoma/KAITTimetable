package com.nicholas.timetable.adapters;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nicholas.timetable.JsonHandler.Handler;
import com.nicholas.timetable.adapters.ViewHolders.TimetableRecyclerViewHolder;
import com.nicholas.timetable.fragments.TimetableFragment;
import com.nicholas.timetable.models.DayOfWeek;
import com.nicholas.timetable.models.Pair;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TimetableRecyclerViewAdapter extends RecyclerView.Adapter<TimetableRecyclerViewHolder> {


    private Handler handler;
    private HashMap<String, List<DayOfWeek>> groups;

    private HashMap<Integer, Pair> pairs;



    @NonNull
    @Override
    public TimetableRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TimetableRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
