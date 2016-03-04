/*
 *  ---------------------------------------------------------------------------
 *  Copyright (C) 2016, Davide Baj - All Rights Reserved
 *
 *  Project name: Nepy
 *  Filename: PlantArrayAdapter.java
 *  Author: Davide Baj
 *  -------------------------------------------------------------------------
 */

package com.davidebaj.nepy.adapters;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.davidebaj.nepy.R;
import com.davidebaj.nepy.dao.Plant;

import java.io.InputStream;

/**
 * Created by davide on 26/02/16.
 */
public class PlantArrayAdapter extends ArrayAdapter<Plant> {

    private static final String TAG = "PlantArrayAdapter";

    private final Context context;
    private final Plant[] plants;

    public PlantArrayAdapter(Context context, Plant[] plants) {
        super(context, R.layout.list_item, plants);
        this.context = context;
        this.plants = plants;
    }

    /**
     * Displays the plant icon and its species name
     *
     * @param index - the position of this plant in the array
     * @param convertView - the view
     * @param parent - the parent view
     * @return - the view of the row
     */
    @Override
    public View getView(int index, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.species_textview);

        String iconFileName = "img/plant_icons/" + getPlantIconFileName(plants[index].getSpecies());
        AssetManager assetManager = context.getAssets();

        try {
            InputStream inputStream = assetManager.open(iconFileName);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.my_icon);
            imageView.setImageDrawable(Drawable.createFromStream(inputStream, ""));

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            Log.d(TAG, "iconFileName = " + iconFileName);
        }

        textView.setText(plants[index].getSpecies());

        return rowView;
    }

    /**
     * Derives the plant icon filename from the species description (e.g. "N. adnata" --> t_adnata_1.jpg)
     *
     * @param speciesName - a species name such as "N. adnata"
     * @return a filename such as t_adnata_1.jpg
     */
    private String getPlantIconFileName(String speciesName) {
        String[] parts = speciesName.split(" ");
        return "t_" + parts[1] + "_1.jpg";
    }

}

