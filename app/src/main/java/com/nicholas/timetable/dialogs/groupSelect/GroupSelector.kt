package com.nicholas.timetable.dialogs.groupSelect

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicholas.timetable.R
import com.nicholas.timetable.Updateable
import com.nicholas.timetable.fragments.TimetableFragment
import kotlinx.android.synthetic.main.choose_group_dialog.view.*


class GroupSelector {

    var dialog: AlertDialog? = null

    fun showGroupSelector(timetableFragment: TimetableFragment, cancelable: Boolean){

        val builder = AlertDialog.Builder(timetableFragment.context!!)
        val selectorView = LayoutInflater.from(timetableFragment.context!!).inflate(R.layout.choose_group_dialog, null, false)
        val groupEditText = selectorView.chooseGroupEditText
        val selectorRV = selectorView.chooseGroupRecyclerView
        selectorRV.adapter = GroupSelectorRVAdapter(this, groupEditText, timetableFragment)
        selectorRV.layoutManager = LinearLayoutManager(timetableFragment.context!!)
        builder.setCancelable(cancelable)
        builder.setView(selectorView)
        dialog = builder.create()
        dialog!!.show()
    }

    fun hideGroupSelector(){
       dialog!!.dismiss()
    }
}