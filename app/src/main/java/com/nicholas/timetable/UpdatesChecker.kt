package com.nicholas.timetable

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.nicholas.timetable.JsonHandler.Handler
import com.nicholas.timetable.networking.JsonReceiver
import com.nicholas.timetable.networking.RequestSender
import com.nicholas.timetable.networking.Sendable
import com.nicholas.timetable.viewmodels.TimetableViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

import io.reactivex.schedulers.Schedulers


class UpdatesChecker(private val context: Context) : Sendable {

    private fun dataSourse(): Observable<String> {
        return Observable.create { subscriber ->
            while (true) {
                Thread.sleep(15 * 1000)
                RequestSender.getInstance().update(this)
            }
        }
    }

    fun startChecking() {
        val dispose = dataSourse()
                .subscribeOn(Schedulers.newThread())
                .subscribe({
                    Toast.makeText(context, "Расписание обновлено", Toast.LENGTH_LONG).show()
                    Log.d("DEBUG", "Расписание обновлено")
                }, {

                })
    }

    override fun getSendCallbackResult(result: Boolean) {
        if (result) {
            val dispose = TimetableBinder.timetableComparator(context, RequestSender.getInstance().lastJson)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.d("DEBUG", "From subscriber " + it)
                        if(!it){
                            val handler = Handler()
                            TimetableViewModel.getInstance().groups = handler.setGroups(RequestSender.getInstance().lastJson)
                            TimetableViewModel.getInstance().getAdapter(context).switchGroup(TimetableViewModel.getInstance().currentGroupName)
                            Log.d("DEBUG", "Расписание не равно")
                        } else{
                            Log.d("DEBUG", "Расписание равно")
                        }
                    }
        }
    }


}