package com.nicholas.timetable.lists.Timetable.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.nicholas.timetable.R;
import com.nicholas.timetable.lists.IListDataset;
import com.nicholas.timetable.models.Lesson;
import com.nicholas.timetable.models.Pair;

import java.util.ArrayList;

public class PairType5ViewHolder extends AbstractPairViewHolder {

    private TextView pairNumber;
    private TextView pairBodySubgroup1Tv;
    private TextView pairBodySubgroup1RoomTv;
    private TextView nechetnayaPairBodySubgroup2Tv;
    private TextView nechetnayaPairBodySubgroup2RoomTv;
    private TextView chetnayaPairBodySubgroup2Tv;
    private TextView chetnayaPairBodySubgroup2RoomTv;


    public PairType5ViewHolder(@NonNull View itemView) {
        super(itemView);
        pairNumber = itemView.findViewById(R.id.pairNumber);
        pairBodySubgroup1Tv = itemView.findViewById(R.id.pair_body_type4_subgroup1_tv);
        pairBodySubgroup1RoomTv = itemView.findViewById(R.id.pair_body_type4_subgroup1_room_tv);
        nechetnayaPairBodySubgroup2Tv =itemView.findViewById(R.id.nechetnaya_pair_body_type4_tv);
        nechetnayaPairBodySubgroup2RoomTv =itemView.findViewById(R.id.nechetnaya_pair_body_type4_subgroup2_room_tv);
        chetnayaPairBodySubgroup2Tv =itemView.findViewById(R.id.chetnaya_pair_body_type4_tv);
        chetnayaPairBodySubgroup2RoomTv = itemView.findViewById(R.id.chetnaya_pair_body_type4_subgroup2_room_tv);
    }

    @Override
    public void bind(ArrayList<IListDataset> dataset, int pos) {
        Pair pair = (Pair) dataset.get(pos);
        pairNumber.setText(Integer.toString(pair.number));
        setPairStartAndEnd(pair.number);
        for(Lesson i : pair.lessons){

            if (i.getGroup().equals("1")) {
                pairBodySubgroup1Tv.setText(i.getName() + "\n\n" + i.getTeacher());
                pairBodySubgroup1RoomTv.setText(i.getRoom());
            } else {
                if (i.getParity().equals("Нечетная")) {
                    nechetnayaPairBodySubgroup2Tv.setText(i.getName() + "\n\n" + i.getTeacher());
                    nechetnayaPairBodySubgroup2RoomTv.setText(i.getRoom());
                } else {
                    chetnayaPairBodySubgroup2Tv.setText(i.getName() + "\n\n" + i.getTeacher());
                    chetnayaPairBodySubgroup2RoomTv.setText(i.getRoom());
                }
            }
        }
    }
}
