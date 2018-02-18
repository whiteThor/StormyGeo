package com.ruben.stormygeo.ui;

import android.app.ListActivity;
import android.os.Bundle;

import com.ruben.stormygeo.R;

public class DailyForecastActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);
    }
}
