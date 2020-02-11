package com.nicholas.timetable.lists.Timetable.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.nicholas.timetable.R;
import com.nicholas.timetable.lists.IListDataset;
import com.nicholas.timetable.models.Lesson;
import com.nicholas.timetable.models.Pair;

import java.util.ArrayList;

public class PairType7ViewHolder extends AbstractPairViewHolder {

    private TextView pairNumber;
    private TextView chetnayaPairBodySubgroup1Tv;
    private TextView chetnayaPairBodySubgroup2Tv;
    private TextView chetnayaPairBodySubgroup1RoomTv;
    private TextView chetnayaPairBodySubgroup2RoomTv;
    private TextView nechetnayaPairTv;
    private TextView nechetnayaPairRoomTv;

    public PairType7ViewHolder(@NonNull View itemView) {
        super(itemView);
        pairNumber = itemView.findViewById(R.id.pairNumber);
        chetnayaPairBodySubgroup1Tv = itemView.findViewById(R.id.chetnaya_pair_body_type7_subgroup1_tv);
        chetnayaPairBodySubgroup2Tv = itemView.findViewById(R.id.chetnaya_pair_body_type7_subgroup2_tv);
        chetnayaPairBodySubgroup1RoomTv = itemView.findViewById(R.id.chetnaya_pair_body_type7_subgroup1_room_tv);
        chetnayaPairBodySubgroup2RoomTv = itemView.findViewById(R.id.chetnaya_pair_body_type7_subgroup2_room_tv);
        nechetnayaPairTv = itemView.findViewById(R.id.nechetnaya_pair_body_type7_tv);
        nechetnayaPairRoomTv = itemView.findViewById(R.id.nechetnaya_pair_body_type7_room_tv);
    }

    @Override
    public void bind(ArrayList<IListDataset> dataset, int pos) {
        Pair pair = (Pair) dataset.get(pos);
        pairNumber.setText(Integer.toString(pair.number));
        for(Lesson i : pair.lessons){
            if(i.getParity().equals("Нечетная")){
                nechetnayaPairTv.setText(i.getName() + "\n\n" + i.getTeacher());
                nechetnayaPairRoomTv.setText(i.getRoom());
            }
            else {
                if (i.getGroup().equals("1")) {
                    chetnayaPairBodySubgroup1Tv.setText(i.getName() + "\n\n" + i.getTeacher());
                    chetnayaPairBodySubgroup1RoomTv.setText(i.getRoom());
                } else {
                    chetnayaPairBodySubgroup2Tv.setText(i.getName() + "\n\n" + i.getTeacher());
                    chetnayaPairBodySubgroup2RoomTv.setText(i.getRoom());
                }
            }
        }
    }
}
