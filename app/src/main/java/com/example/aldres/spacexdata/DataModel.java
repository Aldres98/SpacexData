package com.example.aldres.spacexdata;


/**
 * Created by Aldres on 19.01.2018.
 */

public class DataModel {

    private String rocketName;
    private long launchDateUnix;
    private String missionPatch;
    private String details;
    private int flightNumber;
    private String articleLink;
    private String videoLink;

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getArticleLink() {
        return articleLink;
    }

    public void setArticleLink(String articleLink) {
        this.articleLink = articleLink;
    }


    public String getRocketName() {
        return rocketName;
    }

    public void setRocketName(String rocketName) {
        this.rocketName = rocketName;
    }

    public long getLaunchDateUnix() {
        return launchDateUnix;
    }

    public void setLaunchDateUnix(long launchDateUnix) {
        this.launchDateUnix = launchDateUnix;
    }

    public String getMissionPatch() {
        return missionPatch;
    }

    public void setMissionPatch(String missionPatch) {
        this.missionPatch = missionPatch;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }
}
