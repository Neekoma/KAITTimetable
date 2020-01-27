package com.nicholas.timetable.lists.Timetable;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nicholas.timetable.lists.Timetable.ViewHolders.PairType0ViewHolder;
import com.nicholas.timetable.models.DayOfWeek;
import com.nicholas.timetable.models.Pair;
import com.nicholas.timetable.viewmodels.TimetableViewModel;

import java.util.ArrayList;
import java.util.List;

public class TimetableRecyclerViewAdapter extends RecyclerView.Adapter<TimetableViewHolder> {


    private ArrayList<Pair> pairs = new ArrayList<>();


    public TimetableRecyclerViewAdapter(String groupName){
        switchGroup(groupName);
    }

    public void switchGroup(String groupName){
        if(pairs.size() > 0)
            pairs.clear();
        List<DayOfWeek> days = TimetableViewModel.getInstance().getGroups().get(groupName);
        for(DayOfWeek i : days)
            pairs.addAll(i.getPairs());
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        return pairs.get(position).type;
    }

    @NonNull
    @Override
    public TimetableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){

        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TimetableViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case 0:
                PairType0ViewHolder viewHolder = (PairType0ViewHolder) holder;
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
