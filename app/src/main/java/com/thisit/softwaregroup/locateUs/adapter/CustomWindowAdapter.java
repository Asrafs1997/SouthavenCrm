package com.thisit.softwaregroup.locateUs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.thisit.softwaregroup.R;
 public class CustomWindowAdapter implements GoogleMap.InfoWindowAdapter {

     private Context context;

     public CustomWindowAdapter(Context context) {
         this.context = context.getApplicationContext();

    }


    @Override
    public View getInfoContents(Marker marker) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v =  inflater.inflate(R.layout.info_window_layout, null);

        TextView tvGir = (TextView) v.findViewById(R.id.tvgir);
        TextView tvDetails = (TextView) v.findViewById(R.id.tvd);
        // TextView tvLng = (TextView) v.findViewById(R.id.tv_lng);
        tvGir.setText(marker.getTitle());
        tvDetails.setText(marker.getSnippet());
        //tvLng.setText("Longitude:"+ latLng.longitude);
        return v;


    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;

    }
}