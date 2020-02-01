package com.nicholas.timetable

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.nicholas.timetable.networking.JsonReceiver
import com.nicholas.timetable.networking.RequestSender
import com.nicholas.timetable.networking.Sendable
import com.nicholas.timetable.viewmodels.TimetableViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

import io.reactivex.schedulers.Schedulers


class UpdatesChecker(private val context: Context) : Sendable{

    val receiver = JsonReceiver()

    private fun dataSourse() : Observable<String> {
        return Observable.create { subscriber->
            while(true){
                Thread.sleep(6000)
                RequestSender.getInstance().update(this)
            }
        }
    }

    fun startChecking(){
        val dispose = dataSourse()
                .subscribeOn(Schedulers.newThread())
                .subscribe({
                    Toast.makeText(context, "Расписание обновлено", Toast.LENGTH_LONG).show()
                },{

                })
    }

    override fun getSendCallbackResult(result: Boolean) {
        if(result){
            TimetableBinder.writeNewTimetableJson(context, RequestSender.getInstance().lastJson)
            TimetableViewModel.getInstance().getAdapter(context).switchGroup(TimetableViewModel.getInstance().currentGroupName)
        }
        else{

        }
    }


}