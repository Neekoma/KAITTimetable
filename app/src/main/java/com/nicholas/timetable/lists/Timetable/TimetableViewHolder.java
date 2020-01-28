package com.nicholas.timetable.lists.Timetable;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class TimetableViewHolder extends RecyclerView.ViewHolder implements ITimetableViewHolder{

    public TimetableViewHolder(@NonNull View itemView) {
        super(itemView);
    }


}
