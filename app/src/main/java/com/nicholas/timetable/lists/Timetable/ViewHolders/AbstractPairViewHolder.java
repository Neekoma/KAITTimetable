package com.nicholas.timetable.lists.Timetable.ViewHolders;

import android.view.View;

import androidx.annotation.NonNull;

import com.nicholas.timetable.lists.IListDataset;
import com.nicholas.timetable.lists.Timetable.TimetableViewHolder;

import java.util.ArrayList;

public abstract class AbstractPairViewHolder extends TimetableViewHolder {

    protected String start;
    protected String end;

    public AbstractPairViewHolder(@NonNull View itemView) {
        super(itemView);
    }


    @Override
    public void bind(ArrayList<IListDataset> dataset, int pos){

    }

    protected void setPairStartAndEnd(int pairNumber){
        switch (pairNumber){
            case 1:
                start = "9:00";
                end = "10:30";
                break;
            case 2:
                start = "10:40";
                end = "12:30";
                break;
            case 3:
                start = "12:50";
                end = "14:40";
                break;
            case 4:
                start = "14:50";
                end = "16:20";
                break;
            case 5:
                start = "16:30";
                end = "18:00";
                break;
        }
    }
}
