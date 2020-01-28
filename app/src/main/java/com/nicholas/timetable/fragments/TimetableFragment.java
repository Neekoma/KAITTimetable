package com.nicholas.timetable.fragments;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nicholas.timetable.R;
import com.nicholas.timetable.lists.Timetable.TimetableRecyclerViewAdapter;
import com.nicholas.timetable.models.DayOfWeek;
import com.nicholas.timetable.models.Lesson;
import com.nicholas.timetable.models.Pair;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import com.nicholas.timetable.viewmodels.TimetableViewModel;


public class TimetableFragment extends Fragment {

    private static final String TAG = "TimetableFragment";
   // private LinearLayout tableContainer;
    private TextView oddWeekTv;


    private Context context;
    private Map<String, List<DayOfWeek>> groups;


    private LayoutInflater inflater;

    private TimetableRecyclerViewAdapter adapter;
    private RecyclerView rv;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);
        this.inflater = inflater;
        oddWeekTv = view.findViewById(R.id.oddWeek);
        context = getActivity().getApplicationContext();
        setOddWeek();
        rv = view.findViewById(R.id.recycler);
        rv.setAdapter(new TimetableRecyclerViewAdapter(context, TimetableViewModel.getInstance().getSelectedGroupStr()));
        rv.setLayoutManager(new LinearLayoutManager(context));
        return view;
    }

    private void setOddWeek(){
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        if(week % 2 == 0)
            oddWeekTv.setText("Неделя: четная");
        else
            oddWeekTv.setText("Неделя: НЕчетная");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private enum GroupParity {
        NECHETNAYA("Нечетная"),
        CHETNAYA("Четная");

        private String parity;

        GroupParity(String parity) {
            this.parity = parity;
        }

        public String getParityString() {
            return parity;
        }
    }

}
