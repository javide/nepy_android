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
import java.util.List;

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
     *
     * @param plants
     * The plants
     */
    @JsonProperty("plants")
    public void setPlants(List<Plant> plants) {
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
