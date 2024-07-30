package com.example.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.ViewHolder>{
    //Member variable
    private ArrayList<Sport> mSportsData;
    private Context mContext;

    //Constructor
    SportsAdapter(Context context, ArrayList<Sport> sportsData) {
        this.mSportsData = sportsData;
        this.mContext = context;
    }

    //Method for creating viewholder objects
    @Override
    public SportsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false));
    }

    //Method for binding data to viewholder
    @Override
    public void onBindViewHolder(SportsAdapter.ViewHolder holder, int position) {
        //Get current sport
        Sport currentSport = mSportsData.get(position);
        //Populate textviews with data
        holder.bindTo(currentSport);
    }

    //Method for determining the size of the data set
    @Override
    public int getItemCount() {
        return mSportsData.size();
    }

    //Viewholder class to represent each row of data in RecyclerView
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitleText;
        private TextView mInfoText;
        private ImageView mSportsImage;

        //Constructor for ViewHolder
        ViewHolder(View itemView) {
            super(itemView);

            //Initialize the views
            mTitleText = itemView.findViewById(R.id.title);
            mInfoText = itemView.findViewById(R.id.subTitle);
            mSportsImage = itemView.findViewById(R.id.sportsImage);
            // Set the OnClickListener to the entire view.
            itemView.setOnClickListener(this);
        }

        void bindTo(Sport currentSport) {
            //Populate textviews with data
            mTitleText.setText(currentSport.getTitle());
            mInfoText.setText(currentSport.getInfo());
            mSportsImage = itemView.findViewById(R.id.sportsImage);

            Glide.with(mContext).load(currentSport.getImageResource()).into(mSportsImage);
        }

        @Override
        public void onClick(View view) {
            Sport currentSport = mSportsData.get(getAdapterPosition());

            Intent detailIntent = new Intent(mContext, DetailActivity.class);
            detailIntent.putExtra("title", currentSport.getTitle());
            detailIntent.putExtra("image_resource", currentSport.getImageResource());
            mContext.startActivity(detailIntent);
        }
    }
}
