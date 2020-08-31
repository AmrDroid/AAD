package com.amrmustafa.AAD.model;

import com.google.gson.annotations.SerializedName;

public class LearningLeader_Model {
    @SerializedName("name")
    private String name;

    @SerializedName("hours")
    private int hours;

    @SerializedName("country")
    private String country;

    @SerializedName("badgeUrl")
    private String badgeUrl;

    public LearningLeader_Model(String name,int hours ,String country, String badgeUrl) {
        this.name = name;
        this.hours = hours;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sethours(int hours) {
        this.hours = hours;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setbadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    public String getName() {
        return name;
    }

    public int gethours() {
        return hours;
    }

    public String getCountry() {
        return country;
    }

    public String getbadgeUrl() {
        return badgeUrl;
    }
}
