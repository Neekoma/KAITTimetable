package com.nicholas.timetable;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.nicholas.timetable.viewmodels.TimetableFragmentViewModel;


public class TimetableFragment extends Fragment{

    private static final String TAG = "TimetableFragment";

    private Context mContext;



    public TimetableFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);
        mContext = view.getContext();
        return view;
    }


}
