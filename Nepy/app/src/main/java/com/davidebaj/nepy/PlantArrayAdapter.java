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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.species_textview);

    //    String filename = "t" + nepIds[position] + ".jpg";
    //    InputStream is = getContext().getClass().getClassLoader().getResourceAsStream(filename);

     //   ImageView imageView = (ImageView) rowView.findViewById(R.id.my_icon);
     //   imageView.setImageDrawable(Drawable.createFromStream(is, ""));
        textView.setText(speciesNames[position]);

        return rowView;
    }

}

