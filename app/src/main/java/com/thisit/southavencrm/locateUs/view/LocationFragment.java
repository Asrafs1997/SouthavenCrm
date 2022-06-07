package com.thisit.southavencrm.locateUs.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import com.thisit.southavencrm.common.BaseFragment;
import com.thisit.southavencrm.common.ConfigApp;
import com.thisit.southavencrm.dashboard.view.ECardActivity;
import com.thisit.southavencrm.locateUs.adapter.LocationAdapter;
import com.thisit.southavencrm.locateUs.model.LocationListResponseModel;
import com.thisit.southavencrm.locateUs.presenter.ILocationListPresenter;
import com.thisit.southavencrm.locateUs.presenter.LocationListPresenter;

import java.util.ArrayList;
import java.util.HashMap;

public class LocationFragment extends BaseFragment implements ILocationListView, OnMapReadyCallback {
    private View root;
    private Activity activity;
    private RecyclerView locationrecyclerView;
    private RelativeLayout parent_layout;
    private ILocationListPresenter iLocationListPresenter;
    private ArrayList<LocationListResponseModel> locationListResponseModelArrayList;
    private SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private TextView no_record_text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_location, container, false);
        activity = getActivity();

        locationrecyclerView = (RecyclerView) root.findViewById(R.id.locationrecyclerView);
        parent_layout = (RelativeLayout) root.findViewById(R.id.parent_layout);
        no_record_text = (TextView) root.findViewById(R.id.no_record_text);
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




        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                // mMap.clear(); //clear old markers

                CameraPosition googlePlex = CameraPosition.builder()
                        .target(new LatLng(locationListResponseModelArrayList.get(position).getLatitude(), locationListResponseModelArrayList.get(position).getLongitude()))
                        .zoom(11)
                        .bearing(0)
                        .tilt(45)
                        .build();

                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 1000, null);
               // mMap.setInfoWindowAdapter(new CustomInfoAdapter(this));
                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(locationListResponseModelArrayList.get(position).getLongitude(), locationListResponseModelArrayList.get(position).getLongitude()))
                        .title(locationListResponseModelArrayList.get(position).getLocationName())
                        .snippet(locationListResponseModelArrayList.get(position).getAddress1()));
            }
        });
    }




    @Override
    public void getLocationList(ArrayList<LocationListResponseModel> holdListResponseModelArrayList) {
        locationListResponseModelArrayList = new ArrayList<LocationListResponseModel>();

        if (holdListResponseModelArrayList.size() > 0) {
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
                locationListResponseModelArrayList.add(locationListResponseModel);
                if (locationListResponseModelArrayList.size() > 0) {
                    parent_layout.setVisibility(View.VISIBLE);
                    no_record_text.setVisibility(View.GONE);
                    mapFragment.getMapAsync(this);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
                    LocationAdapter adapter = new LocationAdapter(activity, locationListResponseModelArrayList, this);
                    locationrecyclerView.setLayoutManager(linearLayoutManager);
                    locationrecyclerView.setAdapter(adapter);
                } else {
                    parent_layout.setVisibility(View.GONE);
                    no_record_text.setVisibility(View.VISIBLE);
                }

            }
        } else {
            parent_layout.setVisibility(View.GONE);
            no_record_text.setVisibility(View.VISIBLE);
        }


    }


    @Override
    public void onSuccess() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ECardActivity) getActivity()).title_tv.setText(R.string.locat_us);
    }

    @Override
    public void holdListClick(int position) {
        GoogleMapview(position);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        for (int i = 0; i < locationListResponseModelArrayList.size(); i++) {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            CameraPosition googlePlex = CameraPosition.builder()
                    .target(new LatLng(locationListResponseModelArrayList.get(i).getLatitude()
                            , locationListResponseModelArrayList.get(i).getLongitude()))
                    .zoom(11)
                    .bearing(0)
                    .tilt(45)
                    .build();
            LatLng sydney = new LatLng(locationListResponseModelArrayList.get(i).getLatitude(),
                    locationListResponseModelArrayList.get(i).getLongitude());
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 1000, null);
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(sydney);
            markerOptions.title(locationListResponseModelArrayList.get(i).getLocationName());
            markerOptions.snippet(locationListResponseModelArrayList.get(i).getAddress1());
            mMap.addMarker(markerOptions);
            //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationArrayList.get(i), 6));
        }

    }
}
