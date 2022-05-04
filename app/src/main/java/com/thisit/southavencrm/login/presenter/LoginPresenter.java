package com.thisit.southavencrm.login.presenter;

import android.util.Log;
import android.widget.Toast;

import com.thisit.southavencrm.common.BasicAuth;
import com.thisit.southavencrm.common.ConfigApp;
import com.thisit.southavencrm.common.Constants;
import com.thisit.southavencrm.common.ToastMessage;
import com.thisit.southavencrm.login.model.LoginResponseModel;
import com.thisit.southavencrm.login.view.ILoginView;
import com.thisit.southavencrm.webClient.ApiClient;
import com.thisit.southavencrm.webClientHandler.LoginAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements ILoginPresenter {

    private ILoginView iLoginView;
    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
    }
    @Override
    public void apiCall(final String LoginID, final String password) {
        if (LoginID.isEmpty()) {
            iLoginView.emptyUserName();
        } else if (password.isEmpty()) {
            iLoginView.emptyPassword();
        } else {
            //String requestData = "{\"LoginID\":\"88561711\",\"Password\":\"ef51f2f2\"}";
            String requestData = "{\"LoginID\":" + LoginID + ",\"Password\":\"" + password+ "\"}";
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
                        if (response.body() != null) {
                            if(response.body().isStatus()){
                                ConfigApp.setCompanyCode(response.body().getData().get(0).getCompanyCode());
                                ConfigApp.setContactCode(response.body().getData().get(0).getContactCode());
                                ConfigApp.setContactName(response.body().getData().get(0).getContactName());
                                ConfigApp.setLogin_ID(LoginID);
                                ConfigApp.setPassword(password);
                                iLoginView.onSuccess();
                            }

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
