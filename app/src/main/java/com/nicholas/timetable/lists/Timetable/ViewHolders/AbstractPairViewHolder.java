package com.nicholas.timetable.lists.Timetable.ViewHolders;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.nicholas.timetable.R;
import com.nicholas.timetable.lists.IListDataset;
import com.nicholas.timetable.lists.Timetable.Date;
import com.nicholas.timetable.lists.Timetable.TimetableViewHolder;

import java.util.ArrayList;

public abstract class AbstractPairViewHolder extends TimetableViewHolder implements OnClickListener{


    protected final static String PARITY_NECHETNAYA = "Нечетная";
    protected final static String PARTTY_CHETNAYA = "Четная";

    protected String start;
    protected String end;

    public AbstractPairViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        Toast.makeText(v.getContext(), String.format("Начало: %s\nОкончание: %s", start, end), Toast.LENGTH_SHORT).show();
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
