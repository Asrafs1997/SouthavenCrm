package com.thisit.southavencrm.locateUs.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.thisit.southavencrm.common.BasicAuth;
import com.thisit.southavencrm.common.ConfigApp;
import com.thisit.southavencrm.common.Constants;
import com.thisit.southavencrm.common.ToastMessage;
import com.thisit.southavencrm.locateUs.model.LocationListResponseModel;
import com.thisit.southavencrm.locateUs.view.ILocationListView;
import com.thisit.southavencrm.locateUs.view.LocationFragment;
import com.thisit.southavencrm.login.model.LoginResponseModel;
import com.thisit.southavencrm.webClient.ApiClient;
import com.thisit.southavencrm.webClientHandler.LocationAPI;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
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
