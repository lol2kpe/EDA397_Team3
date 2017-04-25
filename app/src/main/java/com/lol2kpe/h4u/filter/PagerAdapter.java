package com.lol2kpe.h4u.filter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Jonathan Granström
 * User: Jonathan "juntski" Granström
 * Date: 2017-04-24
 */
class PagerAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;
    private PlaceFragment placeFragment;
    private SymptomFragment symptomFragment;

    PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                if (placeFragment == null)
                    placeFragment = new PlaceFragment();
                return placeFragment;
            case 1:
                if (symptomFragment == null)
                    symptomFragment = new SymptomFragment();
                return symptomFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
