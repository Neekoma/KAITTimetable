package com.nicholas.timetable.networking;

import android.util.Log;

import com.nicholas.timetable.JsonHandler.Handler;
import com.nicholas.timetable.TimetableBinder;
import com.nicholas.timetable.viewmodels.TimetableViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class JsonReceiver{


    public static final String SERVER_URL = "http://junior.ru/rasp/api/";


    Retrofit retrofit;
    IApiRequests api;

    String json;


    public JsonReceiver(){
        Log.d("DEBUG", "receiver construct");
        initNetworkObject();
    }

    private void initNetworkObject() {
        retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        api = retrofit.create(IApiRequests.class);
    }

    public String getGroupsJson(){
        Call<String> result = api.getGroups();
        result.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful())
                   setJson(response.body());
                else
                    setJson(null);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                setJson(null);
            }
        });
        if(json != null)
            return json;
        return null;
    }

    private void setJson(String json){
        this.json = json;
    }



}