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
import com.nicholas.timetable.lists.Timetable.ViewHolders.PairType1ViewHolder;
import com.nicholas.timetable.lists.Timetable.ViewHolders.PairType2ViewHolder;
import com.nicholas.timetable.lists.Timetable.ViewHolders.PairType3ViewHolder;
import com.nicholas.timetable.lists.Timetable.ViewHolders.PairType4ViewHolder;
import com.nicholas.timetable.lists.Timetable.ViewHolders.PairType5ViewHolder;
import com.nicholas.timetable.lists.Timetable.ViewHolders.PairType6ViewHolder;
import com.nicholas.timetable.lists.Timetable.ViewHolders.PairType7ViewHolder;
import com.nicholas.timetable.models.DayOfWeek;
import com.nicholas.timetable.models.Pair;
import com.nicholas.timetable.viewmodels.TimetableViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TimetableRecyclerViewAdapter extends RecyclerView.Adapter<TimetableViewHolder> {

    private ArrayList<IListDataset> dataset = new ArrayList<>();

    private Context context;


    public TimetableRecyclerViewAdapter(Context context){
        this.context = context;
    }

    public TimetableRecyclerViewAdapter(String groupname){
        switchGroup(groupname);
    }

    public void switchGroup(String groupName){
        Disposable dispose = groupSwitcher(groupName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it->{
                    notifyDataSetChanged();
                },it->{

                });
    }


    private Single<Integer> groupSwitcher(String groupName){
        return Single.create(subscriber->{
            if(dataset.size() > 0)
                dataset.clear();
            List<DayOfWeek> days = TimetableViewModel.getInstance().getGroups().get(groupName);
            for(DayOfWeek i : days) {
                dataset.add(new TableHeader(i.getDayName()));
                dataset.addAll(i.getPairs());
            }
            subscriber.onSuccess(1);
        });
    }

    @Override
    public int getItemViewType(int position) {

        if(dataset.get(position) instanceof Pair){
            Pair pair = (Pair)dataset.get(position);
            return pair.type;
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
                view = LayoutInflater.from(context).inflate(R.layout.pair_type_3, parent, false);
                viewHolder = new PairType1ViewHolder(view);
                break;
            case 2:
                view = LayoutInflater.from(context).inflate(R.layout.pair_type_1, parent, false);
                viewHolder = new PairType2ViewHolder(view);
                break;
            case 3:
                view = LayoutInflater.from(context).inflate(R.layout.pair_type_2, parent, false);
                viewHolder = new PairType3ViewHolder(view);
                break;
            case 4:
                view = LayoutInflater.from(context).inflate(R.layout.pair_type_5, parent, false);
                viewHolder = new PairType4ViewHolder(view);
                break;
            case 5:
                view = LayoutInflater.from(context).inflate(R.layout.pair_type_4, parent, false);
                viewHolder = new PairType5ViewHolder(view);
                break;
            case 6:
                view = LayoutInflater.from(context).inflate(R.layout.pair_type_6, parent, false);
                viewHolder = new PairType6ViewHolder(view);
                break;
            case 7:
                view = LayoutInflater.from(context).inflate(R.layout.pair_type_7, parent, false);
                viewHolder = new PairType7ViewHolder(view);
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
