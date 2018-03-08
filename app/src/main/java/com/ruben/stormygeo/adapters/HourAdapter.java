package com.ruben.stormygeo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruben.stormygeo.R;
import com.ruben.stormygeo.weather.Hour;

/**
 * Created by Vilchez Ruben on 8/03/2018.
 */

public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourViewHolder> {
    private  Hour[] mHour;

    public HourAdapter(Hour[] mHour) {
        this.mHour = mHour;
    }

    @Override
    public HourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hourly_list_item,parent,false);

        HourViewHolder hourViewHolder = new HourViewHolder(view);

        return hourViewHolder;
    }

    @Override
    public void onBindViewHolder(HourViewHolder holder, int position) {
        holder.bindHour(mHour[position]);
    }

    @Override
    public int getItemCount() {
        return mHour.length;
    }

    public class HourViewHolder extends RecyclerView.ViewHolder{
        public TextView mTimeLabel;
        public TextView mSummaryLabel;
        public TextView mTemperatureLabel;
        public ImageView mIconImageView;

        public HourViewHolder(View itemView) {
            super(itemView);

            mTimeLabel = (TextView) itemView.findViewById(R.id.timeLabel);
            mSummaryLabel = (TextView) itemView.findViewById(R.id.summaryLabel);
            mTemperatureLabel = (TextView) itemView.findViewById(R.id.temperatureLabel);
            mIconImageView = (ImageView) itemView.findViewById(R.id.iconImageView);
        }

        public void bindHour(Hour hour){
            mTimeLabel.setText(hour.getHour());
            mSummaryLabel.setText(hour.getSummary());
            mTemperatureLabel.setText(hour.getTemperature() +"");
            mIconImageView.setImageResource(hour.getIconId());

        }
    }

}
