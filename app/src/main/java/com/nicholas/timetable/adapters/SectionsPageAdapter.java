package com.nicholas.timetable.adapters;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
;

public class SectionsPageAdapter extends FragmentPagerAdapter {

    private final ArrayList<Fragment> fragments = new ArrayList<>();
    private final ArrayList<String> framentsTitles = new ArrayList<>();


    public SectionsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, CharSequence title){
        fragments.add(fragment);
        framentsTitles.add(title.toString());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return framentsTitles.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
