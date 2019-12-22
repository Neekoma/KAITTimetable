package com.nicholas.timetable.fragments;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.nicholas.timetable.R;
import com.nicholas.timetable.models.DayOfWeek;
import com.nicholas.timetable.models.Lesson;
import com.nicholas.timetable.models.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.nicholas.timetable.viewmodels.TimetableViewModel;


public class TimetableFragment extends Fragment{

    private static final String TAG = "TimetableFragment";
    private LinearLayout tableContainer;

    private Context context;
    private Map<String, List<DayOfWeek>> groups;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);
        tableContainer = view.findViewById(R.id.tableContainer);
        context = getActivity().getApplicationContext();
        return view;
    }


    public void initGroups(LayoutInflater inflater) {

            for (Map.Entry<String, List<DayOfWeek>> entry : groups.entrySet()) {

                for (DayOfWeek i : entry.getValue()) {
                    View dayTitleView = inflater.inflate(R.layout.day_of_week, tableContainer, false);
                    TextView dayTitle = dayTitleView.findViewById(R.id.dayName);
                    dayTitle.setText(i.getDayName());
                    tableContainer.addView(dayTitleView);
                    for (Pair j : i.getPairs()) {
                        View pairTemplate;

                        TextView nechetnayaPairBodySubgroup1Tv;
                        TextView nechetnayaPairBodySubgroup2Tv;
                        TextView chetnayaPairBodySubgroup1Tv;
                        TextView chetnayaPairBodySubgroup2Tv;
                        TextView nechetnayaPairBodySubgroup1RoomTv;
                        TextView nechetnayaPairBodySubgroup2RoomTv;
                        TextView chetnayaPairBodySubgroup1RoomTv;
                        TextView chetnayaPairBodySubgroup2RoomTv;

                        switch (j.type) {
                            case 0:
                                pairTemplate = inflater.inflate(R.layout.pair_type_0, tableContainer, false);
                                TextView pairNumber = pairTemplate.findViewById(R.id.pairNumber);
                                pairNumber.setText(Integer.toString(j.number));
                                TextView pairName = pairTemplate.findViewById(R.id.pair_body_type0_tv);
                                TextView pairRoom = pairTemplate.findViewById(R.id.pair_body_type0_room_tv);
                                pairName.setText(j.lessons.get(0).getName());
                                pairRoom.setText(j.lessons.get(0).getRoom());
                                tableContainer.addView(pairTemplate);
                                break;
                            case 1:
                                pairTemplate = inflater.inflate(R.layout.pair_type_3, tableContainer, false);
                                TextView pairNumber2 = pairTemplate.findViewById(R.id.pairNumber);
                                pairNumber2.setText(Integer.toString(j.number));
                                TextView pairNameSub1 = pairTemplate.findViewById(R.id.pair_body_type3_subgroup1_tv);
                                TextView pairNameSub2 = pairTemplate.findViewById(R.id.pair_body_type3_subgroup2_tv);
                                TextView pairNameSub1Room = pairTemplate.findViewById(R.id.pair_body_type3_subgroup1_room_tv);
                                TextView pairNameSub2Room = pairTemplate.findViewById(R.id.pair_body_type3_subgroup2_room_tv);
                                for (Lesson k : j.lessons) {
                                    if (k.getGroup().equals("1")) {
                                        pairNameSub1.setText(k.getName());
                                        pairNameSub1Room.setText(k.getRoom());
                                    } else {
                                        pairNameSub2.setText(k.getName());
                                        pairNameSub2Room.setText(k.getRoom());
                                    }
                                }
                                tableContainer.addView(pairTemplate);
                                break;
                            case 3:
                                pairTemplate = inflater.inflate(R.layout.pair_type_2, tableContainer, false);
                                TextView pairNumber3 = pairTemplate.findViewById(R.id.pairNumber);
                                pairNumber3.setText(Integer.toString(j.number));
                                nechetnayaPairBodySubgroup1Tv = pairTemplate.findViewById(R.id.nechetnaya_pair_body_type2_subgroup1_tv);
                                nechetnayaPairBodySubgroup2Tv = pairTemplate.findViewById(R.id.nechetnaya_pair_body_type2_subgroup2_tv);
                                chetnayaPairBodySubgroup1Tv = pairTemplate.findViewById(R.id.chetnaya_pair_body_type2_subgroup1_tv);
                                chetnayaPairBodySubgroup2Tv = pairTemplate.findViewById(R.id.chetnaya_pair_body_type2_subgroup2_tv);
                                nechetnayaPairBodySubgroup1RoomTv = pairTemplate.findViewById(R.id.nechetnaya_pair_body_type2_subgroup1_room_tv);
                                nechetnayaPairBodySubgroup2RoomTv = pairTemplate.findViewById(R.id.nechetnaya_pair_body_type2_subgroup2_room_tv);
                                chetnayaPairBodySubgroup1RoomTv = pairTemplate.findViewById(R.id.chetnaya_pair_body_type2_subgroup1_room_tv);
                                chetnayaPairBodySubgroup2RoomTv = pairTemplate.findViewById(R.id.chetnaya_pair_body_type2_subgroup2_room_tv);

                                for (Lesson k : j.lessons) {
                                    if (k.getGroup().equals("1")) {
                                        if (k.getParity().equals("Нечетная")) {
                                            nechetnayaPairBodySubgroup1Tv.setText(k.getName());
                                            nechetnayaPairBodySubgroup1RoomTv.setText(k.getRoom());
                                        } else {
                                            chetnayaPairBodySubgroup1Tv.setText(k.getName());
                                            chetnayaPairBodySubgroup1RoomTv.setText(k.getRoom());
                                        }
                                    } else {
                                        if (k.getParity().equals("Нечетная")) {
                                            nechetnayaPairBodySubgroup2Tv.setText(k.getName());
                                            nechetnayaPairBodySubgroup2RoomTv.setText(k.getRoom());
                                        } else {
                                            chetnayaPairBodySubgroup2Tv.setText(k.getName());
                                            chetnayaPairBodySubgroup2RoomTv.setText(k.getRoom());
                                        }
                                    }

                                }
                                tableContainer.addView(pairTemplate);
                                break;

                        }

                    }
                }

            }
    }
    public void refresh(String group, LayoutInflater inflater){
        if(group != "Все группы"){
            groups = new HashMap<>();
            groups.put(group, TimetableViewModel.getInstance().getGroups().get(group));
        }
        else
            groups = TimetableViewModel.getInstance().getGroups();

        if(tableContainer != null && tableContainer.getChildCount() > 0)
            tableContainer.removeAllViews();

        initGroups(inflater);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
