/*
 *  ---------------------------------------------------------------------------
 *  Copyright (C) 2016, Davide Baj - All Rights Reserved
 *
 *  Project name: Nepy
 *  Filename: SoundFragment.java
 *  Author: Davide Baj
 *  -------------------------------------------------------------------------
 */

package com.davidebaj.nepy.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.davidebaj.nepy.R;

/**
 * Created by davide on 18/04/16.
 */
public class SoundFragment extends Fragment {

    private static final String TAG = "SoundFragment";
    private String title;

    public static SoundFragment newInstance(String title) {
        SoundFragment f = new SoundFragment();
        f.title = title;
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sound, container, false);
        view.setClickable(true);
        ((Activity) getContext()).setTitle(title);

        Switch soundSwitch = (Switch) view.findViewById(R.id.sound_switch);

        //set the switch to ON
        soundSwitch.setChecked(true);

        //attach a listener to check for changes in state
        soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    //switchStatus.setText("Switch is currently ON");
                    Log.d(TAG, "ON --------------------------------");
                }else{
                   // switchStatus.setText("Switch is currently OFF");
                    Log.d(TAG, "OFF --------------------------------");
                }

            }
        });

        //check the current state before we display the screen
       /* if(mySwitch.isChecked()){
            switchStatus.setText("Switch is currently ON");
        }
        else {
            switchStatus.setText("Switch is currently OFF");
        }
        */

        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    }

}