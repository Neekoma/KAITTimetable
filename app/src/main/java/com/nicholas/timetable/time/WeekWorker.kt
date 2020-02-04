package com.nicholas.timetable.time

import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.TextView
import com.nicholas.timetable.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class WeekWorker(private val context: Context, private val dayTv: TextView,
                 private val dateRangeTv: TextView, private val weekParityTv: TextView) {

    private val months = arrayOf("января", "февраля", "марта", "апреля", "мая", "июня",
            "июля", "августа", "сентября", "октября", "ноября", "декабря")

    private lateinit var disposable: Disposable

    fun startWeekWorkerJob() {
        disposable = looper()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    dayTv.text = it[0]
                    dateRangeTv.text = it[1]
                    weekParityTv.text = it[2]
                }, {

                })
    }
    fun stopWeekWorker(){
        if(!disposable.isDisposed)
            disposable.dispose()
    }


    private fun currentDay(): SpannableStringBuilder {
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val builder = SpannableStringBuilder("Сегодня $day ${months[month]}")
        val textSpan = StyleSpan(Typeface.BOLD)
        builder.setSpan(textSpan, 7, builder.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return builder
    }

    private fun dateRange(): String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        val monday = Integer.toString((calendar.get(Calendar.DAY_OF_MONTH)))
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        val sunday = Integer.toString((calendar.get(Calendar.DAY_OF_MONTH)))
        return "$monday — $sunday ${months[calendar.get(Calendar.MONTH)]}"
    }

    private fun looper(): Observable<Array<CharSequence>> {
        return Observable.create { subscriber ->
            while (true) {
                subscriber.onNext(arrayOf(currentDay(), dateRange(), week()))
                Thread.sleep(3 * 1000)
            }
        }
    }

    private fun week() : CharSequence{
        val calendar = Calendar.getInstance()
        val week = calendar.get(Calendar.WEEK_OF_YEAR)
        if (week % 2 == 0)
            return setOddWeek() // Четная
        else
           return setEvenWeek() // Нечетная
    }

    private fun setOddWeek() : CharSequence{
        val text = "ЧЕТНАЯ"
        val ss = SpannableString(text)
        val fcsBlue = ForegroundColorSpan(context.getResources().getColor(R.color.oddWeekBlue))
        ss.setSpan(fcsBlue, 0, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return ss
    }

    private fun setEvenWeek() : CharSequence{
        val text = "НЕЧЕТНАЯ"
        val ss = SpannableString(text)
        val fcsRed = ForegroundColorSpan(context.getResources().getColor(R.color.oddWeekRed))
        val fcsBlue = ForegroundColorSpan(context.getResources().getColor(R.color.oddWeekBlue))
        ss.setSpan(fcsRed, 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        ss.setSpan(fcsBlue, 2, ss.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return ss
    }
}