package com.thisit.southavencrm.registration.presenter;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.thisit.southavencrm.common.BasicAuth;
import com.thisit.southavencrm.common.ConfigApp;
import com.thisit.southavencrm.common.Constants;
import com.thisit.southavencrm.common.ToastMessage;
import com.thisit.southavencrm.registration.model.RegistrationRequestModel;
import com.thisit.southavencrm.registration.view.IRegistrationView;
import com.thisit.southavencrm.webClient.ApiClient;
import com.thisit.southavencrm.webClientHandler.RegistrationAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationPresenter implements IRegistrationPresenter {

    private IRegistrationView iRegistrationView;
    private String RegistrationjsonObj = "";

    public RegistrationPresenter(IRegistrationView iRegistrationView) {
        this.iRegistrationView = iRegistrationView;
    }

    @Override
    public void apiCall(RegistrationRequestModel registrationRequestModel) {

        System.out.println("registrationRequestModel" + registrationRequestModel.getContactID());

        if (registrationRequestModel.getCompanyCode() == null || registrationRequestModel.getCompanyCode().isEmpty()) {
            ToastMessage.toast("Company name is empty");
        } else if (registrationRequestModel.getContactID() == null || registrationRequestModel.getContactID().isEmpty()) {
            ToastMessage.toast("Contact ID  is empty");
        } else {
            JSONObject jsonObj = new JSONObject();
            try {
                jsonObj.put("CompanyCode", registrationRequestModel.getCompanyCode());
                jsonObj.put("ContactID", registrationRequestModel.getContactID());
                jsonObj.put("Salutation", registrationRequestModel.getSalutation());
                jsonObj.put("ContactName", registrationRequestModel.getContactName());
                jsonObj.put("LastName", registrationRequestModel.getLastName());
                jsonObj.put("HandphoneNo", registrationRequestModel.getHandphoneNo());
                jsonObj.put("Email", registrationRequestModel.getEmail());
                jsonObj.put("Postalcode", registrationRequestModel.getPostalcode());
                jsonObj.put("Address1", registrationRequestModel.getAddress1());
                jsonObj.put("DOB", registrationRequestModel.getDOB());
                jsonObj.put("Password", registrationRequestModel.getPassword());
                ConfigApp.setCompanyCode(registrationRequestModel.getCompanyCode());
                ConfigApp.setContactCode(registrationRequestModel.getContactID());
                ConfigApp.setContactName(registrationRequestModel.getContactName());
                RegistrationjsonObj = "{\"Model\":" +jsonObj.toString()+"}";
                System.out.println("previewQR\t\t" + RegistrationjsonObj);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            RequestBody rawString = RequestBody.create(MediaType.parse("application/json"), RegistrationjsonObj);
            RegistrationAPI registrationAPI = ApiClient.getClient(Constants.BASE_URL).create(RegistrationAPI.class);
            Call<RegistrationRequestModel> call = registrationAPI.registrationRequestModel(rawString, BasicAuth.getAuth());

            iRegistrationView.showProgress();
            call.enqueue(new Callback<RegistrationRequestModel>() {
                @Override
                public void onResponse(@NonNull Call<RegistrationRequestModel> call, @NonNull Response<RegistrationRequestModel> response) {
                    iRegistrationView.hideProgress();
                    Log.i("response 001\t\t\t", response.body().getMsg());
                    if (response.isSuccessful()) {
                        if (response.body().isStatus()){
                            iRegistrationView.onSuccess(response.body().getMsg());
                        }else {
                            iRegistrationView.onFailure(response.body().getMsg());
                        }
                        return;
                    }

                }

                @Override
                public void onFailure(@NonNull Call<RegistrationRequestModel> call, @NonNull Throwable t) {
                    iRegistrationView.hideProgress();

                    //Toast.makeText(context, "Image upload is failed", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }
}
