package com.thisit.softwaregroup.locateUs.presenter;

import android.util.Log;

import com.thisit.softwaregroup.common.BasicAuth;
import com.thisit.softwaregroup.common.Constants;
import com.thisit.softwaregroup.locateUs.model.LocationListResponseModel;
import com.thisit.softwaregroup.locateUs.view.ILocationListView;
import com.thisit.softwaregroup.locateUs.view.LocationFragment;
import com.thisit.softwaregroup.webClient.ApiClient;
import com.thisit.softwaregroup.webClientHandler.LocationAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationListPresenter implements ILocationListPresenter {
    private ILocationListView iLocationListView;
    public LocationListPresenter(LocationFragment locationFragment) {
        this.iLocationListView = locationFragment;
    }
    @Override
    public void locationList(String CompanyCode) {

        LocationAPI locationAPI = ApiClient.getClient(Constants.BASE_URL).create(LocationAPI.class);

        //String requestData = "{\\\"CompanyCode\\\":1}";
        String requestData = "{\"CompanyCode\":" +CompanyCode + "}";
        Log.d("getHoldOrderList", requestData);
        Call<LocationListResponseModel> call = locationAPI.holdList(requestData, BasicAuth.getAuth());

        call.enqueue(new Callback<LocationListResponseModel>() {
            @Override
            public void onResponse(Call<LocationListResponseModel> call, Response<LocationListResponseModel> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if(response.body().isStatus()){
                            iLocationListView.getLocationList(response.body().getData());
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<LocationListResponseModel> call, Throwable t) {
                Log.i("onFailureResponse 001", t.getMessage().toString());
                //ToastMessage.toast(t.getMessage());
                iLocationListView.hideProgress();

            }
        });



    }
}
