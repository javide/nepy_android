/*
 *  ---------------------------------------------------------------------------
 *  Copyright (C) 2016, Davide Baj - All Rights Reserved
 *
 *  Project name: Nepy
 *  Filename: PagerAdapter.java
 *  Author: Davide Baj
 *  -------------------------------------------------------------------------
 */

package com.davidebaj.nepy;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.List;

/**
 * Created by davide on 27/02/16.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = "ViewPagerAdapter";
    private List<Fragment> fragments;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
        Log.d(TAG, "Created adapter");
    }

    @Override
    public int getCount() {
        Log.d(TAG, "getCount()");
        return this.fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        Log.d(TAG, "getItem " + position);
        return this.fragments.get(position);
    }
}

