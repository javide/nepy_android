/*
 *  ---------------------------------------------------------------------------
 *  Copyright (C) 2016, Davide Baj - All Rights Reserved
 *
 *  Project name: Nepy
 *  Filename: AboutFragment.java
 *  Author: Davide Baj
 *  -------------------------------------------------------------------------
 */

package com.davidebaj.nepy.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.davidebaj.nepy.BuildConfig;
import com.davidebaj.nepy.MainActivity;
import com.davidebaj.nepy.R;
import com.davidebaj.nepy.Resources;

/**
 * Created by davide on 11/03/16.
 */
public class AboutFragment extends Fragment {

    private static final String TAG = "AboutFragment";
    private static final String baseUrl = "file:///android_asset/img/about/";
    private String title;

    public static AboutFragment newInstance(String title) {
        AboutFragment f = new AboutFragment();
        f.title = title;
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.about, container, false);
        view.setClickable(true);

        ((Activity) getContext()).setTitle(title);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Watch for button clicks
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Resources res = MainActivity.resources;
        String content = res.getAboutTemplate();

        for (String propertyName : res.getAllProperties("credits.")) {
            content = content.replace("$" + propertyName, res.getProperty(propertyName));
        }

        content = content.replace("$__authors", android.text.TextUtils.join("<br/>", res.getPlantsObj().getAuthors()))
                .replace("$__version_name", BuildConfig.VERSION_NAME) // set in the Gradle build cfg
                .replace("$__application_id", getContext().getString(R.string.app_name));

        WebView webView = (WebView) getActivity().findViewById(R.id.about_view);

        if (webView != null) {
            webView.setBackgroundColor(Color.BLACK);
            webView.getSettings().setAllowFileAccess(true);
            webView.loadDataWithBaseURL(baseUrl, content, null, "utf-8", null);
        }
    }

}
