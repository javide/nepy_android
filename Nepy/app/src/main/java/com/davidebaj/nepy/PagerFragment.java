/*
 *  ---------------------------------------------------------------------------
 *  Copyright (C) 2016, Davide Baj - All Rights Reserved
 *
 *  Project name: Nepy
 *  Filename: PagerActivity.java
 *  Author: Davide Baj
 *  -------------------------------------------------------------------------
 */

package com.davidebaj.nepy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.davidebaj.nepy.dao.Plant;

import java.util.List;
import java.util.Vector;

/**
 * Created by davide on 27/02/16.
 */
public class PagerFragment extends Fragment {

    private static final String TAG = "PagerFragment";
    private Plant plant;
    private ViewPagerAdapter mAdapter;

    public static PagerFragment newInstance(Plant aPlant) {
        PagerFragment f = new PagerFragment();
        f.plant = aPlant;

        Log.d(TAG, "created new pagerFragment for " + aPlant.getSpecies());
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        List<Fragment> fragments = new Vector<>();
        fragments.add(PhotoFragment.newInstance(plant, 1));
        fragments.add(PhotoFragment.newInstance(plant, 2));
        mAdapter  = new ViewPagerAdapter(getChildFragmentManager(), fragments);

        View view = inflater.inflate(R.layout.pager, container, false);

        ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(mAdapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG, "onPageScrolled " + position);
                Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
                if (position == 0) {
                    toolbar.setTitle(plant.getSpecies() + " (" + plant.getPitcherType() + ")");
                } else if (position == 1) {
                    toolbar.setTitle(plant.getSpecies() + " (" + plant.getPitcherType2() + ")");
                }
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(TAG, "onPageScrollStateChanged");

            }
        });

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(plant.getSpecies() + " (" + plant.getPitcherType() + ")");

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated for " + plant.getSpecies());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
