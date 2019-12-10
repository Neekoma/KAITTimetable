package com.nicholas.timetable.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.nicholas.timetable.R;
import com.nicholas.timetable.users.Auth;
import com.nicholas.timetable.viewmodels.HomeFragmentViewModel;


public class HomeFragment extends Fragment implements View.OnClickListener{

    private TextView username;
    private EditText logEd, passEd;
    private Button entraceButton;

    public HomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        logEd = view.findViewById(R.id.loginEditText);
        passEd = view.findViewById(R.id.passwordEditText);
        entraceButton = view.findViewById(R.id.entrace_button);
        username = view.findViewById(R.id.userNameTv);
        entraceButton.setOnClickListener(this);

        restoreFromViewModel();

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.entrace_button:
                if(Auth.auth(logEd.getText().toString(), passEd.getText().toString())) {
                    Snackbar.make(v, "Вы успешно авторизовались в системе!", Snackbar.LENGTH_SHORT).show();
                    logEd.setVisibility(View.GONE);
                    passEd.setVisibility(View.GONE);
                    username.setText(getString(R.string.str_you_logged_as_tester));
                    HomeFragmentViewModel.logged = true;
                }
                else {
                    Snackbar.make(v, "FAIL", Snackbar.LENGTH_SHORT).show();
                    passEd.setText("");
                }
                break;
        }
    }


    private void restoreFromViewModel(){
        if(HomeFragmentViewModel.logged){
            logEd.setVisibility(View.GONE);
            passEd.setVisibility(View.GONE);
            entraceButton.setVisibility(View.GONE);
            username.setText(getString(R.string.str_you_logged_as_tester));
        }
    }
}
