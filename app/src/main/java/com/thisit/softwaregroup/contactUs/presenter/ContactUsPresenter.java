package com.thisit.softwaregroup.contactUs.presenter;

import com.thisit.softwaregroup.common.BasicAuth;
import com.thisit.softwaregroup.common.ConfigApp;
import com.thisit.softwaregroup.common.Constants;
import com.thisit.softwaregroup.contactUs.model.ContactUsResponseModel;
import com.thisit.softwaregroup.contactUs.view.iContactUsFragment;
import com.thisit.softwaregroup.webClient.ApiClient;
import com.thisit.softwaregroup.webClientHandler.ContactUsAPI;


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
            jsonObj.put("HandphoneNo", ConfigApp.getMOBILE_NUMBER());
            jsonObj.put("Email", ConfigApp.getEMAIL());
            jsonObj.put("Subject", Subject);
            jsonObj.put("Message", Message);
            System.out.println("jsonObj123" +jsonObj.toString());
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

                System.out.println("ContactUs response\t\t" + response.toString());
                if (response.body().isStatus()) {
                    iContactUs.onSuccess();
                } else {
                    iContactUs.onFailure();
                }
              //  ToastMessage.toast(response.message());
            }

            @Override
            public void onFailure(Call<ContactUsResponseModel> call, Throwable t) {
                iContactUs.hideProgress();

                //ToastMessage.toast(context, t.getMessage());
            }
        });


    }
}


