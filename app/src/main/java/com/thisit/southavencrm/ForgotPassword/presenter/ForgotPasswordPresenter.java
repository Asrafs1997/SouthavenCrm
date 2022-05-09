package com.thisit.southavencrm.ForgotPassword.presenter;

import android.util.Log;

import com.thisit.southavencrm.ForgotPassword.model.ForgotPasswordResponseModel;
import com.thisit.southavencrm.ForgotPassword.view.ForgotpasswordActivity;
import com.thisit.southavencrm.ForgotPassword.view.IForgotPasswordView;
import com.thisit.southavencrm.common.BasicAuth;
import com.thisit.southavencrm.common.ConfigApp;
import com.thisit.southavencrm.common.Constants;
import com.thisit.southavencrm.login.model.LoginResponseModel;
import com.thisit.southavencrm.login.presenter.ILoginPresenter;
import com.thisit.southavencrm.login.view.ILoginView;
import com.thisit.southavencrm.webClient.ApiClient;
import com.thisit.southavencrm.webClientHandler.ForgotPasswordAPI;
import com.thisit.southavencrm.webClientHandler.LoginAPI;

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
