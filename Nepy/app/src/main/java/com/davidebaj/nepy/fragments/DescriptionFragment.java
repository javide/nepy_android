/*
 *  ---------------------------------------------------------------------------
 *  Copyright (C) 2016, Davide Baj - All Rights Reserved
 *
 *  Project name: Nepy
 *  Filename: DescriptionFragment.java
 *  Author: Davide Baj
 *  -------------------------------------------------------------------------
 */

package com.davidebaj.nepy.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.davidebaj.nepy.MainActivity;
import com.davidebaj.nepy.R;
import com.davidebaj.nepy.Resources;
import com.davidebaj.nepy.dao.Plant;

/**
 * Created by davide on 28/02/16.
 */
public class DescriptionFragment extends Fragment {

    private static final String TAG = "DescriptionFragment";
    private Plant plant;

    public static DescriptionFragment newInstance(Plant aPlant) {

        DescriptionFragment f = new DescriptionFragment();
        f.plant = aPlant;

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.description, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");

        String html;
        Resources res = MainActivity.resources;
        String template = res.getDescriptionTemplate();

        html = template.replace("$title_pronunciation", res.getProperty("desc.PRONUNCIATION"));
        html = html.replace("$pronunciation_1", plant.getPronunciation1());
        html = html.replace("$pronunciation_2", plant.getPronunciation2());
        html = html.replace("$title_distribution", res.getProperty("desc.DISTRIBUTION"));
        html = html.replace("$distribution", plant.getDistribution());
        html = html.replace("$title_range", res.getProperty("desc.RANGE"));
        html = html.replace("$range", plant.getRange());
        html = html.replace("$title_altitude", res.getProperty("desc.ALTITUDE"));
        html = html.replace("$altitude", plant.getAltitude());
        html = html.replace("$metre", res.getProperty("desc.METRE"));
        html = html.replace("$title_field_notes", res.getProperty("desc.FIELD_NOTES"));
        html = html.replace("$field_notes", plant.getFieldNotes());

        WebView webView = (WebView) getView().findViewById(R.id.webView);
        webView.setBackgroundColor(Color.BLACK);
        webView.loadData(html, "text/html; charset=UTF-8", null);

        //TODO: to remote error "Should not happen: no rect-based-test nodes found" you need to subclass webview and call
        // webview.onScrollChanged(webview.getScrollX(), webview.getScrollY(),webview.getScrollX(), webview.getScrollY());
    }


}
