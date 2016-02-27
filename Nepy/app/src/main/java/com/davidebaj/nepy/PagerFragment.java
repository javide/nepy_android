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

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pager, container, false);
        ((Activity) getContext()).setTitle(plant.getSpecies());

        List<Fragment> fragments = new Vector<>();
        fragments.add(PhotoFragment.newInstance(plant, 1));
        fragments.add(PhotoFragment.newInstance(plant, 1));
        this.mAdapter  = new ViewPagerAdapter(super.getFragmentManager(), fragments);

        ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(this.mAdapter);

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
