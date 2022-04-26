package com.thisit.southavencrm;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.Viewholder> {

    private Context context;
    private ArrayList<LocationModel> courseModelArrayList;

    public LocationAdapter(Context context, ArrayList<LocationModel> courseModelArrayList) {
        this.context = context;
        this.courseModelArrayList = courseModelArrayList;
    }

    @NonNull
    @Override
    public LocationAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loctionview, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        LocationModel model = courseModelArrayList.get(position);
        String phonenumber = "tel:" + model.getTelnumber();
        holder.location_tv.setText(model.getLocation());
        holder.address_tv.setText(model.getAddress());
        holder.telnumber_tv.setText(model.getTelnumber());
        holder.fexnumber_tv.setText(model.getFexnumber());
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

                Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/dir/?api=1&origin=18.519513,73.868315&destination=18.518496,73.879259&waypoints=18.520561,73.872435|18.519254,73.876614|18.52152,73.877327|18.52019,73.879935&travelmode=driving");
                Intent intent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                intent.setPackage("com.google.android.apps.maps");
                try {
                    context.startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    try {
                        Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        context.startActivity(unrestrictedIntent);
                    } catch (ActivityNotFoundException innerEx) {
                        Toast.makeText(context, "Please install a maps application", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return courseModelArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView location_tv, address_tv, telnumber_tv, fexnumber_tv;
        private ImageView contact_img,directions_img;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            location_tv = itemView.findViewById(R.id.location_tv);
            address_tv = itemView.findViewById(R.id.address_tv);
            telnumber_tv = itemView.findViewById(R.id.telnumber_tv);
            fexnumber_tv = itemView.findViewById(R.id.fexnumber_tv);
            contact_img = itemView.findViewById(R.id.contact_img);
            directions_img = itemView.findViewById(R.id.directions_img);
        }
    }
}
