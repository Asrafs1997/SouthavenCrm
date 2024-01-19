package com.thisit.softwaregroup.locateUs.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thisit.softwaregroup.R;
import com.thisit.softwaregroup.locateUs.model.LocationListResponseModel;
import com.thisit.softwaregroup.locateUs.view.ILocationListView;

import java.util.ArrayList;
import java.util.Locale;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.Viewholder> {

    private Context context;
    private ArrayList<LocationListResponseModel> locationListResponseModelArrayList;
    private ILocationListView iLocationListView;

    public LocationAdapter(Context context, ArrayList<LocationListResponseModel> courseModelArrayList, ILocationListView iLocationListView) {
        this.context = context;
        this.locationListResponseModelArrayList = courseModelArrayList;
        this.iLocationListView = iLocationListView;
    }


    @NonNull
    @Override
    public LocationAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loctionview, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.Viewholder holder, @SuppressLint("RecyclerView") int position) {
        // to set data to textview and imageview of each card layout
        LocationListResponseModel locationListResponseModel = locationListResponseModelArrayList.get(position);
        String phonenumber = "tel:" + locationListResponseModel.getPhoneNo();
        holder.location_name.setText(locationListResponseModel.getLocationName());
        holder.address_tv.setText(locationListResponseModel.getAddress1());
        holder.Country_tv.setText(locationListResponseModel.getCountry() + "\t" + locationListResponseModel.getZipCode());
        holder.telnumber_tv.setText(locationListResponseModel.getPhoneNo());
        holder.fexnumber_tv.setText(locationListResponseModel.getFaxNo());
        holder.contact_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(phonenumber));
                context.startActivity(intent);
            }
        });


        holder.directions_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String coordinatesHolder = String.format(Locale.ENGLISH, "geo:%f,%f", locationListResponseModelArrayList.get(position).getLatitude()
                        , locationListResponseModelArrayList.get(position).getLongitude());

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(coordinatesHolder));

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return locationListResponseModelArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView location_name, address_tv, telnumber_tv, fexnumber_tv, Country_tv;
        private ImageView contact_img, directions_img;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            location_name = itemView.findViewById(R.id.location_name);
            Country_tv = itemView.findViewById(R.id.Country_tv);
            address_tv = itemView.findViewById(R.id.address_tv);
            telnumber_tv = itemView.findViewById(R.id.telnumber_tv);
            fexnumber_tv = itemView.findViewById(R.id.fexnumber_tv);
            contact_img = itemView.findViewById(R.id.contact_img);
            directions_img = itemView.findViewById(R.id.directions_img);

            itemView.findViewById(R.id.hold_parent_layout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iLocationListView.holdListClick(getAdapterPosition());
                }
            });
        }
    }
}
