package com.thisit.southavencrm.dashboard.presenter;
import android.util.Log;

import com.thisit.southavencrm.common.BasicAuth;
import com.thisit.southavencrm.common.ConfigApp;
import com.thisit.southavencrm.common.Constants;
import com.thisit.southavencrm.dashboard.model.GetprofileResponseModel;
import com.thisit.southavencrm.dashboard.view.ECardActivity;
import com.thisit.southavencrm.dashboard.view.IGetprofileView;
import com.thisit.southavencrm.login.model.LoginResponseModel;
import com.thisit.southavencrm.webClient.ApiClient;
import com.thisit.southavencrm.webClientHandler.GetprofileAPI;
import com.thisit.southavencrm.webClientHandler.LoginAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetprofilePresenter implements IGetprofilePresenter {
    private IGetprofileView iGetprofileView;

    public GetprofilePresenter(ECardActivity eCardActivity) {

        this.iGetprofileView = eCardActivity;
    }

    @Override
    public void apiCall(final String CompanyCode, final String ContactID) {

        if (CompanyCode.isEmpty()) {
            iGetprofileView.CompanyCode();
        } else if (ContactID.isEmpty()) {
            iGetprofileView.ContactID();
        } else {
            String requestData = "{\"CompanyCode\":" + CompanyCode + ",\"ContactID\":\"" + ContactID+ "\"}";
            Log.i("getprofileAPI", requestData);
            GetprofileAPI getprofileAPI = ApiClient.getClient(Constants.BASE_URL).create(GetprofileAPI.class);
            Call<GetprofileResponseModel> call = getprofileAPI.getprofile(requestData, BasicAuth.getAuth());
            iGetprofileView.showProgress();
            call.enqueue(new Callback<GetprofileResponseModel>() {
                @Override
                public void onResponse(Call<GetprofileResponseModel> call, Response<GetprofileResponseModel> response) {
                    iGetprofileView.hideProgress();
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            if(response.body().isStatus()){

                                ConfigApp.setCompanyCode(response.body().getData().get(0).getCompanyCode());
                                ConfigApp.setContactName(response.body().getData().get(0).getContactName());
                                ConfigApp.setMOBILE_NUMBER(response.body().getData().get(0).getHandphoneNo());
                                ConfigApp.setEMAIL(response.body().getData().get(0).getEmail());
                                ConfigApp.setPOSTALCODE(response.body().getData().get(0).getPostalcode());
                                ConfigApp.setADDRESS(response.body().getData().get(0).getAddress());
                                ConfigApp.setDOB(response.body().getData().get(0).getDOB());
                                System.out.println("response 0001"+response.body().getData().get(0).getEmail());

                                iGetprofileView.onSuccess();
                            }

                        }
                    }
                }

                @Override
                public void onFailure(Call<GetprofileResponseModel> call, Throwable t) {
                    Log.i("onFailureResponse 001", t.getMessage().toString());
                    //ToastMessage.toast(t.getMessage());
                    iGetprofileView.hideProgress();

                }
            });

        }
        }
    }


