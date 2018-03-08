package com.ruben.stormygeo.weather;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Luis Antonio Miranda on 18/02/2018.
 */

public class Hour implements Parcelable{
    private long mTime;
    private String mSummary;
    private double mTemperature;
    private String mIcon;
    private String mTimeZome;

    public Hour() {
    }

    private Hour(Parcel in) {
        mTime = in.readLong();
        mSummary = in.readString();
        mTemperature = in.readDouble();
        mIcon = in.readString();
        mTimeZome = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mTime);
        dest.writeString(mSummary);
        dest.writeDouble(mTemperature);
        dest.writeString(mIcon);
        dest.writeString(mTimeZome);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Hour> CREATOR = new Creator<Hour>() {
        @Override
        public Hour createFromParcel(Parcel in) {
            return new Hour(in);
        }

        @Override
        public Hour[] newArray(int size) {
            return new Hour[size];
        }
    };

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

    public int getTemperature() {
        return (int) Math.round(mTemperature);
    }

    public void setTemperature(double temperature) {
        mTemperature = temperature;
    }

    public String getIcon() {
        return mIcon;
    }

    public int getIconId(){
        return Forecast.getIconId(mIcon);
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public String getTimeZome() {
        return mTimeZome;
    }

    public void setTimeZome(String timeZome) {
        mTimeZome = timeZome;
    }

    public String getHour(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h a");
        Date date = new Date(mTime*1000);
        return simpleDateFormat.format(date);
    }
}
