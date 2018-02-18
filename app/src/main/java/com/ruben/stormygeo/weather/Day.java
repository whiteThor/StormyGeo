package com.ruben.stormygeo.weather;

/**
 * Created by Luis Antonio Miranda on 18/02/2018.
 */

public class Day {

    private long mTime;
    private String mSummary;
    private double mTemperatureMax;
    private String mIcom;
    private String mTimeZone;

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public double getTemperatureMax() {
        return mTemperatureMax;
    }

    public void setTemperatureMax(double temperatureMax) {
        mTemperatureMax = temperatureMax;
    }

    public String getIcom() {
        return mIcom;
    }

    public void setIcom(String icom) {
        mIcom = icom;
    }

    public String getTimeZone() {
        return mTimeZone;
    }

    public void setTimeZone(String timeZone) {
        mTimeZone = timeZone;
    }
}
