package com.ruben.stormygeo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ruben.stormygeo.R;
import com.ruben.stormygeo.weather.Hour;

/**
 * Created by Vilchez Ruben on 8/03/2018.
 */

public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourViewHolder> {
    private  Hour[] mHour;
    private Context mContex;

    public HourAdapter(Context contex, Hour[] mHour) {
        this.mContex = contex;
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

    public class HourViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
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

        @Override
        public void onClick(View v) {
            String time=mTimeLabel.getText().toString();
            String temperature=mTemperatureLabel.getText().toString();
            String summary=mSummaryLabel.getText().toString();
            String message = "At %s it will be %s and %s, time, temperature, summary";
            Toast.makeText(mContex, message, Toast.LENGTH_LONG).show();


        }
    }

}
