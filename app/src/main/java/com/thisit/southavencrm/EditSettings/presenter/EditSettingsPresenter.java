package com.thisit.southavencrm.EditSettings.presenter;


import android.util.Log;

import androidx.annotation.NonNull;

import com.thisit.southavencrm.EditSettings.model.DeviceSettingResponseModel;
import com.thisit.southavencrm.EditSettings.model.EditSettingsResponseModel;
import com.thisit.southavencrm.EditSettings.view.EditSettingsFragment;
import com.thisit.southavencrm.EditSettings.view.IEditSettingsView;
import com.thisit.southavencrm.GeneralSettings.model.GeneralSettingsRequestModel;
import com.thisit.southavencrm.changePassword.model.ChangePasswordRequestModel;
import com.thisit.southavencrm.changePassword.presenter.IChangePasswordPresenter;
import com.thisit.southavencrm.common.BasicAuth;
import com.thisit.southavencrm.common.ConfigApp;
import com.thisit.southavencrm.common.Constants;
import com.thisit.southavencrm.common.ToastMessage;
import com.thisit.southavencrm.editprofile.model.EditProfileResponseModel;
import com.thisit.southavencrm.webClient.ApiClient;
import com.thisit.southavencrm.webClientHandler.ChangePasswordAPI;
import com.thisit.southavencrm.webClientHandler.EditProfileAPI;
import com.thisit.southavencrm.webClientHandler.EditSettingsApi;
import com.thisit.southavencrm.webClientHandler.GeneralSettingsApi;
import com.thisit.southavencrm.webClientHandler.SaveDeviceSettingAPI;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditSettingsPresenter implements IEditSettingsPresenter {

    private IEditSettingsView iEditSettingsView;
    private String SaveDeviceapi = "";

    public EditSettingsPresenter(EditSettingsFragment iEditSettingsView) {
        this.iEditSettingsView = iEditSettingsView;

    }

    @Override
    public void apiCall(String DeviceID) {
        if (DeviceID == null || DeviceID.isEmpty()) {
            ToastMessage.toast("DeviceID  is empty");
        } else {

            String requestData = "{\"DeviceID\":\"" + DeviceID + "\"}";
            Log.d("nextNumberApi", requestData);

            EditSettingsApi editSettingsApi = ApiClient.getClient(Constants.BASE_URL).create(EditSettingsApi.class);
            Call<EditSettingsResponseModel> call = editSettingsApi.general(requestData, BasicAuth.getAuth());
            iEditSettingsView.showProgress();
            call.enqueue(new Callback<EditSettingsResponseModel>() {
                @Override
                public void onResponse(Call<EditSettingsResponseModel> call, Response<EditSettingsResponseModel> response) {
                    iEditSettingsView.hideProgress();
                    if (response.isSuccessful()) {
                        Log.i("response\t\t", response.toString());
                        if (response.body() != null) {
                            if (response.body().isStatus()) {
                                if (response.body().getData().size() > 0) {
                                    iEditSettingsView.HasPromoNotification(response.body().getData().get(0).isHasOrderNotification());
                                    iEditSettingsView.HasOrderNotification(response.body().getData().get(0).isHasOrderNotification());
                                } else {
                                    iEditSettingsView.onFailure();
                                }
                            } else {
                                iEditSettingsView.onFailure();
                            }

                        } else {
                            iEditSettingsView.onFailure();
                        }
                    }
                }

                @Override
                public void onFailure(Call<EditSettingsResponseModel> call, Throwable t) {
                    Log.i("onFailureResponse 001", t.getMessage().toString());
                    //ToastMessage.toast(t.getMessage());
                    iEditSettingsView.hideProgress();

                }


            });

        }
    }

    @Override
    public void SaveDeviceapiCall(String DeviceID, String DeviceName, boolean HasPromoNotification, boolean HasOrderNotification) {
        Log.d("SaveDeviceapiCall", DeviceID + "\t\t" + DeviceName + "\t\t" + HasOrderNotification + "\t\t" + HasPromoNotification);
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("DeviceID", DeviceID);
            jsonObj.put("DeviceName", DeviceName);
            jsonObj.put("HasPromoNotification", HasPromoNotification);
            jsonObj.put("HasOrderNotification", HasOrderNotification);
            SaveDeviceapi = "{\"Model\":" + jsonObj.toString() + "}";
            System.out.println("previewQR\t\t" + SaveDeviceapi);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        RequestBody rawString = RequestBody.create(MediaType.parse("application/json"), SaveDeviceapi);
        SaveDeviceSettingAPI saveDeviceSettingAPI = ApiClient.getClient(Constants.BASE_URL).create(SaveDeviceSettingAPI.class);
        Call<DeviceSettingResponseModel> call = saveDeviceSettingAPI.SaveDeviceAPI(rawString, BasicAuth.getAuth());

        iEditSettingsView.showProgress();
        call.enqueue(new Callback<DeviceSettingResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<DeviceSettingResponseModel> call, @NonNull Response<DeviceSettingResponseModel> response) {
                iEditSettingsView.hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().isStatus()) {
                        iEditSettingsView.onSuccess();
                    } else {
                        iEditSettingsView.onFailure();
                    }
                    return;
                }
            }

            @Override
            public void onFailure(@NonNull Call<DeviceSettingResponseModel> call, @NonNull Throwable t) {
                iEditSettingsView.hideProgress();

                //Toast.makeText(context, "Image upload is failed", Toast.LENGTH_SHORT).show();
            }
        });

    }


}