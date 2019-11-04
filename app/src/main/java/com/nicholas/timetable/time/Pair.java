package com.nicholas.timetable.time;

import com.google.gson.annotations.SerializedName;


public class Pair{

    @SerializedName("type")
    private String type;
    @SerializedName("time")
    private String time;
    @SerializedName("groupsAtLunch")
    private String groupsAtLunch;

    public String getType() {
        return type;
    }

    public String getTime() {
        return time;
    }

    public String getGroupsAtLunch() {
        return groupsAtLunch;
    }

}
