package com.thisit.softwaregroup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProfileSliderAdapter extends SliderViewAdapter<ProfileSliderAdapter.SliderAdapterViewHolder> {

    // list for storing urls of images.
    private final List<ProfileSliderBean> mSliderItems;

    // Constructor
    public ProfileSliderAdapter(Context context, ArrayList<ProfileSliderBean> sliderDataArrayList) {
        this.mSliderItems = sliderDataArrayList;
    }
    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.profileslider_layout, null);
        return new SliderAdapterViewHolder(inflate);
    }
    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, final int position) {

        final ProfileSliderBean sliderItem = mSliderItems.get(position);

        viewHolder.imageViewBackground.setImageResource(sliderItem.getmImageResource());

    }

    @Override
    public int getCount() {
        return mSliderItems.size();
    }

    static class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {
        View itemView;
        ImageView imageViewBackground;
        public SliderAdapterViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.myimage);
            this.itemView = itemView;
        }
    }
}

