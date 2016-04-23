/*
 *  ---------------------------------------------------------------------------
 *  Copyright (C) 2016, Davide Baj - All Rights Reserved
 *
 *  Project name: Nepy
 *  Filename: SettingsFragment.java
 *  Author: Davide Baj
 *  -------------------------------------------------------------------------
 */

package com.davidebaj.nepy.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.davidebaj.nepy.MainActivity;
import com.davidebaj.nepy.R;
import com.davidebaj.nepy.Settings;

/**
 * Created by davide on 18/04/16.
 */
public class SettingsFragment extends Fragment {

    private static final String TAG = "SettingsFragment";
    private String title;
    private Settings settings;

    public static SettingsFragment newInstance(String title) {
        SettingsFragment f = new SettingsFragment();
        f.title = title;
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.settings, container, false);
        view.setClickable(true);
        ((Activity) getContext()).setTitle(title);

        final Switch soundSwitch = (Switch) view.findViewById(R.id.sound_switch);

        settings = Settings.buildSettings(getContext());

        if (settings.isSoundOn()) {
            soundSwitch.setChecked(true);
            soundSwitch.setText(MainActivity.resources.getProperty("settings.QUIZ_SOUND_ON"));
        } else {
            soundSwitch.setChecked(false);
            soundSwitch.setText(MainActivity.resources.getProperty("settings.QUIZ_SOUND_OFF"));
        }

        //attach a listener to check for changes in state
        soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    soundSwitch.setText(MainActivity.resources.getProperty("settings.QUIZ_SOUND_ON"));
                    settings.setSound(true);
                } else {
                    soundSwitch.setText(MainActivity.resources.getProperty("settings.QUIZ_SOUND_OFF"));
                    settings.setSound(false);
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    }

}