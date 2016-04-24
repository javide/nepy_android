/*
 *  ---------------------------------------------------------------------------
 *  Copyright (C) 2016, Davide Baj - All Rights Reserved
 *
 *  Project name: Nepy
 *  Filename: Plants.java
 *  Author: Davide Baj
 *  -------------------------------------------------------------------------
 */

package com.davidebaj.nepy.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
        "plants"
})
public class Plants {

    @JsonProperty("plants")
    private List<Plant> plants = new ArrayList<>();

    /**
     *
     * @return
     * The plants
     */
    @JsonProperty("plants")
    public List<Plant> getPlants() {
        return plants;
    }

    /**
     * Sets a list of plants reordering them alphabetically
     *
     * @param plants
     * The plants
     */
    @JsonProperty("plants")
    public void setPlants(List<Plant> plants) {

        Collections.sort(plants, new Comparator<Plant>() {
            @Override
            public int compare(Plant plant1, Plant plant2) {
                return plant1.getSpecies().compareToIgnoreCase(plant2.getSpecies());
            }
        });

        this.plants = plants;
    }

    /**
     * Returns a list of plants in the given regionCode
     *
     * @param regionCode - the id corresponding to a geographical region
     * @return plants
     */
    public List<Plant> getPlantsByRegion(String regionCode) {

        List<Plant> plantList = new ArrayList<>();

        for (Plant plant : plants) {
            if (plant.getRegions().contains(regionCode)) {
                plantList.add(plant);
            }
        }

        return plantList;
    }

    /**
     * Returns a plant for the given id - if it exists - or null otherwise
     * @param id - the string plant id
     * @return plant
     */
    public Plant getPlantById(String id) {

        for (Plant p : plants) {
            if (p.getId().equals(id)) {
                return p;
            }
        }

        return null;
    }

    /**
     * Returns a list of all the authors of plant photographs in alphabetical order
     * @return - a list of strings
     */
    public List<String> getAuthors() {

        Set<String> authors = new HashSet<>();

        for (Plant p : plants) {
            authors.add(p.getImgCredit());
            authors.add(p.getImgCredit2());
        }

        List<String> sortedAuthors = new ArrayList<>(authors);
        Collections.sort(sortedAuthors);

        return sortedAuthors;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(plants).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Plants) == false) {
            return false;
        }
        Plants rhs = ((Plants) other);
        return new EqualsBuilder().append(plants, rhs.plants).isEquals();
    }

}
