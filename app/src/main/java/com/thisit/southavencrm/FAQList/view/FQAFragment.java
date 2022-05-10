package com.thisit.southavencrm.FAQList.view;

import android.app.Activity;
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
import com.google.android.gms.maps.model.MarkerOptions;
import com.thisit.southavencrm.FAQList.model.FAQListResponseModel;
import com.thisit.southavencrm.FAQList.presenter.FAQListPresenter;
import com.thisit.southavencrm.FAQList.presenter.IFAQListPresenter;
import com.thisit.southavencrm.R;
import com.thisit.southavencrm.common.ConfigApp;
import com.thisit.southavencrm.locateUs.adapter.LocationAdapter;
import com.thisit.southavencrm.locateUs.model.LocationListResponseModel;
import com.thisit.southavencrm.locateUs.presenter.ILocationListPresenter;
import com.thisit.southavencrm.locateUs.presenter.LocationListPresenter;
import com.thisit.southavencrm.locateUs.view.ILocationListView;

import java.util.ArrayList;

public class FQAFragment extends Fragment implements IFAQListView {
    private View root;
    private Activity activity;
    private IFAQListPresenter ifaqListPresenter;
    private ArrayList<FAQListResponseModel> locationListResponseModelArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_location, container, false);
        activity = getActivity();



        ifaqListPresenter = new FAQListPresenter(this);
        if (ConfigApp.isNetworkAvailable(activity)) {
            ifaqListPresenter.locationList(ConfigApp.getCompanyCode());
        } else {
            Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

        return root;
    }



    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void getLocationList(ArrayList<FAQListResponseModel> holdListResponseModelArrayList) {
        locationListResponseModelArrayList = new ArrayList<FAQListResponseModel>();

    }


    @Override
    public void onSuccess() {

    }

    @Override
    public void holdListClick(int position) {

    }


}

