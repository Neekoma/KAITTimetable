package com.nicholas.timetable.networking;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.nicholas.timetable.App;
import com.nicholas.timetable.Json.Handler;
import com.nicholas.timetable.viewmodels.TimetableViewModel;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RequestSender implements Updateable, Callback<String> {

    private static RequestSender instance;

    public static final String SERVER_URL = "http://junior.ru/rasp/api/";
    public static final String DEBUG_SERVER_URL = "http://192.168.1.66:8080/api/";


    Retrofit retrofit;
    IApiRequests api;
    Call<String> result;

    private Sendable lastSender;
    private String lastJson;
    private Disposable dispose;

    private RequestSender() {
        initNetworkObject();
    }



    public static RequestSender getInstance() {
        if (instance == null)
            instance = new RequestSender();
        return instance;
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
        lastSender = sender;
        dispose = timerThread()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it->{
                    result.cancel();
                    Log.d("DEBUG", "Превышено время ожидания ответа сервера");
                    App.isOnline = false;
                    if(!dispose.isDisposed())
                        lastSender.getSendCallbackResult(false);
                },it->{

                });
        result = api.getGroups();
        result.enqueue(this);
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
       dispose.dispose();
        try {
            if (response.isSuccessful()) {
                Handler jsonHandler = new Handler();
                TimetableViewModel.getInstance().setGroups(jsonHandler.setGroups(response.body()));
                lastJson = response.body();
                App.isOnline = true;
                lastSender.getSendCallbackResult(true);
            } else
                lastSender.getSendCallbackResult(false);
        }
        catch (Exception e){
            lastSender.getSendCallbackResult(false);
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        dispose.dispose();
        lastSender.getSendCallbackResult(false);
    }
    public String getLastJson(){return lastJson;}

    private Single<Integer> timerThread(){
        return Single.create(subscriber->{
            try {
                Thread.sleep(6 * 1000); // Если ответ не поступил в течение 6 секунд, то открыть сохраненную версию
                if(!subscriber.isDisposed())
                    subscriber.onSuccess(0);
            }
            catch (InterruptedException e){

            }
        });
    }

}
