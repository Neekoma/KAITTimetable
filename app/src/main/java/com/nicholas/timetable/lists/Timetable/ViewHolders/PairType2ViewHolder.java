package com.nicholas.timetable.lists.Timetable.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.nicholas.timetable.R;
import com.nicholas.timetable.lists.IListDataset;
import com.nicholas.timetable.models.Lesson;
import com.nicholas.timetable.models.Pair;

import java.util.ArrayList;

public class PairType2ViewHolder extends AbstractPairViewHolder {

    private TextView pairNumber;
    private TextView chetnayaPairName;
    private TextView nechetnayaPairName;
    private TextView chetnayaPairRoom;
    private TextView nechetnayaPairRoom;


    public PairType2ViewHolder(@NonNull View itemView) {
        super(itemView);
        pairNumber = itemView.findViewById(R.id.pairNumber);
        nechetnayaPairName = itemView.findViewById(R.id.nechetnaya_pair_body_type1_tv);
        chetnayaPairName = itemView.findViewById(R.id.chetnaya_pair_body_type1_tv);
        nechetnayaPairRoom = itemView.findViewById(R.id.nechetnaya_pair_body_type1_room_tv);
        chetnayaPairRoom = itemView.findViewById(R.id.chetnaya_pair_body_type1_room_tv);
    }

    @Override
    public void bind(ArrayList<IListDataset> dataset, int pos) {
        Pair pair = (Pair) dataset.get(pos);
        pairNumber.setText(Integer.toString(pair.number));
        setPairStartAndEnd(pair.number);
        for (Lesson i : pair.lessons) {
            if (i.getParity().equals(super.PARITY_NECHETNAYA)) {
                nechetnayaPairName.setText(i.getName() + "\n\n" + i.getTeacher());
                nechetnayaPairRoom.setText(i.getRoom());
            } else {
                chetnayaPairName.setText(i.getName() + "\n\n" + i.getTeacher());
                chetnayaPairRoom.setText(i.getRoom());
            }
        }
    }
}
