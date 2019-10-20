package com.nicholas.timetable.adapters.viewholders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class MyViewHolder extends RecyclerView.ViewHolder implements IViewHolderBehaviour {

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
