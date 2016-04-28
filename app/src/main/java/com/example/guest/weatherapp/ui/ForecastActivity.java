package com.example.guest.weatherapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.guest.weatherapp.R;

public class ForecastActivity extends AppCompatActivity {
    public static final String TAG = ForecastActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        Log.i(TAG, location);
    }

}
