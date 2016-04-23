/*
 *  ---------------------------------------------------------------------------
 *  Copyright (C) 2016, Davide Baj - All Rights Reserved
 *
 *  Project name: Nepy
 *  Filename: PagerFragment.java
 *  Author: Davide Baj
 *  -------------------------------------------------------------------------
 */

package com.davidebaj.nepy.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.davidebaj.nepy.R;
import com.davidebaj.nepy.adapters.ViewPagerAdapter;
import com.davidebaj.nepy.dao.Plant;

import java.util.List;
import java.util.Vector;

/**
 * Created by davide on 27/02/16.
 */
public class PagerFragment extends Fragment {

    private static final String TAG = "PagerFragment";
    private Plant plant;

    public static PagerFragment newInstance(Plant aPlant) {
        PagerFragment f = new PagerFragment();
        f.plant = aPlant;

        //Log.d(TAG, "created new pagerFragment for " + aPlant.getSpecies());
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(plant.getSpecies() + " (" + plant.getPitcherType() + ")");

        List<Fragment> fragments = new Vector<>();
        fragments.add(PhotoFragment.newInstance(plant, 1));
        fragments.add(PhotoFragment.newInstance(plant, 2));
        fragments.add(DescriptionFragment.newInstance(plant));
        ViewPagerAdapter mAdapter = new ViewPagerAdapter(getChildFragmentManager(), fragments);

        View view = inflater.inflate(R.layout.pager, container, false);

        ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(mAdapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private int currentPosition;
            private Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
            private String species = plant.getSpecies();
            private String pitcherType1 = species + " (" + plant.getPitcherType() + ")";
            private String pitcherType2 = species + " (" + plant.getPitcherType2() + ")";

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                currentPosition = position;
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                if (currentPosition == 0) {
                    toolbar.setTitle(pitcherType1);
                } else if (currentPosition == 1) {
                    toolbar.setTitle(pitcherType2);
                } else if (currentPosition == 2) {
                    toolbar.setTitle(species);
                } else {
                    toolbar.setTitle("");
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
