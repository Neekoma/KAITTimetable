package com.nicholas.timetable.lists.Timetable;

import com.nicholas.timetable.lists.IListDataset;
import com.nicholas.timetable.models.Pair;

import java.util.ArrayList;

public interface ITimetableViewHolder {

    void bind(ArrayList<IListDataset> dataset, int pos);
}
