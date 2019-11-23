package com.nicholas.timetable.networking;

import com.nicholas.timetable.models.Group;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IApiRequests {
    @GET("groups")
    Call<String> getGroups();

}
