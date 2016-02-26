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

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davide on 23/02/16.
 */
public class PlantListView extends Fragment {

    private static final String TAG = "PlantListView";
    private String title;

    public static final PlantListView newInstance(String title) {
        PlantListView f = new PlantListView();
        f.title = title;
        Log.d(TAG, "Plant list view instantiated ***********************");
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "Plant list view creating ***********************");
        View view = inflater.inflate(R.layout.list_view, container, false);
        Log.d(TAG, "Plant list view created ***********************");

        Activity activity = (Activity) getContext();
        activity.setTitle(title);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        Log.d(TAG, "Plant list view onViewCreated ***********************");
        List<Plant> plantList = MainActivity.resources.getPlantsObj().getPlants();
        List<String> speciesNames = new ArrayList<>();

        for (Plant plant : plantList) {
            speciesNames.add(plant.getSpecies());
        }

        PlantArrayAdapter adapter = new PlantArrayAdapter(getActivity(), speciesNames.toArray(new String[speciesNames.size()]));

        ListView listview = (ListView) view.findViewById(R.id.my_listView);
        listview.setAdapter(adapter);

        // add listener to each row
        /*listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Nep aNep = (Nep) nep_species_array.get(position);

                // get the nep_id of all Nepenthes in this ListView
                String[] nep_id_array = new String[nep_species_array.size()];
                for (int i = 0; i < nep_species_array.size(); i++) {
                    nep_id_array[i] = nep_species_array.get(i).nep_id;

                }

                // position: in the list of Nepenthes
                // nep_id_array.length: the size of the list of Nepenthes
                // nep_id_array: the array of nep_ids in the list
                Log.d(TAG, "Clicked position=" + position + " nepenthes=" + aNep.species + "list of neps lenght = "
                        + nep_id_array.length + " array=" + Arrays.toString(nep_id_array));
                if (mCallbacks != null) {
                    mCallbacks.onListItemSelected(position, nep_id_array);
                }
            }
        });

*/
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "Plant list view onActivityCreated 1 ***********************");
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "Plant list view onActivityCreated 2 ***********************");
    }

}
