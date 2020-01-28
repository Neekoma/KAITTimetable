package com.nicholas.timetable.lists.Timetable;

import android.content.Context;
import android.util.Log;
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
        Log.d("DEBUG", "rv constructor");
        switchGroup(groupName);
    }

    public TimetableRecyclerViewAdapter(String groupName){
        switchGroup(groupName);
    }

    private void deleteSuperfluous(){
        for(int i = 0; i < dataset.size(); i++){
            if(dataset.get(i) instanceof Pair){
                Pair j = (Pair) dataset.get(i);
                if(j.type != 0) {
                    dataset.remove(i);
                    deleteSuperfluous();
                }
            }
        }
    }
    public void switchGroup(String groupName){
        if(dataset.size() > 0)
            dataset.clear();
        List<DayOfWeek> days = TimetableViewModel.getInstance().getGroups().get(groupName);
        for(DayOfWeek i : days) {
            dataset.add(new TableHeader(i.getDayName()));
            dataset.addAll(i.getPairs());
        }
        deleteSuperfluous();
        notifyDataSetChanged();
        Log.d("DEBUG", "switch");
    }


    @Override
    public int getItemViewType(int position) {
        if(dataset.get(position) instanceof Pair){
            Pair pair = (Pair)dataset.get(position);
            if(pair.type == 0) {
                Log.d("DEBUG", "pair viewtype");
                return 0;
            }
        }
        else if(dataset.get(position) instanceof TableHeader) {
            Log.d("DEBUG", "header viewtype");
            return TableHeader.TABLE_HEADER_VIEW_TYPE; // 0x00A1
        }
        Log.d("DEBUG", "other view type");
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
                Log.d("DEBUG", "Viewholder 0");
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
                Log.d("DEBUG", "Viewholder header");
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TimetableViewHolder holder, int position) {
        Log.d("DEBUG", "on bind");
        holder.bind(dataset, position);
    }

    @Override
    public int getItemCount() {
        Log.d("DEBUG", "item count " + dataset.size());
       return dataset.size();
    }
}
