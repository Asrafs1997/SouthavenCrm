package com.thisit.southavencrm.webClientHandler;




import com.thisit.southavencrm.common.Constants;
import com.thisit.southavencrm.registration.model.RegistrationRequestModel;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RegistrationAPI {
    @POST(Constants.REGISTRATION)
    Call<RegistrationRequestModel> registrationRequestModel(@Body RequestBody rawString, @Header("Authorization") String auth);


}
