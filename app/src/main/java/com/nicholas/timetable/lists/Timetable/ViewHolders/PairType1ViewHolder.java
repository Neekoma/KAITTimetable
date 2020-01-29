package com.nicholas.timetable.lists.Timetable.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.nicholas.timetable.R;
import com.nicholas.timetable.lists.IListDataset;
import com.nicholas.timetable.models.Lesson;
import com.nicholas.timetable.models.Pair;

import java.util.ArrayList;

public class PairType1ViewHolder extends AbstractPairViewHolder{

    private TextView pairNumber;
    private TextView pairSub1Name;
    private TextView pairSub2Name;
    private TextView pairSub1Room;
    private TextView pairSub2Room;

    public PairType1ViewHolder(@NonNull View itemView) {
        super(itemView);
        pairNumber = itemView.findViewById(R.id.pairNumber);
        pairSub1Name = itemView.findViewById(R.id.pair_body_type3_subgroup1_tv);
        pairSub2Name = itemView.findViewById(R.id.pair_body_type3_subgroup2_tv);
        pairSub1Room = itemView.findViewById(R.id.pair_body_type3_subgroup1_room_tv);
        pairSub2Room = itemView.findViewById(R.id.pair_body_type3_subgroup2_room_tv);
    }


    @Override
    public void bind(ArrayList<IListDataset> dataset, int pos) {
        Pair pair = (Pair) dataset.get(pos);
        pairNumber.setText(Integer.toString(pair.number));
        for(Lesson i : pair.lessons){
            if(i.getGroup().equals("1")){
                pairSub1Name.setText(i.getName() + "\n\n" + i.getTeacher());
                pairSub1Room.setText(i.getRoom());
            }
            else{
                pairSub2Name.setText(i.getName() + "\n\n" + i.getTeacher());
                pairSub2Room.setText(i.getRoom());
            }
        }
        setPairStartAndEnd(pair.number);
    }
}
