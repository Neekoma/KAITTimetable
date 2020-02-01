package com.nicholas.timetable.lists.Timetable.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.nicholas.timetable.R;
import com.nicholas.timetable.lists.IListDataset;
import com.nicholas.timetable.models.Lesson;
import com.nicholas.timetable.models.Pair;

import java.util.ArrayList;

public class PairType4ViewHolder extends AbstractPairViewHolder {

    private TextView pairNumber;

    private TextView nechetnayaPairBodySubgroup1Tv;
    private TextView chetnayaPairBodySubgroup1Tv;
    private TextView nechetnayaPairBodySubgroup1RoomTv;
    private TextView chetnayaPairBodySubgroup1RoomTv;
    private TextView pairBodySubgroup2Tv;
    private TextView pairBodySubgroup2RoomTv;


    public PairType4ViewHolder(@NonNull View itemView) {
        super(itemView);
        pairNumber = itemView.findViewById(R.id.pairNumber);
        nechetnayaPairBodySubgroup1Tv = itemView.findViewById(R.id.nechetnaya_pair_body_type5_subgroup1_tv);
        chetnayaPairBodySubgroup1Tv = itemView.findViewById(R.id.chetnaya_pair_body_type5_subgroup1_tv);
        nechetnayaPairBodySubgroup1RoomTv = itemView.findViewById(R.id.nechetnaya_pair_body_type5_subgroup1_room_tv);
        chetnayaPairBodySubgroup1RoomTv = itemView.findViewById(R.id.chetnaya_pair_body_type5_subgroup1_room_tv);
        pairBodySubgroup2Tv = itemView.findViewById(R.id.pair_body_type5_subgroup2_tv);
        pairBodySubgroup2RoomTv = itemView.findViewById(R.id.pair_body_type5_subgroup2_room_tv);
    }

    @Override
    public void bind(ArrayList<IListDataset> dataset, int pos) {
        Pair pair = (Pair) dataset.get(pos);
        pairNumber.setText(Integer.toString(pair.number));
        setPairStartAndEnd(pair.number);
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
                pairBodySubgroup2Tv.setText(k.getName() + "\n\n" + k.getTeacher());
                pairBodySubgroup2RoomTv.setText(k.getRoom());
            }

        }
    }
}
