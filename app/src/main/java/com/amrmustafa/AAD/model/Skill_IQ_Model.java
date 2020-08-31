package com.amrmustafa.AAD.model;

import com.google.gson.annotations.SerializedName;

public class Skill_IQ_Model {
    @SerializedName("name")
    private String name;

    @SerializedName("score")
    private int score;

    @SerializedName("country")
    private String country;

    @SerializedName("badgeUrl")
    private String badgeUrl;

    public Skill_IQ_Model(String name,int score ,String country, String badgeUrl) {
        this.name = name;
        this.score = score;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
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

    public int getScore() {
        return score;
    }

    public String getCountry() {
        return country;
    }

    public String getbadgeUrl() {
        return badgeUrl;
    }
}
