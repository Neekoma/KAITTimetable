package com.nicholas.timetable.lists.Timetable.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.nicholas.timetable.R;
import com.nicholas.timetable.lists.Timetable.TimetableViewHolder;

public class PairType0ViewHolder extends TimetableViewHolder {

    private TextView pairNumber;
    private TextView pairName;
    private TextView pairRoom;



    public PairType0ViewHolder(@NonNull View itemView) {
        super(itemView);
        pairNumber = itemView.findViewById(R.id.pairNumber);
        pairName = itemView.findViewById(R.id.pair_body_type0_tv);
        pairRoom = itemView.findViewById(R.id.pair_body_type0_room_tv);
    }

    public TextView getPairNumber() {
        return pairNumber;
    }

    public TextView getPairName() {
        return pairName;
    }

    public TextView getPairRoom() {
        return pairRoom;
    }

    @Override
    public void bind() {

    }
}
