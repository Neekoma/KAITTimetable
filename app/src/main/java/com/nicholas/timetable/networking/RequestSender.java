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

public class RequestSender implements Updateable, Callback<String> {

    private static RequestSender instance;

    //Изменить при необходимости
    public static final String SERVER_URL = "http://192.168.1.65:8080/api/";


    Retrofit retrofit;
    IApiRequests api;


    private Sendable lastSender;
    private String lastJson;

    private RequestSender() {
        initNetworkObject();
    }



    public static RequestSender getInstance() {
        if (instance == null)
            instance = new RequestSender();
        return instance;
    }


    public IApiRequests getApi() {
        return api;
    }

    private void initNetworkObject() {
        retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        api = retrofit.create(IApiRequests.class);
    }


    @Override
    public void update(Sendable sender) {
        Call<String> result = api.getGroups();
        result.enqueue(this);
        lastSender = sender;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if(response.isSuccessful()){
            Handler jsonHandler = new Handler();
            TimetableViewModel.getInstance().setGroups(jsonHandler.setGroups(response.body()));
            lastJson = response.body();
            lastSender.getSendCallbackResult(true);
        }
        else
           lastSender.getSendCallbackResult(false);
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        lastSender.getSendCallbackResult(false);
        Log.d("DEBUG", "onFailure");
    }
    public String getLastJson(){return lastJson;}
}



