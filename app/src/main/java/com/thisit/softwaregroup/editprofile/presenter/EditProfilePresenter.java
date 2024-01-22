package com.thisit.softwaregroup.editprofile.presenter;

import androidx.annotation.NonNull;

import com.thisit.softwaregroup.common.BasicAuth;
import com.thisit.softwaregroup.common.ConfigApp;
import com.thisit.softwaregroup.common.Constants;
import com.thisit.softwaregroup.common.ToastMessage;
import com.thisit.softwaregroup.editprofile.model.EditProfileResponseModel;
import com.thisit.softwaregroup.editprofile.view.EditProfileFragment;
import com.thisit.softwaregroup.editprofile.view.iEditProfile;
import com.thisit.softwaregroup.webClient.ApiClient;
import com.thisit.softwaregroup.webClientHandler.EditProfileAPI;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfilePresenter implements IEditProfilePresenter {
    private String RegistrationjsonObj;
    private iEditProfile iEditProfile;

    public EditProfilePresenter(EditProfileFragment editProfileFragment) {
        this.iEditProfile = editProfileFragment;
    }

    @Override
    public void apiCall(EditProfileResponseModel editProfileResponseModel) {
        if (editProfileResponseModel.getContactName() == null || editProfileResponseModel.getContactName().isEmpty()) {
            ToastMessage.toast(" Name is empty");
        } else if (editProfileResponseModel.getHandphoneNo() == null || editProfileResponseModel.getHandphoneNo().isEmpty()) {
            ToastMessage.toast(" Mobile Number is empty");
      //  }
        //else if (editProfileResponseModel.getLastName() == null || editProfileResponseModel.getLastName().isEmpty()) {
        //    ToastMessage.toast(" LastName  is empty");
        } else if (editProfileResponseModel.getEmail() == null || editProfileResponseModel.getEmail().isEmpty()) {
            ToastMessage.toast(" Email is empty");
        } else if (editProfileResponseModel.getPostalcode() == null || editProfileResponseModel.getPostalcode().isEmpty()) {
            ToastMessage.toast(" PostalCode is empty");
        } else if (editProfileResponseModel.getAddress1() == null || editProfileResponseModel.getAddress1().isEmpty()) {
            ToastMessage.toast(" Address is empty");
        } else if (editProfileResponseModel.getDOB() == null || editProfileResponseModel.getDOB().isEmpty()) {
            ToastMessage.toast(" DOB is empty");
        } else {
            JSONObject jsonObj = new JSONObject();
            try {
                jsonObj.put("CompanyCode", "1");
                jsonObj.put("ContactID", ConfigApp.getContactID());
                jsonObj.put("Salutation", editProfileResponseModel.getSalutation());
                jsonObj.put("ContactName", editProfileResponseModel.getContactName());
         //       jsonObj.put("LastName", editProfileResponseModel.getLastName());
                //jsonObj.put("LastName", editProfileResponseModel.getLastName());
                jsonObj.put("HandphoneNo", editProfileResponseModel.getHandphoneNo());
                jsonObj.put("Email", editProfileResponseModel.getEmail());
                jsonObj.put("Postalcode", editProfileResponseModel.getPostalcode());
                jsonObj.put("Address1", editProfileResponseModel.getAddress1());
                jsonObj.put("DOB", editProfileResponseModel.getDOB());
                //jsonObj.put("Password", editProfileResponseModel.getPassword());
                RegistrationjsonObj = "{\"Model\":" + jsonObj.toString() + "}";
                System.out.println("previewQR\t\t" + RegistrationjsonObj);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            RequestBody rawString = RequestBody.create(MediaType.parse("application/json"), RegistrationjsonObj);
            EditProfileAPI editProfileAPI = ApiClient.getClient(Constants.BASE_URL).create(EditProfileAPI.class);
            Call<EditProfileResponseModel> call = editProfileAPI.editProfileAPI(rawString, BasicAuth.getAuth());

            iEditProfile.showProgress();
            call.enqueue(new Callback<EditProfileResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<EditProfileResponseModel> call, @NonNull Response<EditProfileResponseModel> response) {
                    iEditProfile.hideProgress();
                    System.out.println("response EditProfile\t\t" + response.toString());
                    if (response.isSuccessful()) {
                        if (response.body().isStatus()) {
                            iEditProfile.onSuccess(response.body().getMsg());
                        } else {
                            iEditProfile.onFailure(response.body().getMsg());
                        }
                        return;
                    }
                }

                @Override
                public void onFailure(@NonNull Call<EditProfileResponseModel> call, @NonNull Throwable t) {
                    iEditProfile.hideProgress();

                    //Toast.makeText(context, "Image upload is failed", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

}