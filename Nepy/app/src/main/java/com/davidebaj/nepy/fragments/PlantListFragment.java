/*
 *  ---------------------------------------------------------------------------
 *  Copyright (C) 2016, Davide Baj - All Rights Reserved
 *
 *  Project name: Nepy
 *  Filename: PlantListFragment.java
 *  Author: Davide Baj
 *  -------------------------------------------------------------------------
 */

package com.davidebaj.nepy.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.davidebaj.nepy.R;
import com.davidebaj.nepy.dao.Plant;

/**
 * Created by davide on 23/02/16.
 */
public class PlantListFragment extends Fragment {

    private static final String TAG = "PlantListFragment";
    private String title;
    private ArrayAdapter<Plant> adapter;
    private Callbacks mCallbacks;

    public static PlantListFragment newInstance(String title, ArrayAdapter<Plant> adapter) {

        PlantListFragment f = new PlantListFragment();
        f.title = title;
        f.adapter = adapter;

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(title);

        return inflater.inflate(R.layout.list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        ListView listview = (ListView) view.findViewById(R.id.plantListView);
        listview.setAdapter(adapter);

        // add listener to each row
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int index, long id) {

                //Log.d(TAG, "Position ----> " + index);
                Plant plant = adapter.getItem(index);
                //Log.d(TAG, "Plant ----> " + plant.getSpecies());

                if (mCallbacks != null) {
                    mCallbacks.onListItemSelected(plant);
                }
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    /**
     * Interface
     */
    public interface Callbacks {

        /**
         * The action to be taken upon selecting a plant
         * @param aPlant - a plant object
         */
        void onListItemSelected(Plant aPlant);
    }

    /**
     * Called when a fragment is first attached to its context.
     * {@link #onCreate(Bundle)} will be called after this.
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallbacks = (Callbacks) context;
        } catch (ClassCastException ex) {
            Log.e(TAG, "Casting the activity as a Callbacks listener failed " + ex);
            mCallbacks = null;
        }
    }
}
