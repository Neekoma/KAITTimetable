package com.nicholas.timetable.lists.Timetable.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.nicholas.timetable.R;
import com.nicholas.timetable.lists.IListDataset;
import com.nicholas.timetable.models.Lesson;
import com.nicholas.timetable.models.Pair;

import java.util.ArrayList;

public class PairType6ViewHolder extends AbstractPairViewHolder {

    private TextView pairNumber;
    private TextView nechetnayaPairBodySubgroup1Tv;
    private TextView nechetnayaPairBodySubgroup2Tv;
    private TextView nechetnayaPairBodySubgroup1RoomTv;
    private TextView nechetnayaPairBodySubgroup2RoomTv;
    private TextView chetnayaPairTv;
    private TextView chetnayaPairRoomTv;

    public PairType6ViewHolder(@NonNull View itemView) {
        super(itemView);
        pairNumber = itemView.findViewById(R.id.pairNumber);
        nechetnayaPairBodySubgroup1Tv = itemView.findViewById(R.id.nechetnaya_pair_body_type6_subgroup1_tv);
        nechetnayaPairBodySubgroup2Tv = itemView.findViewById(R.id.nechetnaya_pair_body_type6_subgroup2_tv);
        nechetnayaPairBodySubgroup1RoomTv = itemView.findViewById(R.id.nechetnaya_pair_body_type6_subgroup1_room_tv);
        nechetnayaPairBodySubgroup2RoomTv = itemView.findViewById(R.id.nechetnaya_pair_body_type6_subgroup2_room_tv);
        chetnayaPairTv = itemView.findViewById(R.id.chetnaya_pair_body_type6_tv);
        chetnayaPairRoomTv = itemView.findViewById(R.id.chetnaya_pair_body_type6_room_tv);
    }

    @Override
    public void bind(ArrayList<IListDataset> dataset, int pos) {
        Pair pair = (Pair) dataset.get(pos);
        pairNumber.setText(Integer.toString(pair.number));
        setPairStartAndEnd(pair.number);
        for (Lesson k : pair.lessons) {
            if (k.getParity().equals("Четная")) {
                chetnayaPairTv.setText(k.getName() + "\n\n" + k.getTeacher());
                chetnayaPairRoomTv.setText(k.getRoom());
            } else {
                if (k.getGroup().equals("1")) {
                    nechetnayaPairBodySubgroup1Tv.setText(k.getName() + "\n\n" + k.getTeacher());
                    nechetnayaPairBodySubgroup1RoomTv.setText(k.getRoom());
                } else {
                    nechetnayaPairBodySubgroup2Tv.setText(k.getName() + "\n\n" + k.getTeacher());
                    nechetnayaPairBodySubgroup2RoomTv.setText(k.getRoom());
                }

            }
        }
    }
}
