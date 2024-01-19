package com.thisit.softwaregroup.webClientHandler;

import com.thisit.softwaregroup.common.Constants;
import com.thisit.softwaregroup.editprofile.model.EditProfileResponseModel;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface EditProfileAPI {
    @POST(Constants.EDIT_PROFILE)
    Call<EditProfileResponseModel>editProfileAPI(@Body RequestBody rawString, @Header("Authorization") String auth);


    Call<EditProfileResponseModel> ContactUsAPi(RequestBody rawString, String auth);
}

