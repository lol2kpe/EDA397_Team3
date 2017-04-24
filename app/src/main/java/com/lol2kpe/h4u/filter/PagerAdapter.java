package com.lol2kpe.h4u.filter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Jonathan on 2017-04-24.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    PlaceFragment placeFragment;
    SymptomFragment symptomFragment;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                if (placeFragment == null)
                    placeFragment = new PlaceFragment();
                return new PlaceFragment();
            case 1:
                if (symptomFragment== null)
                    symptomFragment = new SymptomFragment();
                return new SymptomFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
