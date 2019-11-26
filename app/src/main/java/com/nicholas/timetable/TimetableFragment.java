package com.nicholas.timetable;


import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.nicholas.timetable.networking.RequestSender;
import com.nicholas.timetable.viewmodels.FragmentDialog;


public class TimetableFragment extends Fragment implements FragmentDialog {

    private static final String TAG = "TimetableFragment";
    private View contentView;
    private TextView contentTv;
    private String contentText;

    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);
        context = view.getContext();
        contentView = view.findViewById(R.id.successLayout);
        contentTv = view.findViewById(R.id.contentTv);
        showLoadDialog();
        RequestSender.getInstance().update(this);
        return view;
    }


    @Override
    public void showLoadDialog(){

    }
    @Override
    public void showErrorDialog(){
        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showSuccessDialog(){
        Log.d("JSON", "Success");
        contentTv.setText(RequestSender.strRes);
        contentView.setVisibility(View.VISIBLE);
    }




}
