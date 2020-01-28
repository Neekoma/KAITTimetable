package com.nicholas.timetable.lists.Timetable.ViewHolders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.nicholas.timetable.R;
import com.nicholas.timetable.lists.IListDataset;
import com.nicholas.timetable.models.Pair;

import java.util.ArrayList;

public class PairType0ViewHolder extends AbstractPairViewHolder implements View.OnClickListener{

    private TextView pairNumber;
    private TextView pairName;
    private TextView pairRoom;

    private String teacher;

    private Context context;

    public PairType0ViewHolder(@NonNull View itemView) {
        super(itemView);
        pairNumber = itemView.findViewById(R.id.pairNumber);
        pairName = itemView.findViewById(R.id.pair_body_type0_tv);
        pairRoom = itemView.findViewById(R.id.pair_body_type0_room_tv);
        context = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    @Override
    public void bind(ArrayList<IListDataset> dataset, int pos) {
        Pair pair = (Pair) dataset.get(pos);
        pairNumber.setText(Integer.toString(pair.number));
        pairName.setText(pair.lessons.get(0).getName());
        pairRoom.setText(pair.lessons.get(0).getRoom());
        teacher = pair.lessons.get(0).getTeacher();
        setPairStartAndEnd(pair.number);
    }


    @Override
    public void onClick(View v) {
        Toast.makeText(context, String.format("Преподаватель: %s\nНачало: %s\nКонец: %s", teacher, start, end), Toast.LENGTH_LONG).show();
    }
}
