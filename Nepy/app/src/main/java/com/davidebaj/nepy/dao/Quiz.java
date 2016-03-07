/*
 *  ---------------------------------------------------------------------------
 *  Copyright (C) 2016, Davide Baj - All Rights Reserved
 *
 *  Project name: Nepy
 *  Filename: Quiz.java
 *  Author: Davide Baj
 *  -------------------------------------------------------------------------
 */

package com.davidebaj.nepy.dao;

import android.util.Log;

import com.davidebaj.nepy.MainActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by davide on 05/03/16.
 */
public class Quiz {

    private static final String TAG = "Quiz";
    private static final Plants plants = MainActivity.resources.getPlantsObj();
    private List<Challenge> challenges;

    private Quiz() {
    }

    /**
     * Instantiates a Quiz object
     *
     * @return - a quiz
     */
    public static Quiz getInstance() {
        return new Quiz();
    }

    /**
     * Returns a list of challenges twice as long the number of the main plant list
     * @return - the challenges
     */
    public List<Challenge> getQuizData() {

        // each plant needs to appear twice in the challenge list as we have two photos for each plant
        challenges = new ArrayList<>(plants.getPlants().size() * 2);

        prepareQuiz();
        Collections.shuffle(challenges);

        return challenges;
    }

    /**
     * Debugging
     */
    public void logQuizData() {

        for (Challenge challenge : challenges) {
            Log.d(TAG, challenge.getPlant().getSpecies() + " ---> " + challenge.getAnswers());
        }
    }

    /**
     * Prepare a list of challenges
     */
    private void prepareQuiz() {

        for (Plant plant : plants.getPlants()) {
            challenges.add(Challenge.getInstance("1", plant));
            challenges.add(Challenge.getInstance("2", plant));
        }

        Iterator<Challenge> iter = challenges.iterator();
        while (iter.hasNext()) {
            Challenge challenge = iter.next();

            try {
                challenge.setWrongAnswers(pickTwoOtherPlants(challenge.getPlant()));
            } catch (Exception e) {
                Log.e(TAG, "Removing challenge for species " + challenge.getPlant().getSpecies());
                challenges.remove(challenge);
            }
        }
    }

    /**
     * Returns a list of two random plants that do not match the given plant
     *
     * @param aPlant - the Plant which should not be returned in the list
     * @return - a list containing two other random plants
     */
    private List<Plant> pickTwoOtherPlants(Plant aPlant) {

        Plant anotherPlant1 = null;
        Plant anotherPlant2 = null;
        Integer numOfPlants = plants.getPlants().size();
        List<Plant> otherPlants = new ArrayList<>(2);

        while (anotherPlant1 == null || aPlant.getId().equals(anotherPlant1.getId())) {
            anotherPlant1 = plants.getPlants().get(new Random().nextInt(numOfPlants));
        }

        while (anotherPlant2 == null || aPlant.getId().equals(anotherPlant2.getId()) || anotherPlant1.getId().equals(anotherPlant2.getId())) {
            anotherPlant2 = plants.getPlants().get(new Random().nextInt(numOfPlants));
        }

        otherPlants.add(anotherPlant1);
        otherPlants.add(anotherPlant2);

        return otherPlants;
    }

}
