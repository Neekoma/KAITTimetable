package com.nicholas.timetable.adapters;

import android.view.ViewGroup;

import com.nicholas.timetable.adapters.viewholders.MyViewHolder;
import com.nicholas.timetable.adapters.viewholders.TimetableViewHolder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TimetableRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TimetableViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
