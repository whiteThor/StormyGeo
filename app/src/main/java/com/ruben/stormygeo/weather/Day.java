package com.ruben.stormygeo.weather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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

    public int getTemperatureMax() {
        return (int) Math.round(mTemperatureMax);
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

    public int getIconId() {

        return Forecast.getIconId(mIcom);
    }

    public String getDayOfTheWeek() {
        SimpleDateFormat format = new SimpleDateFormat("EEEE");
        format.setTimeZone(TimeZone.getTimeZone(mTimeZone));
        Date date = new Date(mTime * 1000);

        return format.format(date);

    }
}
