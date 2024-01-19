package com.thisit.softwaregroup.webClientHandler;




import com.thisit.softwaregroup.common.Constants;
import com.thisit.softwaregroup.registration.model.RegistrationRequestModel;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RegistrationAPI {
    @POST(Constants.REGISTRATION)
    Call<RegistrationRequestModel> registrationRequestModel(@Body RequestBody rawString, @Header("Authorization") String auth);


}
