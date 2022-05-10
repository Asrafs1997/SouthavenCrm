package com.thisit.southavencrm.webClientHandler;




import com.thisit.southavencrm.FAQList.model.FAQListResponseModel;
import com.thisit.southavencrm.common.Constants;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface FAQAPI {
    @GET(Constants.FAQ_LIST)
    //Call<ArrayList<LocationListResponseModel>> holdList(@Query("RequestData") String requestData, @Header("Authorization") String auth);
    Call<FAQListResponseModel> holdList(@Query("RequestData") String requsetDate, @Header("Authorization") String auth);
}
