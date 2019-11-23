package com.nicholas.timetable.models;

import com.google.gson.annotations.SerializedName;

public class Lesson {
    @SerializedName("name")
    private String name;
    @SerializedName("room")
    private String room;
    @SerializedName("group")
    private String group;
    @SerializedName("parity")
    private String parity;
    @SerializedName("teacher")
    private String teacher;


    public String getName() {
        return name;
    }

    public String getRoom() {
        return room;
    }

    public String getGroup() {
        return group;
    }

    public String getParity() {
        return parity;
    }

    public String getTeacher() {
        return teacher;
    }
}
