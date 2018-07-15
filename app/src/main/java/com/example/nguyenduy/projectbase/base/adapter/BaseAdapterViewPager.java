package com.example.nguyenduy.projectbase.base.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public abstract class BaseAdapterViewPager extends FragmentPagerAdapter {

    public BaseAdapterViewPager(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
