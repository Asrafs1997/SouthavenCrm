package com.thisit.southavencrm.changePassword.presenter;

import android.util.Log;

import com.thisit.southavencrm.changePassword.view.ChangePasswordFragment;
import com.thisit.southavencrm.changePassword.view.IChangePasswordFragment;
import com.thisit.southavencrm.changePassword.model.ChangePasswordRequestModel;
import com.thisit.southavencrm.common.BasicAuth;
import com.thisit.southavencrm.common.ConfigApp;
import com.thisit.southavencrm.common.Constants;
import com.thisit.southavencrm.common.ToastMessage;
import com.thisit.southavencrm.webClient.ApiClient;
import com.thisit.southavencrm.webClientHandler.ChangePasswordAPI;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordPresenter implements IChangePasswordPresenter {
    private IChangePasswordFragment iChangePasswordFragment;
    private String requestString = "";

    public ChangePasswordPresenter(ChangePasswordFragment changePasswordFragment) {
        this.iChangePasswordFragment = changePasswordFragment;
    }


    @Override
    public void apiCall(String currentpassword, String newpassword, String confirmpassword) {
        if (currentpassword == null || currentpassword.isEmpty()) {
            ToastMessage.toast("current password  is empty");
        } else if (newpassword == null || newpassword.isEmpty()) {
            ToastMessage.toast("New password  is empty");
        } else if (confirmpassword == null || confirmpassword.isEmpty()) {
            ToastMessage.toast("confirm password  is empty");
        } else {

            String requestData = "{\"CompanyCode\":" + ConfigApp.getCompanyCode() + ",\"ContactID\":\"" + ConfigApp.getContactID() + "\",\"Password\":\"" + currentpassword + "\",\"NewPassword\":\"" + newpassword + "\"}";
            requestString = "{\"Model\":" + requestData + "}";
            Log.d("requestData1", requestString);

            RequestBody rawString = RequestBody.create(MediaType.parse("application/json"), requestString);
            ChangePasswordAPI changePasswordAPI = ApiClient.getClient(Constants.BASE_URL).create(ChangePasswordAPI.class);
            Call<ChangePasswordRequestModel> call = changePasswordAPI.ChangePassword(rawString, BasicAuth.getAuth());
            iChangePasswordFragment.showProgress();
            call.enqueue(new Callback<ChangePasswordRequestModel>() {
                @Override
                public void onResponse(Call<ChangePasswordRequestModel> call, Response<ChangePasswordRequestModel> response) {
                    iChangePasswordFragment.hideProgress();

                    if (response.body().isStatus()) {
                        iChangePasswordFragment.onSuccess("");
                    } else {
                        iChangePasswordFragment.onFailure("");
                    }
                    ToastMessage.toast(response.message());
                }

                @Override
                public void onFailure(Call<ChangePasswordRequestModel> call, Throwable t) {
                    iChangePasswordFragment.hideProgress();
                    System.out.println("getMessage"+t.getMessage());
                }
            });


        }
    }
}
