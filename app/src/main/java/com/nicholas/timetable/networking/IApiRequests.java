package com.nicholas.timetable.networking;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IApiRequests {
    @GET("groups")
    Call<String> getGroups();

}
