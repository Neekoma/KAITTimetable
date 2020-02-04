package com.nicholas.timetable.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.navigation.Navigation
import com.nicholas.timetable.R
import com.nicholas.timetable.TimetableBinder
import com.nicholas.timetable.networking.RequestSender
import com.nicholas.timetable.networking.Sendable
import com.nicholas.timetable.viewmodels.TimetableViewModel

class LoadFragment : Fragment(), Sendable, Runnable{

    private companion object val TAG = "LoadFragment"



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        loadData()
        return inflater.inflate(R.layout.fragment_load, container, false)
    }

    private fun loadData() {
       val timerThread = Thread(this)
        timerThread.start()
    }

    override fun getSendCallbackResult(result: Boolean) {
        if(result) {
            TimetableBinder.writeNewTimetableJson(context, RequestSender.getInstance().lastJson)
            Navigation.findNavController(view!!).navigate(R.id.action_loadFragment_to_timetableFragment)
        }
        else
            if(TimetableBinder.haveSave(context)) {
                TimetableViewModel.getInstance().groups = TimetableBinder.getLocalGroups(context)
                Navigation.findNavController(view!!).navigate(R.id.action_loadFragment_to_timetableFragment)
            }
            else
                Navigation.findNavController(view!!).navigate(R.id.action_loadFragment_to_loadingErrorFragment)
    }

    override fun run(){
        RequestSender.getInstance().update(this);
    }


}
