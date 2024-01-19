package com.thisit.softwaregroup.webClientHandler;




import com.thisit.softwaregroup.common.Constants;
import com.thisit.softwaregroup.locateUs.model.LocationListResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface LocationAPI {
    @GET(Constants.LOCATION_LIST)
    //Call<ArrayList<LocationListResponseModel>> holdList(@Query("RequestData") String requestData, @Header("Authorization") String auth);
    Call<LocationListResponseModel> holdList(@Query("RequestData") String requsetDate, @Header("Authorization") String auth);
}
