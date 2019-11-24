package com.nicholas.timetable.models;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pair {

    @SerializedName("number")
    public int number;
    @SerializedName("type")
    public int type;
    @SerializedName("lessons")
    public List<Lesson> lessons;
}
