package com.thisit.softwaregroup.ForgotPassword.presenter;

import android.util.Log;

import com.thisit.softwaregroup.ForgotPassword.model.ForgotPasswordResponseModel;
import com.thisit.softwaregroup.ForgotPassword.view.ForgotpasswordActivity;
import com.thisit.softwaregroup.ForgotPassword.view.IForgotPasswordView;
import com.thisit.softwaregroup.common.BasicAuth;
import com.thisit.softwaregroup.common.Constants;
import com.thisit.softwaregroup.webClient.ApiClient;
import com.thisit.softwaregroup.webClientHandler.ForgotPasswordAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordPresenter implements IForgotPasswordPresenter {

    private IForgotPasswordView iForgotPasswordView;

    public ForgotPasswordPresenter(ForgotpasswordActivity forgotpasswordActivity) {

        this.iForgotPasswordView = forgotpasswordActivity;
    }

    @Override
    public void apiCall(final String Email_ID) {
        if (Email_ID.isEmpty()) {
            iForgotPasswordView.emptyEmailId();
        } else {
            iForgotPasswordView.showProgress();
            ForgotPasswordAPI forgotPasswordAPI = ApiClient.getClient(Constants.BASE_URL).create(ForgotPasswordAPI.class);
            String requestData = "{\"Email\":\"" + Email_ID + "\"}";
            Log.d("getHoldOrderList", requestData);
            Call<ForgotPasswordResponseModel> call = forgotPasswordAPI.forgotPasswordAPI(requestData, BasicAuth.getAuth());
            iForgotPasswordView.showProgress();
            call.enqueue(new Callback<ForgotPasswordResponseModel>() {
                @Override
                public void onResponse(Call<ForgotPasswordResponseModel> call, Response<ForgotPasswordResponseModel> response) {
                    iForgotPasswordView.hideProgress();
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            if (response.body().isResult()) {
                                iForgotPasswordView.onSuccess(response.body().getMsg());
                            } else {
                                iForgotPasswordView.onFailure(response.body().getMsg());
                            }

                        }
                    } else {
                        iForgotPasswordView.onFailure(response.body().getMsg());
                    }
                }

                @Override
                public void onFailure(Call<ForgotPasswordResponseModel> call, Throwable t) {
                    Log.i("onFailureResponse 001", t.getMessage().toString());
                    //ToastMessage.toast(t.getMessage());
                    iForgotPasswordView.hideProgress();

                }
            });

        }
    }

}
