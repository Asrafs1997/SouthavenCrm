package com.thisit.southavencrm.webClientHandler;




import com.thisit.southavencrm.common.Constants;
import com.thisit.southavencrm.locateUs.model.LocationListResponseModel;
import com.thisit.southavencrm.login.model.LoginResponseModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface LocationAPI {
    @GET(Constants.LOCATION_LIST)
    //Call<ArrayList<LocationListResponseModel>> holdList(@Query("RequestData") String requestData, @Header("Authorization") String auth);
    Call<LocationListResponseModel> holdList(@Query("RequestData") String requsetDate, @Header("Authorization") String auth);
}
