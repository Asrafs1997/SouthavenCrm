package com.thisit.softwaregroup.dashboard.presenter;
import android.util.Log;

import com.thisit.softwaregroup.common.BaseFragment;
import com.thisit.softwaregroup.common.BasicAuth;
import com.thisit.softwaregroup.common.ConfigApp;
import com.thisit.softwaregroup.common.Constants;
import com.thisit.softwaregroup.dashboard.model.GetprofileResponseModel;
import com.thisit.softwaregroup.dashboard.view.IGetprofileView;
import com.thisit.softwaregroup.webClient.ApiClient;
import com.thisit.softwaregroup.webClientHandler.GetprofileAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetprofilePresenter  implements IGetprofilePresenter {
    private IGetprofileView iGetprofileView;

    public GetprofilePresenter(BaseFragment baseFragment) {

        this.iGetprofileView = baseFragment;
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
           // iGetprofileView.showProgress();
            call.enqueue(new Callback<GetprofileResponseModel>() {
                @Override
                public void onResponse(Call<GetprofileResponseModel> call, Response<GetprofileResponseModel> response) {
                    //iGetprofileView.hideProgress();
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            if(response.body().isStatus()){
                                if(response.body().getData().size()>0){
                                    ConfigApp.setCompanyCode(response.body().getData().get(0).getCompanyCode());
                                    ConfigApp.setContactName(response.body().getData().get(0).getContactName());
                                    ConfigApp.setLastName(response.body().getData().get(0).getLastName());
                                    ConfigApp.setMOBILE_NUMBER(response.body().getData().get(0).getHandphoneNo());
                                    ConfigApp.setEMAIL(response.body().getData().get(0).getEmail());
                                    ConfigApp.setPOSTALCODE(response.body().getData().get(0).getPostalcode());
                                    ConfigApp.setADDRESS(response.body().getData().get(0).getAddress());
                                    ConfigApp.setDOB(response.body().getData().get(0).getDOB());
                                    ConfigApp.setTitle(response.body().getData().get(0).getTitle());
                                    System.out.println("response getDOB "+response.body().getData().get(0).getDOB());
                                    //iGetprofileView.onSuccess();
                                }
                            }

                        }
                    }
                }

                @Override
                public void onFailure(Call<GetprofileResponseModel> call, Throwable t) {
                    Log.i("onFailureResponse 001", t.getMessage().toString());
                    //ToastMessage.toast(t.getMessage());
                   // iGetprofileView.hideProgress();

                }
            });

        }
        }
    }


