package com.thisit.southavencrm.changePassword.presenter;

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

public class ChangePasswordPresentr implements IChangePasswordPresentr {
    private IChangePasswordFragment iChangePasswordFragment;
    private String requestString = "";

    public ChangePasswordPresentr(IChangePasswordFragment iChangePasswordFragment) {
        this.iChangePasswordFragment = iChangePasswordFragment;
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
            //ChangePasswordjsonObj  = "{\"Model\":{\"CompanyCode\":\"1\",\"ContactId\":\"7708440879\",\"Password\":\"123456\",\"NewPassword\":\"123456\"}}";
            JSONObject jsonObj = new JSONObject();
            try {
                jsonObj.put("CompanyCode", ConfigApp.getCompanyCode());
                jsonObj.put("ContactId", ConfigApp.getContactID());
                jsonObj.put("Password", currentpassword);
                jsonObj.put("NewPassword", newpassword);
                requestString = "{\"Model\":" + jsonObj.toString() + "}";
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            System.out.println("requestString\t\t" + requestString);
            RequestBody rawString = RequestBody.create(MediaType.parse("application/json"), requestString);
            ChangePasswordAPI changePasswordAPI = ApiClient.getClient(Constants.BASE_URL).create(ChangePasswordAPI.class);
            Call<ChangePasswordRequestModel> call = changePasswordAPI.ChangePassword(rawString, BasicAuth.getAuth());
            iChangePasswordFragment.showProgress();
            call.enqueue(new Callback<ChangePasswordRequestModel>() {
                @Override
                public void onResponse(Call<ChangePasswordRequestModel> call, Response<ChangePasswordRequestModel> response) {
                    iChangePasswordFragment.hideProgress();
                    if (response.isSuccessful() && response.body() != null) {
                        if (response.body().isStatus()) {
                            iChangePasswordFragment.onSuccess(response.body().getMsg());
                        } else {
                            iChangePasswordFragment.onFailure(response.body().getMsg());
                        }
                        return;
                    }
                    ToastMessage.toast(response.message());
                }

                @Override
                public void onFailure(Call<ChangePasswordRequestModel> call, Throwable t) {
                    iChangePasswordFragment.hideProgress();
                    //ToastMessage.toast(context, t.getMessage());
                }
            });


        }
    }
}
