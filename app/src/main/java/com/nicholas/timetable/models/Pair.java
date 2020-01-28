package com.nicholas.timetable.models;
import com.google.gson.annotations.SerializedName;
import com.nicholas.timetable.lists.IListDataset;

import java.util.List;

public class Pair implements IListDataset {

    @SerializedName("number")
    public int number;
    @SerializedName("type")
    public int type;
    @SerializedName("lessons")
    public List<Lesson> lessons;
}
