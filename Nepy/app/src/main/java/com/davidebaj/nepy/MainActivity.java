/*
 *  ---------------------------------------------------------------------------
 *  Copyright (C) 2016, Davide Baj - All Rights Reserved
 *
 *  Project name: Nepy
 *  Filename: MainActivity.java
 *  Author: Davide Baj
 *  -------------------------------------------------------------------------
 */

package com.davidebaj.nepy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import com.davidebaj.nepy.dao.Plant;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, PlantListFragment.Callbacks {

    private static final String TAG = "MainActivity";
    public static Resources resources;
    private Fragment fragment;
    private PagerFragment pagerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        resources = Resources.getResources(getApplicationContext(), Locale.getDefault().getLanguage());

      /*  for (Plant plant : resources.getPlantsObj().getPlants()) {
            Log.d(TAG, plant.getSpecies());
        }
      */

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        // set drawer menu item titles
        NavigationView nav_draw = (NavigationView) findViewById(R.id.nav_view);
        nav_draw.getMenu().findItem(R.id.nav_home).setTitle(resources.getProperty("nav.HOME"));
        nav_draw.getMenu().findItem(R.id.nav_all_species).setTitle(resources.getProperty("nav.ALL_SPECIES"));
        nav_draw.getMenu().findItem(R.id.nav_by_region).setTitle(resources.getProperty("nav.BY_REGION"));
        nav_draw.getMenu().findItem(R.id.nav_quiz).setTitle(resources.getProperty("nav.QUIZ"));
        nav_draw.getMenu().findItem(R.id.nav_about).setTitle(resources.getProperty("nav.ABOUT"));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Log.d(TAG, "Selected home ************");

        } else if (id == R.id.nav_all_species) {
            Log.d(TAG, "Selected all species ************");

            List<Plant> plantList = resources.getPlantsObj().getPlants();
            ArrayAdapter<Plant> adapter = new PlantArrayAdapter(this, plantList.toArray(new Plant[plantList.size()]));
            fragment = PlantListFragment.newInstance(resources.getProperty("label.ALL_SPECIES"), adapter);

        } else if (id == R.id.nav_by_region) {
            Log.d(TAG, "Selected by region ************");

            fragment = RegionsFragment.newInstance(resources.getProperty("label.REGIONS"));

        } else if (id == R.id.nav_quiz) {
            Log.d(TAG, "Selected quiz ************");

            fragment = QuizFragment.newInstance(resources.getProperty("nav.QUIZ"));

        } else if (id == R.id.nav_about) {
            Log.d(TAG, "Selected about ************");

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else {

            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentTransaction.replace(R.id.content_main, fragment).commit();

        Log.d(TAG, "TRANSACTION COMMITED +++++++++++");

        return true;
    }

    /**
     * User has clicked on a plant name, so we now display its photo
     *
     * @param aPlant - a plant object
     */
    public void onListItemSelected(Plant aPlant) {

        Log.d(TAG, "Selected nepenthes = " + aPlant.getSpecies());

        pagerFragment = PagerFragment.newInstance(aPlant);

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.content_main, pagerFragment)
                .commit();
    }
}
