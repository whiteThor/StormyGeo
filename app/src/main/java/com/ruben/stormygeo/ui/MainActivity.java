package com.ruben.stormygeo.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ruben.stormygeo.R;
import com.ruben.stormygeo.weather.Current;
import com.ruben.stormygeo.weather.Day;
import com.ruben.stormygeo.weather.Forecast;
import com.ruben.stormygeo.weather.Hour;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    public Forecast mForecast;

    @BindView(R.id.timeLabel)
    TextView mTimeLabel;
    @BindView(R.id.temperatureLabel)
    TextView mTemperatureLabel;
    @BindView(R.id.humidityValue)
    TextView mHumidityValue;
    @BindView(R.id.precipValue)
    TextView mPrecipValue;
    @BindView(R.id.summaryLabel)
    TextView mSummaryLabel;
    @BindView(R.id.iconImageView)
    ImageView mIconImageView;
    @BindView(R.id.refreshImageView)
    ImageView mRefreshImageView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mProgressBar.setVisibility(View.INVISIBLE);

        final double latitude = 4.598889;
        final double longitude = -74.080833;
        getForecast(latitude, longitude);
        mRefreshImageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                getForecast(latitude, longitude);
            }
        });


    }

    private void getForecast(double latitude, double longitude) {
        String apiKey = "4c34a5e97de8707b362a05691b22f49b";

        String foreCastUrl = "https://api.darksky.net/forecast/" + apiKey + "/" + latitude + "," + longitude;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(foreCastUrl)
                .build();
        if (isNetWorkAvalible()) {
            toggleRefresh();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            toggleRefresh();
                        }
                    });
                    alertUserAboutError();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        String jSonData = response.body().string();
                        Log.v(TAG, jSonData);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                toggleRefresh();
                            }
                        });

                        if (response.isSuccessful()) {
                            mForecast = parseForecastDetails(jSonData);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    updateDisplay();
                                }
                            });

                        } else {
                            alertUserAboutError();
                        }

                    } catch (JSONException e) {
                        Log.e(TAG, "Exception caught:", e);

                    }
                }
            });
        } else {
            Toast.makeText(this, "The network is unavalilable", Toast.LENGTH_LONG);
        }
    }

    private void toggleRefresh() {

        if (mProgressBar.getVisibility() == View.INVISIBLE) {
            mProgressBar.setVisibility(View.VISIBLE);
            mRefreshImageView.setVisibility(View.INVISIBLE);
        } else {
            mProgressBar.setVisibility(View.INVISIBLE);
            mRefreshImageView.setVisibility(View.VISIBLE);
        }

    }

    private void updateDisplay() {
        Current current = mForecast.getCurrent();
        mTemperatureLabel.setText(current.getTemperature() + "");
        mTimeLabel.setText("At " + current.getFormattedTime() + " it will be");
        mHumidityValue.setText(current.getHumidity() + "");
        mPrecipValue.setText(current.getPrecipChance() + "%");
        mSummaryLabel.setText(current.getSummary());

        Drawable drawable = getResources().getDrawable(current.getIconId());

        mIconImageView.setImageDrawable(drawable);


    }

    private Forecast parseForecastDetails(String jSonData) throws JSONException {
        Forecast forecast = new Forecast();

        forecast.setCurrent(getCurrentDetails(jSonData));
        forecast.setHourlyForecast(getHourlyForecast(jSonData));
        forecast.setDailyForecast(getDailyForecast(jSonData));


        return forecast;
    }

    private Day[] getDailyForecast(String jSonData) throws JSONException {
        JSONObject jsonObject = new JSONObject(jSonData);
        String timeZone = jsonObject.getString("timezone");

        JSONObject daily = jsonObject.getJSONObject("daily");
        JSONArray data = daily.getJSONArray("data");

        Day[] days = new Day[data.length()];
        for (int i = 0; i < data.length(); i++) {
            JSONObject jsonDay = data.getJSONObject(i);
            Day day = new Day();

            day.setSummary(jsonDay.getString("summary"));
            day.setIcom(jsonDay.getString("icon"));
            day.setTemperatureMax(jsonDay.getDouble("temperatureMax"));
            day.setTime(jsonDay.getLong("time"));
            day.setTimeZone(timeZone);

            days[i] = day;
        }


        return days;
    }

    private Hour[] getHourlyForecast(String jSonData) throws JSONException {
        JSONObject jsonObject = new JSONObject(jSonData);
        String timeZone = jsonObject.getString("timezone");
        JSONObject hourly = jsonObject.getJSONObject("hourly");
        JSONArray data = hourly.getJSONArray("data");
        Hour hours[] = new Hour[data.length()];
        for (int i = 0; i < data.length(); i++) {

            JSONObject jsonHour = data.getJSONObject(i);

            Hour hour = new Hour();

            hour.setSummary(jsonHour.getString("summary"));
            hour.setTemperature(jsonHour.getDouble("temperature"));
            hour.setIcon(jsonHour.getString("icon"));
            hour.setTimeZome(timeZone);

            hours[i] = hour;

        }

        return hours;


    }

    private Current getCurrentDetails(String jSonData) throws JSONException {
        JSONObject jsonObject = new JSONObject(jSonData);
        String timeZone = jsonObject.getString("timezone");
        Log.i(TAG, "TimeZone:" + timeZone);

        JSONObject currently = jsonObject.getJSONObject("currently");

        Current current = new Current();

        current.setHumidity(currently.getDouble("humidity"));
        current.setTime(currently.getLong("time"));
        current.setIcon(currently.getString("icon"));
        current.setTemperature(currently.getDouble("temperature"));
        current.setPrecipChance(currently.getDouble("precipProbability"));
        current.setSummary(currently.getString("summary"));
        current.setPrecipType(currently.getString("precipType"));
        current.setApparentTemperature(currently.getLong("apparentTemperature"));
        current.setUvIndex(currently.getLong("uvIndex"));
        current.setTimeZone(timeZone);

        return current;
    }

    private boolean isNetWorkAvalible() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        boolean isAvalilable = false;
        if (info != null) {
            isAvalilable = info.isConnected();
        }
        return isAvalilable;
    }

    private void alertUserAboutError() {

        AlertDialogFragment dialogFragment = new AlertDialogFragment();
        dialogFragment.show(getFragmentManager(), "error_dialog");
    }
}
















