package com.nicholas.timetable.time;

import com.google.gson.annotations.SerializedName;

public class Pair {

   private static final String TYPE_TO_LESSON = "ToLesson";
   private static final String TYPE_FROM_LESSON = "fromLesson";
   private static final String TYPE_TO_LUNCH = "toLunch";


    @SerializedName("type")
    private String type;
    @SerializedName("time")
    private String time;
    @SerializedName("groupsAtLunch")
    private String groupsAtLunch;


    public String getType(){return type;}
    public String getTime(){return time;}
    public String getGroupsAtLunch(){return groupsAtLunch;}

}
