package com.nicholas.timetable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.nicholas.timetable.adapters.SectionsPageAdapter;


public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private SectionsPageAdapter pageAdapter;

    private SwipeRefreshLayout swipeRefresh;

    private Fragment timetableFragment, replacemantFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initWidgets();
        initPageAdapter(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        initCallsFragment();
    }


    private void initWidgets(){
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.fragment_container);
        swipeRefresh = findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimaryDark));
    }

    private void initPageAdapter(ViewPager viewPager){
        pageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        timetableFragment = new TimetableFragment();
        replacemantFragment = new ReplacementFragment();
        pageAdapter.addFragment(timetableFragment, getString(R.string.timetable_tab_title));
        pageAdapter.addFragment(replacemantFragment, getString(R.string.replacements_tab_title));
        viewPager.setAdapter(pageAdapter);
    }

    private void initCallsFragment(){
        CallsFragment fragment = new CallsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.timerFragment_container, fragment);
        transaction.commit();
    }


}
