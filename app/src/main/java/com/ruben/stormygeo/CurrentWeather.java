package com.ruben.stormygeo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
    private String mTimeZone;

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }
/*
, , , , , wind, fog, cloudy, partly-cloudy-day, or
* */

    public int getIconId() {
        int mIconId = 0;

        if (mIcon.equals("clear-day")) {
            mIconId = R.drawable.clear_day;
        } else if (mIcon.equals("clear-night")) {
            mIconId = R.drawable.clear_night;
        } else if (mIcon.equals("rain")) {
            mIconId = R.drawable.rain;
        } else if (mIcon.equals("snow")) {
            mIconId = R.drawable.snow;
        } else if (mIcon.equals("sleet")) {
            mIconId = R.drawable.sleet;
        } else if (mIcon.equals("wind")) {
            mIconId = R.drawable.wind;
        } else if (mIcon.equals("fog")) {
            mIconId = R.drawable.fog;
        } else if (mIcon.equals("cloudy")) {
            mIconId = R.drawable.cloudy;
        } else if (mIcon.equals("partly-cloudy-day")) {
            mIconId = R.drawable.partly_cloudy;
        } else if (mIcon.equals("partly-cloudy-nigh")) {
            mIconId = R.drawable.cloudy_night;
        }

        return mIconId;

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

    public String getFormattedTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a");
        dateFormat.setTimeZone(TimeZone.getTimeZone(getTimeZone()));
        Date date = new Date(getTime() * 1000);
        String timeString = dateFormat.format(date);

        return timeString;

    }

    public String getTimeZone() {
        return mTimeZone;
    }

    public void setTimeZone(String timeZone) {
        mTimeZone = timeZone;
    }
}
