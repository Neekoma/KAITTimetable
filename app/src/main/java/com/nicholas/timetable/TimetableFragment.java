package com.nicholas.timetable;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.nicholas.timetable.models.Group;
import com.nicholas.timetable.networking.IApiRequests;
import com.nicholas.timetable.networking.Updateable;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TimetableFragment extends Fragment implements Updateable, View.OnClickListener {

    private static final String TAG = "TimetableFragment";

    private Context mContext;
    private View progressBar;
    private View errorContainer;
    private View contentContainer;
    private TextView contentText;

    // Network
    Retrofit retrofit;
    IApiRequests api;

    // Controls
    TextView updateTextView;
    public TimetableFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);
        mContext = view.getContext();
        progressBar = view.findViewById(R.id.timetable_progressIndicator);
        errorContainer = view.findViewById(R.id.timetable_error_container);
        contentContainer = view.findViewById(R.id.timetable_success_content_container);
        contentText = view.findViewById(R.id.timetable_contentTv);
        updateTextView = view.findViewById(R.id.timetable_update_tv);
        updateTextView.setOnClickListener(this);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(IApiRequests.class);
        update();
        return view;
    }

    @Override
    public boolean update(){
        showLoadIndicator();
        Call<List<Group>> call = api.getGroups();
        call.enqueue(new Callback<List<Group>>() {
            @Override
            public void onResponse(Call<List<Group>> call, Response<List<Group>> response) {
                if(!response.isSuccessful()){
                    showErrorScreen();
                    contentText.setText(Integer.toString(response.code()));
                }
                else{
                    showContent();
                    List<Group> result = response.body();
                    String content = "";
                    for(Group i : result){
                        content+= i.title + "\n\n";
                    }
                    contentText.setText(content);
                }
            }

            @Override
            public void onFailure(Call<List<Group>> call, Throwable t) {
                showErrorScreen();
            }
        });
        return false;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.timetable_update_tv:
                update();
                break;
        }
    }

    private void showLoadIndicator(){
        progressBar.setVisibility(View.VISIBLE);
        errorContainer.setVisibility(View.GONE);
        contentContainer.setVisibility(View.GONE);
    }
    private void showErrorScreen(){
        errorContainer.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        contentContainer.setVisibility(View.GONE);
    }

    private void showContent(){
        contentContainer.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        errorContainer.setVisibility(View.GONE);
    }
}
