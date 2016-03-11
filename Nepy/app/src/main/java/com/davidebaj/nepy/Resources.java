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
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

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
    private static String aboutTemplate;

    private Resources() {
        parsePlantsFile();
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

    /**
     *
     */
    private static void parsePlantsFile() {

        Log.d(TAG, "Parsing Plants file");

        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(languageCode + "/plants.json");
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

    /**
     * Loads all the properties in memory for the language of this object
     */
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

    /**
     * Returns the value of a given property
     * @param propertyName - the key name of the property
     * @return - the value of the property
     */
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

    /**
     * Returns a set of strings of all or matching property key names
     * @return - a set of strings (not ordered)
     */
    public Set<String> getAllProperties(String filter) {

        if (filter == null) {
            return properties.stringPropertyNames();
        }

        Set<String> propertySet = new HashSet<>();
        String regexp = "^" + filter + ".*$";

        for (String p : properties.stringPropertyNames()) {
            if (p.matches(regexp)) {
                propertySet.add(p);
            }
        }

        return propertySet;
    }

    /**
     * Returns the plant description page template
     * @return - String
     */
    public String getDescriptionTemplate() {

        if (plantDescriptionTemplate != null) {
            return plantDescriptionTemplate;
        }

        plantDescriptionTemplate = loadTemplate("plant_description.html");
        return plantDescriptionTemplate;
    }

    /**
     * Returns the about page template
     * @return - String
     */
    public String getAboutTemplate() {

        if (aboutTemplate != null) {
            return aboutTemplate;
        }

        aboutTemplate = loadTemplate("about.html");
        return aboutTemplate;
    }

    /**
     * Returns a template string loaded from a resource filename
     * @param filename - resource
     * @return - the content of the file
     */
    private String loadTemplate(String filename) {

        String template = "";

        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(filename);
            template = IOUtils.toString(inputStream, "UTF-8");
        } catch (IOException e) {
            Log.e(TAG, "Unable to find template" + e.getMessage());
        }

        return template;
    }

}
