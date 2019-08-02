package com.scdevs.helpyourshelf;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainPageViewAdapter extends FragmentPagerAdapter {

    public MainPageViewAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return ShelvesFragment.newInstance();
            case 1:
                return CameraFragment.newInstance();
            case 2:
                return RecommendedFragment.newInstance();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
