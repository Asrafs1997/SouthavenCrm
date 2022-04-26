package com.thisit.southavencrm.Fragment;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.thisit.southavencrm.LocationAdapter;
import com.thisit.southavencrm.LocationModel;
import com.thisit.southavencrm.R;

import java.util.ArrayList;

public class LocationFragment extends Fragment implements OnMapReadyCallback {
    private View root;
    private RecyclerView locationrecyclerView;
    private ArrayList<LocationModel> courseModelArrayList;
    private Activity activity;
    private GoogleMap mMap;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_location, container, false);
        activity=getActivity();
        locationrecyclerView = (RecyclerView) root.findViewById(R.id.locationrecyclerView);
        CameraUpdate cameraUpdate = null;
        courseModelArrayList = new ArrayList<LocationModel>();
        courseModelArrayList.add(new LocationModel("The center point","176 orchard road \n#02-36 to 39 \nsingapore 238843","Tel:(65)67325288","Fex:(65)67325088"));
        courseModelArrayList.add(new LocationModel("Wheelock place","501 orchard road \n#B1-04 \nsingapore 238880","Tel:(65)67325288","Fex:(65)67325088"));

        LocationAdapter courseAdapter = new LocationAdapter(activity, courseModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        locationrecyclerView.setLayoutManager(linearLayoutManager);
        locationrecyclerView.setAdapter(courseAdapter);

        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager()
                .findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(this);

        return root;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-33.852, 151.211);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


}

