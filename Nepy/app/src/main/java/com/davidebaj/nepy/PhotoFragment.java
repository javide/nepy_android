/*
 *  ---------------------------------------------------------------------------
 *  Copyright (C) 2016, Davide Baj - All Rights Reserved
 *
 *  Project name: Nepy
 *  Filename: PhotoFragment.java
 *  Author: Davide Baj
 *  -------------------------------------------------------------------------
 */

package com.davidebaj.nepy;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.davidebaj.nepy.dao.Plant;

import java.io.InputStream;

/**
 * Created by davide on 27/02/16.
 */
public class PhotoFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private static final String TAG = "PhotoFragment";
    private Plant plant;

    public static PhotoFragment newInstance(Plant aPlant, int position) {
        PhotoFragment f = new PhotoFragment();
        f.plant = aPlant;
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.d(TAG, " on create view");

        View view = inflater.inflate(R.layout.photo, container, false);

        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        Log.d(TAG, " on view created");

        ((Activity) getContext()).setTitle(plant.getSpecies() + " (" + plant.getPitcherType() + ")");

        String plantFileName = "img/plants/" + getPlantFileName(plant.getSpecies(), "1");
        AssetManager assetManager = getContext().getAssets();
        Log.d(TAG, plantFileName);
        try {
            InputStream inputStream = assetManager.open(plantFileName);
            ImageView imageView = (ImageView) view.findViewById(R.id.photoView);
            imageView.setImageDrawable(Drawable.createFromStream(inputStream, ""));

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            Log.d(TAG, "iconFileName = " + plantFileName);
        }
    }

    /**
     * Derives the plant filename from the species description (e.g. "N. adnata" --> adnata_1.jpg)
     *
     * @param speciesName - a species name such as "N. adnata"
     * @return a filename such as adnata_1.jpg
     */
    private String getPlantFileName(String speciesName, String sequenceNumber) {
        String[] parts = speciesName.split(" ");
        return parts[1] + "_" + sequenceNumber + ".jpg";
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d(TAG, "page scrolled");

    }

    @Override
    public void onPageSelected(int position) {
        Log.d(TAG, "page selected");

        // TODO: change title
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.d(TAG, "page scroll state changed");
    }

}
