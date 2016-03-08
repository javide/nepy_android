/*
 *  ---------------------------------------------------------------------------
 *  Copyright (C) 2016, Davide Baj - All Rights Reserved
 *
 *  Project name: Nepy
 *  Filename: Challenge.java
 *  Author: Davide Baj
 *  -------------------------------------------------------------------------
 */

package com.davidebaj.nepy.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by davide on 07/03/16.
 */
public class Challenge {

    private String photoNum;
    private Plant aPlant;
    private List<String> answers;

    private Challenge() {}

    /**
     * Returns a Challenge for the supplied photoNum and Plant
     * @param photoNumber - currently either "1" or "2"
     * @param plant - a Plant object
     * @return - a Challenge object
     */
    static Challenge getInstance(String photoNumber, Plant plant) {

        Challenge challenge = new Challenge();

        challenge.photoNum = photoNumber;
        challenge.aPlant = plant;

        return challenge;
    }

    /**
     * Sets the correct answer and - at least - two incorrect answers for this challenge
     *
     * @param wrongPlants - a List of Plants (at least two) which must not corresponds to the plant of this challenge
     * @throws Exception
     */
    void setWrongAnswers(List<Plant> wrongPlants) throws Exception {

        if (wrongPlants.size() < 2) {
            throw new Exception("Argument wrongPlants must contain at least two Plants");
        }

        answers = new ArrayList<>(wrongPlants.size() + 1);

        for (Plant p : wrongPlants) {
            if (aPlant.getSpecies().equals(p.getSpecies())) {
                throw new Exception("Argument wrongPlants cannot contain the correct answer");
            }
            answers.add(p.getSpecies());
        }

        // the correct answer
        answers.add(aPlant.getSpecies());

        Collections.shuffle(answers);
    }

    /**
     * Returns the photo number of the plant associated with this challenge
     *
     * @return - the photo number
     */
    public String getPhotoNum() {
        return photoNum;
    }

    /**
     * Returns the plant of this challenge
     *
     * @return - a Plant object
     */
    public Plant getPlant() {
        return aPlant;
    }

    /**
     * Returns all the possible answers for this challenge in random order
     *
     * @return - a list of plant names, e.g.: [N. ampullaria, N. papuana, N. eustachya]
     */
    public List<String> getAnswers() {
        return answers;
    }

}
