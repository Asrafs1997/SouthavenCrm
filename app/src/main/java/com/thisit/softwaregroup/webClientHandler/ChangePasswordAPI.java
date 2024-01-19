package com.thisit.softwaregroup.webClientHandler;




import com.thisit.softwaregroup.changePassword.model.ChangePasswordRequestModel;
import com.thisit.softwaregroup.common.Constants;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ChangePasswordAPI {
    @POST(Constants.CHANGEPASSWORED)
    Call<ChangePasswordRequestModel> ChangePassword(@Body RequestBody rawString, @Header("Authorization") String auth);
    //Call<ChangePasswordRequestModel> addCustomer(@Header("Authorization") String auth, @Body RequestBody rawString);
}
