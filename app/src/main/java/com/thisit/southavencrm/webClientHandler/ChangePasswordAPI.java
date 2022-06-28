package com.thisit.southavencrm.webClientHandler;




import com.thisit.southavencrm.changePassword.model.ChangePasswordRequestModel;
import com.thisit.southavencrm.common.Constants;
import com.thisit.southavencrm.editprofile.model.EditProfileResponseModel;
import com.thisit.southavencrm.registration.model.RegistrationRequestModel;

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
