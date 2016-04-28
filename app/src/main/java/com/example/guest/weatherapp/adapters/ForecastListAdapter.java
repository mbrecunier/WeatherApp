package com.example.guest.weatherapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.weatherapp.R;
import com.example.guest.weatherapp.models.Weather;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 4/28/16.
 */
public class ForecastListAdapter extends RecyclerView.Adapter<ForecastListAdapter.ForecastViewHolder> {
    private ArrayList<Weather> mForecast = new ArrayList<>();
    private Context mContext;

    public ForecastListAdapter(Context context, ArrayList<Weather> forecast) {
        mContext = context;
        mForecast = forecast;
    }

    @Override
    public ForecastListAdapter.ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_list_item, parent, false);
        ForecastViewHolder viewHolder = new ForecastViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ForecastListAdapter.ForecastViewHolder holder, int position) {
        holder.bindWeather(mForecast.get(position));
    }

    @Override
    public int getItemCount() {
        return mForecast.size();
    }

    public class ForecastViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.weatherImageView)
        ImageView mWeatherImageView;
        @Bind(R.id.dateTextView) TextView mDateView;
        @Bind(R.id.descriptionTextView) TextView mDescriptionView;
        @Bind(R.id.maxTextView) TextView mMaxTextView;
        @Bind(R.id.minTextView) TextView mMinTextView;
        private Context mContext;

        public ForecastViewHolder(View itemView) {
            super (itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindWeather(Weather weather) {
            mDateView.setText(weather.getDate());
            mDescriptionView.setText(weather.getDescription());
            mMaxTextView.setText(weather.getMaxTemp());
            mMinTextView.setText(weather.getMinTemp());
            Picasso.with(mContext).load(weather.getWeatherIcon()).into(mWeatherImageView);
        }
    }
}



