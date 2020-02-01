package com.nicholas.timetable.lists.Timetable.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.nicholas.timetable.R;
import com.nicholas.timetable.lists.IListDataset;
import com.nicholas.timetable.models.Lesson;
import com.nicholas.timetable.models.Pair;

import java.util.ArrayList;

public class PairType3ViewHolder extends AbstractPairViewHolder {



    private TextView pairNumber;

    private TextView nechetnayaPairBodySubgroup1Tv;
    private TextView nechetnayaPairBodySubgroup2Tv;
    private TextView chetnayaPairBodySubgroup1Tv;
    private TextView chetnayaPairBodySubgroup2Tv;
    private TextView nechetnayaPairBodySubgroup1RoomTv;
    private TextView nechetnayaPairBodySubgroup2RoomTv;
    private TextView chetnayaPairBodySubgroup1RoomTv;
    private TextView chetnayaPairBodySubgroup2RoomTv;
    public PairType3ViewHolder(@NonNull View itemView) {
        super(itemView);
        pairNumber = itemView.findViewById(R.id.pairNumber);
        nechetnayaPairBodySubgroup1Tv = itemView.findViewById(R.id.nechetnaya_pair_body_type2_subgroup1_tv);
        nechetnayaPairBodySubgroup2Tv = itemView.findViewById(R.id.nechetnaya_pair_body_type2_subgroup2_tv);
        chetnayaPairBodySubgroup1Tv = itemView.findViewById(R.id.chetnaya_pair_body_type2_subgroup1_tv);
        chetnayaPairBodySubgroup2Tv = itemView.findViewById(R.id.chetnaya_pair_body_type2_subgroup2_tv);
        nechetnayaPairBodySubgroup1RoomTv = itemView.findViewById(R.id.nechetnaya_pair_body_type2_subgroup1_room_tv);
        nechetnayaPairBodySubgroup2RoomTv = itemView.findViewById(R.id.nechetnaya_pair_body_type2_subgroup2_room_tv);
        chetnayaPairBodySubgroup1RoomTv = itemView.findViewById(R.id.chetnaya_pair_body_type2_subgroup1_room_tv);
        chetnayaPairBodySubgroup2RoomTv = itemView.findViewById(R.id.chetnaya_pair_body_type2_subgroup2_room_tv);
    }

    @Override
    public void bind(ArrayList<IListDataset> dataset, int pos) {
        Pair pair = (Pair) dataset.get(pos);
        pairNumber.setText(Integer.toString(pair.number));

        for (Lesson k : pair.lessons) {
            if (k.getGroup().equals("1")) {
                if (k.getParity().equals("Нечетная")) {
                    nechetnayaPairBodySubgroup1Tv.setText(k.getName() + "\n\n" + k.getTeacher());
                    nechetnayaPairBodySubgroup1RoomTv.setText(k.getRoom());
                } else {
                    chetnayaPairBodySubgroup1Tv.setText(k.getName() + "\n\n" + k.getTeacher());
                    chetnayaPairBodySubgroup1RoomTv.setText(k.getRoom());
                }
            } else {
                if (k.getParity().equals("Нечетная")) {
                    nechetnayaPairBodySubgroup2Tv.setText(k.getName() + "\n\n" + k.getTeacher());
                    nechetnayaPairBodySubgroup2RoomTv.setText(k.getRoom());
                } else {
                    chetnayaPairBodySubgroup2Tv.setText(k.getName() + "\n\n" + k.getTeacher());
                    chetnayaPairBodySubgroup2RoomTv.setText(k.getRoom());
                }
            }

        }
        setPairStartAndEnd(pair.number);
    }
}
