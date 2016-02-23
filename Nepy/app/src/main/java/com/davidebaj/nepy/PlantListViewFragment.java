/*
 *  ---------------------------------------------------------------------------
 *  Copyright (C) 2016, Davide Baj - All Rights Reserved
 *
 *  Project name: Nepy
 *  Filename: PlantListViewFragment.java
 *  Author: Davide Baj
 *  -------------------------------------------------------------------------
 */

package com.davidebaj.nepy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by davide on 23/02/16.
 */
public class PlantListViewFragment extends Fragment {

    private static final String TAG = "PlantListViewFragment";

    public static final PlantListViewFragment newInstance(int id, String message, String region_code) {
        PlantListViewFragment f = new PlantListViewFragment();
        Bundle bdl = new Bundle(2);
        bdl.putInt("ID", id);
        bdl.putString("MESSAGE", message);
        f.setArguments(bdl);
        Log.d(TAG, "Plant list view instantiated ***********************");
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "Plant list view creating ***********************");
        View view = inflater.inflate(R.layout.list_view, container, false);
        Log.d(TAG, "Plant list view created ***********************");

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.d(TAG, "Plant list view onViewCreated ***********************");

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "Plant list view onActivityCreated 1 ***********************");
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "Plant list view onActivityCreated 2 ***********************");
    }

}
