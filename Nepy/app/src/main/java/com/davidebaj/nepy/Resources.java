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

import com.davidebaj.nepy.dao.Plants;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by davide on 21/02/16.
 */
public class Resources {

    private static final String TAG = "Resources";

    private static Resources resources;
    private static String languageCode;
    private static Plants plants;
    private static Context context;
    private static Properties properties;
    private static String plantDescriptionTemplate;

    private Resources() {
        parseNepenthesFile();
        loadProperties();
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

    private static void loadProperties() {

        Log.d(TAG, "Loading translations");
        properties = new Properties();

        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(languageCode + "/tx.properties");
            properties.load(inputStream);

            Enumeration propertyNames = properties.propertyNames();

            while (propertyNames.hasMoreElements()) {
                String propertyName = (String) propertyNames.nextElement();
                Log.d(TAG, propertyName + " = " + properties.getProperty(propertyName));
            }

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public String getProperty(String propertyName) {

        String value = null;

        try {
            value = properties.getProperty(propertyName).trim();
        } catch (NullPointerException e) {
            Log.e(TAG, "'" + propertyName + "' property not found: " + e.getMessage());
            System.exit(1);
        }

        return value;
    }

    public String loadDescriptionTemplate() {

        if (plantDescriptionTemplate != null) {
            return plantDescriptionTemplate;
        }

        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("plant_description.html");
            plantDescriptionTemplate = IOUtils.toString(inputStream, "UTF-8");
        } catch (IOException e) {
            Log.e(TAG, "Unable to find plant description template" + e.getMessage());
            System.exit(1);
        }

        return plantDescriptionTemplate;
    }

}
