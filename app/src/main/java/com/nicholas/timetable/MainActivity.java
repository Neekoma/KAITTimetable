package com.nicholas.timetable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.nicholas.timetable.adapters.SectionsPageAdapter;
import com.nicholas.timetable.time.Timer;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private SectionsPageAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        initPageAdapter(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        Timer.getInstance().startTimer();
    }


    private void initWidgets(){
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.fragment_container);
    }
    private void initPageAdapter(ViewPager viewPager){
        pageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        pageAdapter.addFragment(new TimetableFragment(), getString(R.string.timetable_tab_title));
        pageAdapter.addFragment(new ReplacementFragment(), getString(R.string.replacements_tab_title));
        viewPager.setAdapter(pageAdapter);
    }


}
