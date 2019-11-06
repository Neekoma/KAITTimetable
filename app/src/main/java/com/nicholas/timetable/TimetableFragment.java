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


public class TimetableFragment extends Fragment implements TableFragment{

    private static final String TAG = "TimetableFragment";

    private Context mContext;

    private TableLayout table;
    private TableRow groupsRow;


    public TimetableFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);
        mContext = view.getContext();
        initTable();
        return view;
    }

    @Override
    public void initTable() {
        table = new TableLayout(mContext);
        table.setStretchAllColumns(true);
        table.setShrinkAllColumns(true);
    }

    @Override
    public void update() {

    }
}
