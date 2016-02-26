/*
 *  ---------------------------------------------------------------------------
 *  Copyright (C) 2016, Davide Baj - All Rights Reserved
 *
 *  Project name: Nepy
 *  Filename: PlantArrayAdapter.java
 *  Author: Davide Baj
 *  -------------------------------------------------------------------------
 */

package com.davidebaj.nepy;

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

import java.io.InputStream;

/**
 * Created by davide on 26/02/16.
 */
public class PlantArrayAdapter extends ArrayAdapter<String> {

    private static final String TAG = "PlantArrayAdapter";

    private final Context context;
    private final String[] speciesNames;

    public PlantArrayAdapter(Context context, String[] values) {
        super(context, R.layout.list_item, values);
        this.context = context;
        this.speciesNames = values;
    }

    /**
     * Displays the plant icon and its species name
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.species_textview);

        String iconFileName = "plant_icons/" + getPlantIconFileName(speciesNames[position]);
        AssetManager assetManager = context.getAssets();

        try {
            InputStream inputStream = assetManager.open(iconFileName);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.my_icon);
            imageView.setImageDrawable(Drawable.createFromStream(inputStream, ""));

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            Log.d(TAG, "iconFileName = " + iconFileName);
        }

        textView.setText(speciesNames[position]);

        return rowView;
    }

    /**
     * Derives the plant icon filename from the species description (e.g. "N. adnata" --> t_adnata_1.jpg)
     *
     * @param speciesName
     * @return
     */
    private String getPlantIconFileName(String speciesName) {
        String[] parts = speciesName.split(" ");
        return "t_" + parts[1] + "_1.jpg";
    }

}

