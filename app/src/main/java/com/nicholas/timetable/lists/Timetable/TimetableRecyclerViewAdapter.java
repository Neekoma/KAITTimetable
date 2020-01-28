package com.nicholas.timetable.lists.Timetable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nicholas.timetable.R;
import com.nicholas.timetable.lists.IListDataset;
import com.nicholas.timetable.lists.TableHeader;
import com.nicholas.timetable.lists.Timetable.ViewHolders.DayNameViewHolder;
import com.nicholas.timetable.lists.Timetable.ViewHolders.PairType0ViewHolder;
import com.nicholas.timetable.models.DayOfWeek;
import com.nicholas.timetable.models.Pair;
import com.nicholas.timetable.viewmodels.TimetableViewModel;

import java.util.ArrayList;
import java.util.List;

public class TimetableRecyclerViewAdapter extends RecyclerView.Adapter<TimetableViewHolder> {

    private ArrayList<IListDataset> dataset = new ArrayList<>();

    private Context context;

    public TimetableRecyclerViewAdapter(Context context, String groupName){
        this.context = context;
        switchGroup(groupName);
    }

    public void switchGroup(String groupName){
        if(dataset.size() > 0)
            dataset.clear();
        List<DayOfWeek> days = TimetableViewModel.getInstance().getGroups().get(groupName);
        for(DayOfWeek i : days) {
            dataset.add(new TableHeader(i.getDayName()));
            dataset.addAll(i.getPairs());
        }
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        if(dataset.get(position) instanceof Pair){
            Pair pair = (Pair)dataset.get(position);
            return pair.type; //[0;7]
        }
        else if(dataset.get(position) instanceof TableHeader)
            return TableHeader.TABLE_HEADER_VIEW_TYPE; // 0x00A1
        return -1;
    }

    @NonNull
    @Override
    public TimetableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = null;
       TimetableViewHolder viewHolder = null;
        switch (viewType){
            case 0:
                view = LayoutInflater.from(context).inflate(R.layout.pair_type_0, parent, false);
                viewHolder = new PairType0ViewHolder(view);
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
            case TableHeader.TABLE_HEADER_VIEW_TYPE:
                view = LayoutInflater.from(context).inflate(R.layout.day_of_week, parent, false);
                viewHolder = new DayNameViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TimetableViewHolder holder, int position) {
        holder.bind(dataset, position);
    }

    @Override
    public int getItemCount() {
       return dataset.size();
    }
}
