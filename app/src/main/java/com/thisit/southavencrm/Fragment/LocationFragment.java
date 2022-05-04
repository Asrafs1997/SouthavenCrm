package com.thisit.southavencrm.Fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.thisit.southavencrm.LocationAdapter;
import com.thisit.southavencrm.LocationModel;
import com.thisit.southavencrm.R;

import java.util.ArrayList;

public class LocationFragment extends Fragment {
    private View root;
    private RecyclerView locationrecyclerView;
    private ArrayList<LocationModel> courseModelArrayList;
    private Activity activity;
    private GoogleMap mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_location, container, false);
        activity = getActivity();
        locationrecyclerView = (RecyclerView) root.findViewById(R.id.locationrecyclerView);
        CameraUpdate cameraUpdate = null;
        courseModelArrayList = new ArrayList<LocationModel>();
        courseModelArrayList.add(new LocationModel("The center point", "176 orchard road \n#02-36 to 39 \nsingapore 238843", "Tel:(65)67325288", "Fex:(65)67325088"));
        courseModelArrayList.add(new LocationModel("Wheelock place", "501 orchard road \n#B1-04 \nsingapore 238880", "Tel:(65)67325288", "Fex:(65)67325088"));

        LocationAdapter courseAdapter = new LocationAdapter(activity, courseModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        locationrecyclerView.setLayoutManager(linearLayoutManager);
        locationrecyclerView.setAdapter(courseAdapter);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.frg);  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                mMap.clear(); //clear old markers

                CameraPosition googlePlex = CameraPosition.builder()
                        .target(new LatLng(1.301982, 103.839826))
                        .zoom(10)
                        .bearing(0)
                        .tilt(45)
                        .build();

                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);

                /*mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(37.4219999, -122.0862462))
                        .title("Spider Man")
                        .icon(bitmapDescriptorFromVector(getActivity(),R.drawable.history)));*/
                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(1.301982, 103.839826))
                        .title("The center point")
                        .snippet("176 orchard road \n#02-36 to 39 \nsingapore 238843"));
                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(1.304833, 103.831833))
                        .title("Wheelock place")
                        .snippet("501 orchard road \n#B1-04 \nsingapore 238880"));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(1.304833, 103.831833))
                        .title("Wisma Atria"));
            }
        });


        return root;
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

}

