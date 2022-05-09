package com.thisit.southavencrm.webClientHandler;

import com.thisit.southavencrm.ForgotPassword.model.ForgotPasswordResponseModel;
import com.thisit.southavencrm.common.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ForgotPasswordAPI {
    @GET(Constants.RESETPASSWORD)
    Call<ForgotPasswordResponseModel> forgotPasswordAPI(@Query("RequestData") String requsetDate, @Header("Authorization") String auth);
}
