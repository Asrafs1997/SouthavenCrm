package com.thisit.southavencrm.webClientHandler;




import com.thisit.southavencrm.common.Constants;
import com.thisit.southavencrm.login.model.LoginResponseModel;

import java.util.ArrayList;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface LoginAPI {
    @GET(Constants.LOGIN)
    Call<LoginResponseModel> login(@Query("RequestData") String requsetDate, @Header("Authorization") String auth);
    //Call<ArrayList<LoginResponseModel>> login(@Query("RequestData") String requsetDate, @Header("Authorization") String basicAuth);
    //Call<ArrayList<LoginResponseModel>> login(String userName, String password, String auth);
}
