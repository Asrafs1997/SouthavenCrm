package com.thisit.softwaregroup.GeneralSettings.presenter;

import android.util.Log;

import com.thisit.softwaregroup.GeneralSettings.model.GeneralSettingsRequestModel;
import com.thisit.softwaregroup.GeneralSettings.view.GeneralSettingsActivity;
import com.thisit.softwaregroup.GeneralSettings.view.IGeneralSettingsView;
import com.thisit.softwaregroup.common.BasicAuth;
import com.thisit.softwaregroup.common.Constants;
import com.thisit.softwaregroup.common.ToastMessage;
import com.thisit.softwaregroup.webClient.ApiClient;
import com.thisit.softwaregroup.webClientHandler.GeneralSettingsApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneralSettingsPresenter implements IGeneralSettingsPresenter {


    private IGeneralSettingsView iGeneralSettingsView;

    public GeneralSettingsPresenter(GeneralSettingsActivity iGeneralSettingsView) {
        this.iGeneralSettingsView = iGeneralSettingsView;

    }

    @Override
    public void apiCall(String CompanyCode) {
        if (CompanyCode.isEmpty()) {
            ToastMessage.toast("CompanyCode  is empty");
        }  else {
            String requestData = "{\"CompanyCode\":" + CompanyCode + "}";
            Log.i("generalsettingsApi", requestData);
            GeneralSettingsApi generalSettingsApi = ApiClient.getClient(Constants.BASE_URL).create(GeneralSettingsApi.class);
//            RequestBody rawString = RequestBody.create(MediaType.parse("application/json"), requestData);
            Call<GeneralSettingsRequestModel> call = generalSettingsApi.general(requestData, BasicAuth.getAuth());
            iGeneralSettingsView.showProgress();
            call.enqueue(new Callback<GeneralSettingsRequestModel>() {
                @Override
                public void onResponse(Call<GeneralSettingsRequestModel> call, Response<GeneralSettingsRequestModel> response) {
                    iGeneralSettingsView.hideProgress();

                    System.out.println("SettingID" + response.toString());

                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            if (response.body().isStatus()) {
                                if (response.body().getData().size() > 0) {
                                    System.out.println("SettingID" + response.body().getData().get(0).getSettingID());
                                } else {
                                    iGeneralSettingsView.onFailure();
                                }
                            } else {
                                iGeneralSettingsView.onFailure();
                            }

                        } else {
                            iGeneralSettingsView.onFailure();
                        }
                    }
                }


                @Override
                public void onFailure(Call<GeneralSettingsRequestModel> call, Throwable t) {
                    Log.i("onFailureResponse 001", t.getMessage().toString());
                    //ToastMessage.toast(t.getMessage());
                    iGeneralSettingsView.hideProgress();

                }
            });
        }

    }

}
