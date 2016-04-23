/*
 *  ---------------------------------------------------------------------------
 *  Copyright (C) 2016, Davide Baj - All Rights Reserved
 *
 *  Project name: Nepy
 *  Filename: Settings.java
 *  Author: Davide Baj
 *  -------------------------------------------------------------------------
 */

package com.davidebaj.nepy;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by davide on 23/04/2016.
 */
public class Settings {

    private static final String TAG = "Settings";
    private static final String sharedPreferenceFilename = "com.davidebaj.nepy.shared_prefs";
    public static final String soundKey = "com.davidebaj.nepy.sound";
    private static final int DEFAULT_SOUND = 1;
    private static Settings settings;
    private static SharedPreferences sharedPreferences;

    /**
     * Use static method instead
     */
    private Settings() {}

    /**
     * Singleton
     * @param aContext - a context
     * @return - a Settings obj
     */
    public static Settings buildSettings(Context aContext) {

        sharedPreferences = aContext.getSharedPreferences(sharedPreferenceFilename, 0);

        if (settings == null) {
            settings = new Settings();
        }

        return settings;
    }

    /**
     * Is sound enabled?
     * @return - boolean
     */
    public boolean isSoundOn() {
        return sharedPreferences.getInt(soundKey, DEFAULT_SOUND) == 1;
    }

    /**
     * Enable / disable sound
     * @param flag [true | false]
     */
    public void setSound(boolean flag) {
        sharedPreferences.edit().putInt(soundKey, flag ? 1 : 0).commit();
    }

}
