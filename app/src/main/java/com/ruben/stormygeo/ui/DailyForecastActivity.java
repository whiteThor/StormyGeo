package com.ruben.stormygeo.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ruben.stormygeo.R;
import com.ruben.stormygeo.adapters.DayAdapter;
import com.ruben.stormygeo.weather.Day;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DailyForecastActivity extends ListActivity {

    @BindView(R.id.locationLabel)
    TextView mLocationLabel;

    private Day[] mDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        //mDays = (Day[])
        Bundle extras = intent.getExtras();
        //String location = extras.getString(MainActivity.DAILY_FORECAST_LOCATION);
        mLocationLabel.setText(extras.getString(MainActivity.DAILY_FORECAST_LOCATION));

        Parcelable[] parcelable = extras.getParcelableArray(MainActivity.DAILY_FORECAST);
        //Parcelable[] parcelable = intent.getParcelableArrayExtra(MainActivity.DAILY_FORECAST);
        mDays = Arrays.copyOf(parcelable, parcelable.length, Day[].class);
        DayAdapter dayAdapter = new DayAdapter(this, mDays);
        setListAdapter(dayAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String dayOfTheWeek = mDays[position].getDayOfTheWeek() ;
        String condition= mDays[position].getSummary();
        String highTemperature = mDays[position].getTemperatureMax() + "";

        String message = String.format("On %s the high will be %s and it will be %s", dayOfTheWeek, highTemperature,condition);

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
