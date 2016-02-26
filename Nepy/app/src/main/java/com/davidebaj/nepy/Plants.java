/*
 *  ---------------------------------------------------------------------------
 *  Copyright (C) 2016, Davide Baj - All Rights Reserved
 *
 *  Project name: Nepy
 *  Filename: Plants.java
 *  Author: Davide Baj
 *  -------------------------------------------------------------------------
 */

package com.davidebaj.nepy;

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
     * Returns a list of plants in the given regionCode
     *
     * @param regionCode - the id corresponding to a geographical region
     * @return plants
     */
    public List<Plant> getPlantsByRegion(String regionCode) {

        List<Plant> plantList = new ArrayList<>();

        for (Plant plant : getPlants()) {
            if (plant.getRegions().contains(regionCode)) {
                plantList.add(plant);
            }
        }

        return plantList;
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
