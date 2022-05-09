package com.thisit.southavencrm.locateUs.view;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.thisit.southavencrm.R;
import com.thisit.southavencrm.common.ConfigApp;
import com.thisit.southavencrm.locateUs.adapter.LocationAdapter;
import com.thisit.southavencrm.locateUs.model.LocationListResponseModel;
import com.thisit.southavencrm.locateUs.presenter.ILocationListPresenter;
import com.thisit.southavencrm.locateUs.presenter.LocationListPresenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;

public class LocationFragment extends Fragment implements ILocationListView, OnMapReadyCallback {
    private View root;
    private Activity activity;
    private RecyclerView locationrecyclerView;
    private ILocationListPresenter iLocationListPresenter;
    private ArrayList<LocationListResponseModel> locationListResponseModelArrayList;
    private SupportMapFragment mapFragment;
    private GoogleMap mMap;
    Geocoder geo;
    private double latitude, longitude;


    // creating array list for adding all our locations.
    private ArrayList<LatLng> locationArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_location, container, false);
        activity = getActivity();

        locationrecyclerView = (RecyclerView) root.findViewById(R.id.locationrecyclerView);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.frg);
        iLocationListPresenter = new LocationListPresenter(this);


        if (ConfigApp.isNetworkAvailable(activity)) {
            iLocationListPresenter.locationList(ConfigApp.getCompanyCode());
        } else {
            Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

        return root;
    }

    private void GoogleMapview(int position) {
        Double latitude = Double.valueOf(locationListResponseModelArrayList.get(position).getLatitude());
        Double longitude = Double.valueOf(locationListResponseModelArrayList.get(position).getLongitude());
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

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(latitude, longitude))
                        .title(locationListResponseModelArrayList.get(position).getLocationName())
                        .snippet(locationListResponseModelArrayList.get(position).getAddress1()));
            }
        });

    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void getLocationList(ArrayList<LocationListResponseModel> holdListResponseModelArrayList) {
        locationListResponseModelArrayList = new ArrayList<LocationListResponseModel>();
        for (int i = 0; i < holdListResponseModelArrayList.size(); i++) {
            LocationListResponseModel locationListResponseModel = new LocationListResponseModel();
            locationListResponseModel.set$id(holdListResponseModelArrayList.get(i).get$id());
            locationListResponseModel.setLocationCode(holdListResponseModelArrayList.get(i).getLocationCode());
            locationListResponseModel.setLocationName(holdListResponseModelArrayList.get(i).getLocationName());
            locationListResponseModel.setAddress1(holdListResponseModelArrayList.get(i).getAddress1());
            locationListResponseModel.setAddress2(holdListResponseModelArrayList.get(i).getAddress2());
            locationListResponseModel.setAddress3(holdListResponseModelArrayList.get(i).getAddress3());
            locationListResponseModel.setCountry(holdListResponseModelArrayList.get(i).getCountry());
            locationListResponseModel.setZipCode(holdListResponseModelArrayList.get(i).getZipCode());
            locationListResponseModel.setPhoneNo(holdListResponseModelArrayList.get(i).getPhoneNo());
            locationListResponseModel.setFaxNo(holdListResponseModelArrayList.get(i).getFaxNo());
            locationListResponseModel.setLatitude(holdListResponseModelArrayList.get(i).getLatitude());
            locationListResponseModel.setLongitude(holdListResponseModelArrayList.get(i).getLongitude());

            latitude = Double.valueOf(holdListResponseModelArrayList.get(i).getLatitude());
            longitude = Double.valueOf(holdListResponseModelArrayList.get(i).getLongitude());
            mapFragment.getMapAsync(this);
            locationArrayList = new ArrayList<>();
            LatLng sydney = new LatLng(latitude, longitude);
            locationArrayList.add(sydney);
            locationListResponseModelArrayList.add(locationListResponseModel);

            if (locationListResponseModelArrayList.size() > 0) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
                LocationAdapter adapter = new LocationAdapter(activity, locationListResponseModelArrayList, this);
                locationrecyclerView.setLayoutManager(linearLayoutManager);
                locationrecyclerView.setAdapter(adapter);
            }
        }
    }


    @Override
    public void onSuccess() {

    }

    @Override
    public void holdListClick(int position) {
        GoogleMapview(position);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        for (int i=0;i<locationArrayList.size();i++){
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            CameraPosition googlePlex = CameraPosition.builder()
                    .target(new LatLng(1.301982, 103.839826))
                    .zoom(10)
                    .bearing(0)
                    .tilt(45)
                    .build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(locationArrayList.get(i));
            mMap.addMarker(markerOptions);
           // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationArrayList.get(i), 6));
        }
    }
}

