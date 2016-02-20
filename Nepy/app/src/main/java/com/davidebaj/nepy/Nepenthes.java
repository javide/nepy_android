/*
 *  ---------------------------------------------------------------------------
 *  Copyright (C) 2016, Davide Baj - All Rights Reserved
 *
 *  Project name: Nepy
 *  Filename: Nepenthes.java
 *  Author: Davide Baj
 *  -------------------------------------------------------------------------
 */

package com.davidebaj.nepy;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
        "id",
        "species",
        "img_credit",
        "img_credit_2",
        "pitcher_type",
        "pitcher_type_2",
        "regions",
        "pronunciation_1",
        "pronunciation_2",
        "range",
        "distribution",
        "altitude",
        "field_notes"
})
public class Nepenthes {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("species")
    private String species;
    @JsonProperty("img_credit")
    private String imgCredit;
    @JsonProperty("img_credit_2")
    private String imgCredit2;
    @JsonProperty("pitcher_type")
    private String pitcherType;
    @JsonProperty("pitcher_type_2")
    private String pitcherType2;
    @JsonProperty("regions")
    private Integer regions;
    @JsonProperty("pronunciation_1")
    private String pronunciation1;
    @JsonProperty("pronunciation_2")
    private String pronunciation2;
    @JsonProperty("range")
    private String range;
    @JsonProperty("distribution")
    private String distribution;
    @JsonProperty("altitude")
    private String altitude;
    @JsonProperty("field_notes")
    private String fieldNotes;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public Nepenthes() {
    }

    /**
     * @param imgCredit
     * @param id
     * @param fieldNotes
     * @param range
     * @param pitcherType
     * @param species
     * @param altitude
     * @param imgCredit2
     * @param regions
     * @param distribution
     * @param pitcherType2
     * @param pronunciation1
     * @param pronunciation2
     */
    public Nepenthes(Integer id, String species, String imgCredit, String imgCredit2, String pitcherType, String pitcherType2, Integer regions, String pronunciation1, String pronunciation2, String Species2, String range, String distribution, String altitude, String fieldNotes) {
        this.id = id;
        this.species = species;
        this.imgCredit = imgCredit;
        this.imgCredit2 = imgCredit2;
        this.pitcherType = pitcherType;
        this.pitcherType2 = pitcherType2;
        this.regions = regions;
        this.pronunciation1 = pronunciation1;
        this.pronunciation2 = pronunciation2;
        this.range = range;
        this.distribution = distribution;
        this.altitude = altitude;
        this.fieldNotes = fieldNotes;
    }

    /**
     * @return The id
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The species
     */
    @JsonProperty("species")
    public String getSpecies() {
        return species;
    }

    /**
     * @param species The species
     */
    @JsonProperty("species")
    public void setSpecies(String species) {
        this.species = species;
    }

    /**
     * @return The imgCredit
     */
    @JsonProperty("img_credit")
    public String getImgCredit() {
        return imgCredit;
    }

    /**
     * @param imgCredit The img_credit
     */
    @JsonProperty("img_credit")
    public void setImgCredit(String imgCredit) {
        this.imgCredit = imgCredit;
    }

    /**
     * @return The imgCredit2
     */
    @JsonProperty("img_credit_2")
    public String getImgCredit2() {
        return imgCredit2;
    }

    /**
     * @param imgCredit2 The img_credit_2
     */
    @JsonProperty("img_credit_2")
    public void setImgCredit2(String imgCredit2) {
        this.imgCredit2 = imgCredit2;
    }

    /**
     * @return The pitcherType
     */
    @JsonProperty("pitcher_type")
    public String getPitcherType() {
        return pitcherType;
    }

    /**
     * @param pitcherType The pitcher_type
     */
    @JsonProperty("pitcher_type")
    public void setPitcherType(String pitcherType) {
        this.pitcherType = pitcherType;
    }

    /**
     * @return The pitcherType2
     */
    @JsonProperty("pitcher_type_2")
    public String getPitcherType2() {
        return pitcherType2;
    }

    /**
     * @param pitcherType2 The pitcher_type_2
     */
    @JsonProperty("pitcher_type_2")
    public void setPitcherType2(String pitcherType2) {
        this.pitcherType2 = pitcherType2;
    }

    /**
     * @return The regions
     */
    @JsonProperty("regions")
    public Integer getRegions() {
        return regions;
    }

    /**
     * @param regions The regions
     */
    @JsonProperty("regions")
    public void setRegions(Integer regions) {
        this.regions = regions;
    }

    /**
     * @return The pronunciation1
     */
    @JsonProperty("pronunciation_1")
    public String getPronunciation1() {
        return pronunciation1;
    }

    /**
     * @param pronunciation1 The pronunciation_1
     */
    @JsonProperty("pronunciation_1")
    public void setPronunciation1(String pronunciation1) {
        this.pronunciation1 = pronunciation1;
    }

    /**
     * @return The pronunciation2
     */
    @JsonProperty("pronunciation_2")
    public String getPronunciation2() {
        return pronunciation2;
    }

    /**
     * @param pronunciation2 The pronunciation_2
     */
    @JsonProperty("pronunciation_2")
    public void setPronunciation2(String pronunciation2) {
        this.pronunciation2 = pronunciation2;
    }

    /**
     * @return The range
     */
    @JsonProperty("range")
    public String getRange() {
        return range;
    }

    /**
     * @param range The range
     */
    @JsonProperty("range")
    public void setRange(String range) {
        this.range = range;
    }

    /**
     * @return The distribution
     */
    @JsonProperty("distribution")
    public String getDistribution() {
        return distribution;
    }

    /**
     * @param distribution The distribution
     */
    @JsonProperty("distribution")
    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    /**
     * @return The altitude
     */
    @JsonProperty("altitude")
    public String getAltitude() {
        return altitude;
    }

    /**
     * @param altitude The altitude
     */
    @JsonProperty("altitude")
    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    /**
     * @return The fieldNotes
     */
    @JsonProperty("field_notes")
    public String getFieldNotes() {
        return fieldNotes;
    }

    /**
     * @param fieldNotes The field_notes
     */
    @JsonProperty("field_notes")
    public void setFieldNotes(String fieldNotes) {
        this.fieldNotes = fieldNotes;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(species).append(imgCredit).append(imgCredit2).append(pitcherType).append(pitcherType2).append(regions).append(pronunciation1).append(pronunciation2).append(range).append(distribution).append(altitude).append(fieldNotes).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Nepenthes) == false) {
            return false;
        }
        Nepenthes rhs = ((Nepenthes) other);
        return new EqualsBuilder().append(id, rhs.id).append(species, rhs.species).append(imgCredit, rhs.imgCredit).append(imgCredit2, rhs.imgCredit2).append(pitcherType, rhs.pitcherType).append(pitcherType2, rhs.pitcherType2).append(regions, rhs.regions).append(pronunciation1, rhs.pronunciation1).append(pronunciation2, rhs.pronunciation2).append(range, rhs.range).append(distribution, rhs.distribution).append(altitude, rhs.altitude).append(fieldNotes, rhs.fieldNotes).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}


