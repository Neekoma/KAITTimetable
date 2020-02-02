package com.nicholas.timetable.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.nicholas.timetable.R
import kotlinx.android.synthetic.main.fragment_loading_error.view.*

class LoadingErrorFragment : Fragment(), View.OnClickListener {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val mView = inflater.inflate(R.layout.fragment_loading_error, container, false)
        mView.loading_error_try_again.setOnClickListener(this)
        return mView
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.loading_error_try_again -> {
                Navigation.findNavController(view!!).navigate(R.id.action_loadingErrorFragment_to_loadFragment)
            }
        }
    }


}
