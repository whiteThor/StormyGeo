package com.ruben.stormygeo.ui;

import android.app.ListActivity;
import android.os.Bundle;

import com.ruben.stormygeo.R;
import com.ruben.stormygeo.adapters.DayAdapter;
import com.ruben.stormygeo.weather.Day;

public class DailyForecastActivity extends ListActivity {

    private Day[] mDays;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);

        DayAdapter dayAdapter = new DayAdapter(this, mDays);
    }

}
