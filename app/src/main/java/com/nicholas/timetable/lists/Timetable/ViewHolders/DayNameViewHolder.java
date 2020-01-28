package com.nicholas.timetable.lists.Timetable.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.nicholas.timetable.R;
import com.nicholas.timetable.lists.IListDataset;
import com.nicholas.timetable.lists.TableHeader;
import com.nicholas.timetable.lists.Timetable.TimetableViewHolder;

import java.util.ArrayList;

public class DayNameViewHolder extends TimetableViewHolder {

    private TextView dayName;

    public DayNameViewHolder(@NonNull View itemView) {
        super(itemView);
        dayName = itemView.findViewById(R.id.dayName);
    }

    @Override
    public void bind(ArrayList<IListDataset> dataset, int pos) {
        TableHeader header = (TableHeader) dataset.get(pos);
        dayName.setText(header.title);
    }

    public TextView getDayName(){return dayName;}


    //Текущий день недели подчеркнуть определенным цветом
    /*
    private void setTitleColor(){

    }
     */
}
