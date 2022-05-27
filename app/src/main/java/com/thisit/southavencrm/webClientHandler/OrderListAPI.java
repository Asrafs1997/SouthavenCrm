package com.thisit.southavencrm.webClientHandler;


import com.thisit.southavencrm.FAQList.model.FAQListResponseModel;
import com.thisit.southavencrm.OrderList.model.OrderListResponseModel;
import com.thisit.southavencrm.common.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface OrderListAPI {
    @GET(Constants.ORDER_LIST)
    Call<OrderListResponseModel> holdList(@Query("RequestData") String requsetDate, @Header("Authorization") String auth);
}
