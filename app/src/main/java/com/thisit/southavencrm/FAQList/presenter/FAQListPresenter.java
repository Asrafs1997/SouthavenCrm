package com.thisit.southavencrm.FAQList.presenter;

import android.util.Log;

import com.thisit.southavencrm.FAQList.model.FAQListResponseModel;
import com.thisit.southavencrm.FAQList.view.FQAFragment;
import com.thisit.southavencrm.FAQList.view.IFAQListView;
import com.thisit.southavencrm.common.BasicAuth;
import com.thisit.southavencrm.common.Constants;
import com.thisit.southavencrm.locateUs.model.LocationListResponseModel;
import com.thisit.southavencrm.locateUs.presenter.ILocationListPresenter;
import com.thisit.southavencrm.locateUs.view.ILocationListView;
import com.thisit.southavencrm.locateUs.view.LocationFragment;
import com.thisit.southavencrm.webClient.ApiClient;
import com.thisit.southavencrm.webClientHandler.FAQAPI;
import com.thisit.southavencrm.webClientHandler.LocationAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FAQListPresenter implements IFAQListPresenter {
    private IFAQListView ifaqListView;
    public FAQListPresenter(FQAFragment fqaFragment) {
        this.ifaqListView = fqaFragment;
    }
    @Override
    public void locationList(String CompanyCode) {

        FAQAPI faqapi = ApiClient.getClient(Constants.BASE_URL).create(FAQAPI.class);

        //String requestData = "{\\\"CompanyCode\\\":1}";
        String requestData = "{\"CompanyCode\":" +CompanyCode + "}";
        Log.d("getHoldOrderList", requestData);
        Call<FAQListResponseModel> call = faqapi.holdList(requestData, BasicAuth.getAuth());
        ifaqListView.showProgress();
        call.enqueue(new Callback<FAQListResponseModel>() {
            @Override
            public void onResponse(Call<FAQListResponseModel> call, Response<FAQListResponseModel> response) {
              ifaqListView.hideProgress();
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if(response.body().isStatus()){
                            System.out.println("response\t\t\t"+response.body().get$id());
                            System.out.println("response001\t\t\t"+response.body().getMsg());
                            System.out.println("response002\t\t\t"+response.body().isStatus());
                            ifaqListView.getLocationList(response.body().getData());
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<FAQListResponseModel> call, Throwable t) {
                Log.i("onFailureResponse 001", t.getMessage().toString());
                //ToastMessage.toast(t.getMessage());
                ifaqListView.hideProgress();

            }
        });



    }
}
