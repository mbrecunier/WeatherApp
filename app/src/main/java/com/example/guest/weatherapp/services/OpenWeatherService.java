package com.example.guest.weatherapp.services;

import android.util.Log;

import com.example.guest.weatherapp.Constants;
import com.example.guest.weatherapp.models.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Guest on 4/28/16.
 */
public class OpenWeatherService {

    public static void getWeather(String location, Callback callback) {
        String OPEN_WEATHER_KEY = Constants.OPEN_WEATHER_KEY;
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.OPEN_WEATHER_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.OPEN_WEATHER_QUERY_PARAMETER, location);
        urlBuilder.addQueryParameter(Constants.KEY_QUERY_PARAMETER, OPEN_WEATHER_KEY);
        String url = urlBuilder.build().toString();
        Log.d("URL", url);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Weather> processResults(Response response) {
        ArrayList<Weather> forecast = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if(response.isSuccessful()) {

                JSONObject openWeatherJSON = new JSONObject(jsonData);
                JSONArray forecastJSON = openWeatherJSON.getJSONArray("list");

                for(int i = 0; i < forecastJSON.length(); i++) {
                    JSONObject weatherJSON = forecastJSON.getJSONObject(i);
                    String minTemp = weatherJSON.getJSONObject("temp").getString("min");
                    String maxTemp = weatherJSON.getJSONObject("temp").getString("max");
                    String description = weatherJSON.getJSONArray("weather").getJSONObject(0).getString("description");
                    long dateJSON = (weatherJSON.getLong("dt") * 1000);

                    String date;
                    SimpleDateFormat df = new SimpleDateFormat("EEEE");
                    date = df.format(dateJSON);

                    String weatherIcon = weatherJSON.getJSONArray("weather").getJSONObject(0).getString("icon");


                    Weather weather = new Weather(minTemp, maxTemp, description, date, weatherIcon);
                    forecast.add(weather);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return forecast;
    }
}
