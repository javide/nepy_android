/*
 *  ---------------------------------------------------------------------------
 *  Copyright (C) 2016, Davide Baj - All Rights Reserved
 *
 *  Project name: Nepy
 *  Filename: Resources.java
 *  Author: Davide Baj
 *  -------------------------------------------------------------------------
 */

package com.davidebaj.nepy;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;

/**
 * Created by davide on 21/02/16.
 */
public class Resources {

    private static final String TAG = "Resources";

    private static Resources resources;
    private static String languageCode;
    private static Plants plants;
    private static Context context;

    private Resources() {
        parseNepenthesFile();
    }

    public static Resources getResources(Context aContext, String languageCodeStr) {

        Log.d(TAG, "Creating object");
        context = aContext;

        if (resources == null && (languageCode == null || !languageCode.equals(languageCodeStr))) {
            languageCode = languageCodeStr;
            resources = new Resources();
        }

        return resources;
    }

    public Plants getPlantsObj() {
        return plants;
    }

    private static void parseNepenthesFile() {

        Log.d(TAG, "Parsing Nepenthes file");

        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(languageCode + "/nepenthes.json");
            String jsonString = IOUtils.toString(inputStream, "UTF-8");

            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.enable(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY);
            JsonNode rootNode = mapper.readTree(jsonString);
            plants = mapper.readValue(rootNode.traverse(), Plants.class);

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

    }

}
