package com.ruben.stormygeo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    public CurrentWeather mCurrentWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String apiKey = "4c34a5e97de8707b362a05691b22f49b";
        double latitude = 4.598889;
        double longitude = -74.080833;
        String foreCastUrl = "https://api.darksky.net/forecast/" + apiKey + "/" + latitude + "," + longitude;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(foreCastUrl)
                .build();
        if (isNetWorkAvalible()) {
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        String jSonData = response.body().string();
                        Log.v(TAG, jSonData);

                        if (response.isSuccessful()) {
                            mCurrentWeather = getCurrentDetails(jSonData);
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

    private CurrentWeather getCurrentDetails(String jSonData) throws JSONException {
        JSONObject jsonObject = new JSONObject(jSonData);
        String timeZone = jsonObject.getString("timezone");
        Log.i(TAG, "TimeZone:" + timeZone);

        JSONObject currently = jsonObject.getJSONObject("currently");

        CurrentWeather currentWeather = new CurrentWeather();

        currentWeather.setHumidity(currently.getDouble("humidity"));
        currentWeather.setTime(currently.getLong("time"));
        currentWeather.setIcon(currently.getString("icon"));
        currentWeather.setTemperature(currently.getDouble("temperature"));
        currentWeather.setPrecipChance(currently.getDouble("precipProbability"));
        currentWeather.setSummary(currently.getString("summary"));
        currentWeather.setPrecipType(currently.getString("precipType"));
        currentWeather.setApparentTemperature(currently.getLong("apparentTemperature"));
        currentWeather.setUvIndex(currently.getLong("uvIndex"));
        currentWeather.setTimeZone(currently.getString("timezone"));

        return currentWeather;
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
















