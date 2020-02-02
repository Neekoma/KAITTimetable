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
import com.nicholas.timetable.lists.Timetable.ViewHolders.PairType1ViewHolder;
import com.nicholas.timetable.lists.Timetable.ViewHolders.PairType2ViewHolder;
import com.nicholas.timetable.lists.Timetable.ViewHolders.PairType3ViewHolder;
import com.nicholas.timetable.lists.Timetable.ViewHolders.PairType4ViewHolder;
import com.nicholas.timetable.models.DayOfWeek;
import com.nicholas.timetable.models.Pair;
import com.nicholas.timetable.viewmodels.TimetableViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
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
    //TODO: Удалить по окончании разработки вьюхолдеров
    private void deleteSuperfluous(){
        for(int i = 0; i < dataset.size(); i++){
            if(dataset.get(i) instanceof Pair){
                Pair j = (Pair) dataset.get(i);
                if(j.type != 0 && j.type != 1 && j.type != 2 && j.type != 3 && j.type != 4) {
                    dataset.remove(i);
                    deleteSuperfluous();
                }
            }
        }
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


    private Observable<Integer> groupSwitcher(String groupName){
        return Observable.create(subscriber->{
            if(dataset.size() > 0)
                dataset.clear();
            List<DayOfWeek> days = TimetableViewModel.getInstance().getGroups().get(groupName);
            for(DayOfWeek i : days) {
                dataset.add(new TableHeader(i.getDayName()));
                dataset.addAll(i.getPairs());
            }
            deleteSuperfluous();
            subscriber.onNext(1);
        });
    }

    //TODO: По окончании разработки вьюхолдеров, сделать по-нормальному
    @Override
    public int getItemViewType(int position) {
        if(dataset.get(position) instanceof Pair){
            Pair pair = (Pair)dataset.get(position);
            if(pair.type == 0)
                return 0;
            if(pair.type == 1)
                return 1;
            if(pair.type == 2)
                return 2;
            if(pair.type == 3)
                return 3;
            if(pair.type == 4)
                return 4;
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
