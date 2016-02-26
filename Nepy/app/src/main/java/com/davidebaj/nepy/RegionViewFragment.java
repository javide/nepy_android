/*
 *  ---------------------------------------------------------------------------
 *  Copyright (C) 2016, Davide Baj - All Rights Reserved
 *
 *  Project name: Nepy
 *  Filename: RegionViewFragment.java
 *  Author: Davide Baj
 *  -------------------------------------------------------------------------
 */

package com.davidebaj.nepy;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davide on 26/02/16.
 */
public class RegionViewFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "RegionViewFragment";
    private String title;
    Button button1, button2, button3, button4, button5, button6, button7;

    public static RegionViewFragment newInstance(String title) {
        RegionViewFragment f = new RegionViewFragment();
        f.title = title;
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.region_view, container, false);
        view.setClickable(true);

        ((Activity) getContext()).setTitle(title);

        button1 = (Button) view.findViewById(R.id.ButtonRegion1);
        button1.setText(MainActivity.resources.getProperty("label.REGION_1"));
        button1.setOnClickListener(this);

        button2 = (Button) view.findViewById(R.id.ButtonRegion2);
        button2.setText(MainActivity.resources.getProperty("label.REGION_2"));
        button2.setOnClickListener(this);

        button3 = (Button) view.findViewById(R.id.ButtonRegion3);
        button3.setText(MainActivity.resources.getProperty("label.REGION_3"));
        button3.setOnClickListener(this);

        button4 = (Button) view.findViewById(R.id.ButtonRegion4);
        button4.setText(MainActivity.resources.getProperty("label.REGION_4"));
        button4.setOnClickListener(this);

        button5 = (Button) view.findViewById(R.id.ButtonRegion5);
        button5.setText(MainActivity.resources.getProperty("label.REGION_5"));
        button5.setOnClickListener(this);

        button6 = (Button) view.findViewById(R.id.ButtonRegion6);
        button6.setText(MainActivity.resources.getProperty("label.REGION_6"));
        button6.setOnClickListener(this);

        button7 = (Button) view.findViewById(R.id.ButtonRegion7);
        button7.setText(MainActivity.resources.getProperty("label.REGION_7"));
        button7.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Watch for button clicks
    }

    @Override
    public void onClick(View v) {

        String regionCode = null;
        String regionName = null;

        if (button1.getId() == ((Button) v).getId()) {
            regionCode = "1";
            regionName = (String) button1.getText();
        } else if (button2.getId() == ((Button) v).getId()) {
            regionCode = "2";
            regionName = (String) button2.getText();
        } else if (button3.getId() == ((Button) v).getId()) {
            regionCode = "3";
            regionName = (String) button3.getText();
        } else if (button4.getId() == ((Button) v).getId()) {
            regionCode = "4";
            regionName = (String) button4.getText();
        } else if (button5.getId() == ((Button) v).getId()) {
            regionCode = "5";
            regionName = (String) button5.getText();
        } else if (button6.getId() == ((Button) v).getId()) {
            regionCode = "6";
            regionName = (String) button6.getText();
        } else if (button7.getId() == ((Button) v).getId()) {
            regionCode = "7";
            regionName = (String) button7.getText();
        }

        if (regionCode == null || regionName == null) {
            return;
        }

        Log.d(TAG, "Clicked region:" + regionCode);

        List<Plant> plantList = MainActivity.resources.getPlantsObj().getPlantsByRegion(regionCode);
        List<String> speciesNames = new ArrayList<>();

        for (Plant plant : plantList) {
            speciesNames.add(plant.getSpecies());
        }

        ArrayAdapter adapter = new PlantArrayAdapter(getContext(), speciesNames.toArray(new String[speciesNames.size()]));
        PlantListViewFragment fragment = PlantListViewFragment.newInstance(regionName, adapter);

        // FragmentManager.enableDebugLogging(true);
        getFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.content_main, fragment)
                .commit();
    }

}
