package com.thisit.southavencrm.webClientHandler;

import com.thisit.southavencrm.common.Constants;
import com.thisit.southavencrm.editprofile.model.EditProfileResponseModel;
import com.thisit.southavencrm.login.model.LoginResponseModel;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface EditProfileAPI {
    @POST(Constants.EDIT_PROFILE)
    Call<EditProfileResponseModel>editProfileAPI(@Body RequestBody rawString, @Header("Authorization") String auth);

}

