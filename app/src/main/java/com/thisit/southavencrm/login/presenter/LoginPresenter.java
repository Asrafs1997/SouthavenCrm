package com.thisit.southavencrm.login.presenter;

import android.util.Log;

import com.thisit.southavencrm.common.BasicAuth;
import com.thisit.southavencrm.common.ConfigApp;
import com.thisit.southavencrm.common.Constants;
import com.thisit.southavencrm.login.model.LoginResponseModel;
import com.thisit.southavencrm.login.view.ILoginView;
import com.thisit.southavencrm.login.view.LoginActivity;
import com.thisit.southavencrm.webClient.ApiClient;
import com.thisit.southavencrm.webClientHandler.LoginAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements ILoginPresenter {

    private ILoginView iLoginView;

    public LoginPresenter(LoginActivity iLoginView) {
        this.iLoginView = iLoginView;
    }

    @Override
    public void apiCall(final String LoginID, final String password) {

        if (LoginID.isEmpty()) {
            iLoginView.emptyUserName();
        } else if (password.isEmpty()) {
            iLoginView.emptyPassword();
        } else {
            String requestData = "{\"LoginID\":\"" + LoginID +  "\""+ ",\"Password\":\"" + password + "\"}";
            //String requestData = "{\"LoginID\":" + LoginID + ",\"Password\":\"" + password + "\"}";
            Log.i("loginAPI", requestData);
            LoginAPI loginAPI = ApiClient.getClient(Constants.BASE_URL).create(LoginAPI.class);
//            RequestBody rawString = RequestBody.create(MediaType.parse("application/json"), requestData);

            Call<LoginResponseModel> call = loginAPI.login(requestData, BasicAuth.getAuth());
            iLoginView.showProgress();
            call.enqueue(new Callback<LoginResponseModel>() {
                @Override

                public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                    iLoginView.hideProgress();
                    if (response.isSuccessful()) {
                        Log.i("response\t\t", response.toString());
                        if (response.body() != null) {
                            if (response.body().isStatus()) {
                                if (response.body().getData().size() > 0) {
                                    ConfigApp.setCompanyCode(response.body().getData().get(0).getCompanyCode());
                                    ConfigApp.setContactCode(response.body().getData().get(0).getContactCode());
                                    ConfigApp.setContactName(response.body().getData().get(0).getContactName());
                                    ConfigApp.setContactID(response.body().getData().get(0).getContactID());
                                    ConfigApp.setEMAIL(response.body().getData().get(0).getEmail());
                                    ConfigApp.setLogin_ID(LoginID);
                                    ConfigApp.setPassword(password);
                                    iLoginView.onSuccess();
                                }else {
                                    iLoginView.onFailure(response.body().getMsg());
                                }
                            } else {
                                iLoginView.onFailure(response.body().getMsg());
                            }

                        } else {
                            iLoginView.onFailure(response.body().getMsg());
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                    Log.i("onFailureResponse 001", t.getMessage().toString());
                    //ToastMessage.toast(t.getMessage());
                    iLoginView.hideProgress();

                }
            });

        }
    }

}
