package com.thisit.southavencrm.contactUs.presenter;

import com.thisit.southavencrm.common.BasicAuth;
import com.thisit.southavencrm.common.ConfigApp;
import com.thisit.southavencrm.common.Constants;
import com.thisit.southavencrm.common.ToastMessage;
import com.thisit.southavencrm.contactUs.model.ContactUsResponseModel;
import com.thisit.southavencrm.contactUs.view.iContactUsFragment;
import com.thisit.southavencrm.webClient.ApiClient;
import com.thisit.southavencrm.webClientHandler.ContactUsAPI;


import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUsPresenter implements IContactUsPresenter {
    private String requestString;

    private iContactUsFragment iContactUs;

    public ContactUsPresenter(iContactUsFragment iContactUs) {

        this.iContactUs = iContactUs;

    }


    @Override
    public void apiCall(String Subject, String Message) {


        System.out.println("ContactUsResponseModel 001  " + Message);
        System.out.println("ContactUsResponseModel 0012  " + Subject);


        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("CompanyCode", ConfigApp.getCompanyCode());
            jsonObj.put("ContactID", ConfigApp.getContactCode());
            jsonObj.put("Salutation", "SOUTHAVEN");
            jsonObj.put("ContactName", ConfigApp.getContactName());
            jsonObj.put("HandphoneNo", "");
            jsonObj.put("Email", ConfigApp.getEMAIL());
            jsonObj.put("Subject", Subject);
            jsonObj.put("Message", Message);
            requestString = "{\"Model\":" + jsonObj.toString() + "}";
            System.out.println("previewQR\t\t" + requestString);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("requestString\t\t" + requestString);
        RequestBody rawString = RequestBody.create(MediaType.parse("application/json"), requestString);
        ContactUsAPI changePasswordAPI = ApiClient.getClient(Constants.BASE_URL).create(ContactUsAPI.class);
        Call<ContactUsResponseModel> call = changePasswordAPI.ChangePassword(rawString, BasicAuth.getAuth());
        iContactUs.showProgress();
        call.enqueue(new Callback<ContactUsResponseModel>() {
            @Override
            public void onResponse(Call<ContactUsResponseModel> call, Response<ContactUsResponseModel> response) {
                iContactUs.hideProgress();
                if (response.body().isStatus()) {
                    iContactUs.onSuccess();
                } else {
                    iContactUs.onFailure();
                }
                ToastMessage.toast(response.message());
            }

            @Override
            public void onFailure(Call<ContactUsResponseModel> call, Throwable t) {
                iContactUs.hideProgress();
                //ToastMessage.toast(context, t.getMessage());
            }
        });


    }
}


