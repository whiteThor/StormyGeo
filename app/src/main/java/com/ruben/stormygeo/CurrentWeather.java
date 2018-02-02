package com.ruben.stormygeo;

/**
 * Created by Luis Antonio Miranda on 1/02/2018.
 */

public class CurrentWeather {

    private String mIcon;
    private long mTime;
    private double mTemperature;
    private double mHumidity;
    private double mPrecipChance;
    private String mSummary;
    private float mUvIndex;
    private float mApparentTemperature;
    private String mPrecipType;

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public double getTemperature() {
        return mTemperature;
    }

    public void setTemperature(double temperature) {
        mTemperature = temperature;
    }

    public double getHumidity() {
        return mHumidity;
    }

    public void setHumidity(double humidity) {
        mHumidity = humidity;
    }

    public double getPrecipChance() {
        return mPrecipChance;
    }

    public void setPrecipChance(double precipChance) {
        mPrecipChance = precipChance;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public float getUvIndex() {
        return mUvIndex;
    }

    public void setUvIndex(float uvIndex) {
        mUvIndex = uvIndex;
    }

    public float getApparentTemperature() {
        return mApparentTemperature;
    }

    public void setApparentTemperature(float apparentTemperature) {
        mApparentTemperature = apparentTemperature;
    }

    public String getPrecipType() {
        return mPrecipType;
    }

    public void setPrecipType(String precipType) {
        mPrecipType = precipType;
    }
}
