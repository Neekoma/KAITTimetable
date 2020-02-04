package com.nicholas.timetable.dialogs.groupSelect

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.nicholas.timetable.R
import com.nicholas.timetable.Updateable
import com.nicholas.timetable.fragments.TimetableFragment
import com.nicholas.timetable.lists.SimpleBindedViewHolder
import com.nicholas.timetable.viewmodels.TimetableViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.alert_dialog_groupname_item.view.*
import android.text.method.TextKeyListener.clear


class GroupSelectorRVAdapter(val selector: GroupSelector,
                             val editText: EditText ,
                             val timetableFragment: TimetableFragment) : RecyclerView.Adapter<GroupSelectorRVAdapter.GroupViewHolder>(){

    private var items: MutableList<String>? = null

init{
    items = mutableListOf()
    items!!.addAll(TimetableViewModel.getInstance().groupNames)
    val dispose = filter()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                notifyDataSetChanged()
            },{

            })
}

    private fun filter() : Observable<String>{
        return Observable.create {
            var length = editText.length()
            while(true){
                if(editText.length() != length){
                    items!!.clear()
                    for(i: String in TimetableViewModel.getInstance().groupNames)
                        if(i.toLowerCase().contains(editText.text.toString().toLowerCase()))
                           items!!.add(i)
                    length = editText.length()
                    it.onNext("OK")
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val view = LayoutInflater.from(timetableFragment.context!!).inflate(com.nicholas.timetable.R.layout.alert_dialog_groupname_item, parent, false)
        return GroupViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    inner class GroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), SimpleBindedViewHolder, View.OnClickListener{

        private val groupName = itemView.alertDialog_groupName_Tv
        private var pos : Int = 0

        override fun bind(position: Int) {
            groupName.text = items!![position]
            pos = position
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            TimetableViewModel.getInstance().currentGroupName = items!![pos]
            val preferences = timetableFragment.activity!!.getSharedPreferences("MY_SHARED", Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putString("SHARED_GROUP", groupName.text.toString());
            editor.commit()
            timetableFragment.update()
            selector.hideGroupSelector()
        }
    }

}