package com.thisit.softwaregroup.webClientHandler;




import com.thisit.softwaregroup.common.Constants;
import com.thisit.softwaregroup.login.model.LoginResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface LoginAPI {
    @GET(Constants.LOGIN)
    Call<LoginResponseModel> login(@Query("RequestData") String requsetDate, @Header("Authorization") String auth);
}
