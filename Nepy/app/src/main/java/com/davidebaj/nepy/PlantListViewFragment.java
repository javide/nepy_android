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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by davide on 23/02/16.
 */
public class PlantListViewFragment extends Fragment {

    private static final String TAG = "PlantListViewFragment";
    private String title;
    private ArrayAdapter adapter;

    public static PlantListViewFragment newInstance(String title, ArrayAdapter adapter) {

        PlantListViewFragment f = new PlantListViewFragment();
        f.title = title;
        f.adapter = adapter;

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list_view, container, false);
        ((Activity) getContext()).setTitle(title);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        ListView listview = (ListView) view.findViewById(R.id.plantListView);
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
        super.onActivityCreated(savedInstanceState);

    }

}
