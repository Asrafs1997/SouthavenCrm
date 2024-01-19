package com.thisit.softwaregroup.registration.presenter;

import com.thisit.softwaregroup.common.ConfigApp;
import com.thisit.softwaregroup.common.ToastMessage;
import com.thisit.softwaregroup.registration.model.RegistrationRequestModel;
import com.thisit.softwaregroup.registration.view.IRegistrationView;

import org.json.JSONException;
import org.json.JSONObject;

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
                ConfigApp.setContactName(registrationRequestModel.getContactName());
                RegistrationjsonObj = "{\"Model\":" +jsonObj.toString()+"}";
                System.out.println("registration json object data\t\t" + RegistrationjsonObj);


                ConfigApp.setCompanyCode(registrationRequestModel.getCompanyCode());
                ConfigApp.setContactCode(registrationRequestModel.getContactID());
                ConfigApp.setContactName(registrationRequestModel.getContactName());
                ConfigApp.setContactID(registrationRequestModel.getContactID());
                ConfigApp.setEMAIL(registrationRequestModel.getEmail());
                ConfigApp.setLOGIN_STATUS("true");
                ConfigApp.setLogin_ID(registrationRequestModel.getEmail());
                ConfigApp.setPassword( registrationRequestModel.getPassword());
                ConfigApp.setLastName(registrationRequestModel.getLastName());
                ConfigApp.setMOBILE_NUMBER(registrationRequestModel.getHandphoneNo());
                ConfigApp.setPOSTALCODE(registrationRequestModel.getPostalcode());
                ConfigApp.setADDRESS(registrationRequestModel.getAddress1());
                ConfigApp.setDOB(registrationRequestModel.getDOB());

                iRegistrationView.onSuccess("Success");


            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
           /* RequestBody rawString = RequestBody.create(MediaType.parse("application/json"), RegistrationjsonObj);
            RegistrationAPI registrationAPI = ApiClient.getClient(Constants.BASE_URL).create(RegistrationAPI.class);
            Call<RegistrationRequestModel> call = registrationAPI.registrationRequestModel(rawString, BasicAuth.getAuth());

            iRegistrationView.showProgress();
            call.enqueue(new Callback<RegistrationRequestModel>() {
                @Override
                public void onResponse(@NonNull Call<RegistrationRequestModel> call, @NonNull Response<RegistrationRequestModel> response) {
                    iRegistrationView.hideProgress();
                    if (response.isSuccessful()) {
                        if (response.body().isStatus()){
                            iRegistrationView.onSuccess(response.body().getMsg());
                        }else {
                            iRegistrationView.onFailure(response.body().getMsg());
                            iRegistrationView.onSuccess(response.body().getMsg());
                        }
                        return;
                    }

                }

                @Override
                public void onFailure(@NonNull Call<RegistrationRequestModel> call, @NonNull Throwable t) {
                    iRegistrationView.hideProgress();

                    //Toast.makeText(context, "Image upload is failed", Toast.LENGTH_SHORT).show();
                }
            });*/


        }
    }
}
