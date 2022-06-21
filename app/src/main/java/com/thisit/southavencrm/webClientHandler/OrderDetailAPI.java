package com.thisit.southavencrm.webClientHandler;

import com.thisit.southavencrm.OrderDetail.model.OrderDetailResponseModel;
import com.thisit.southavencrm.common.Constants;
import com.thisit.southavencrm.login.model.LoginResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface OrderDetailAPI {

        @GET(Constants.ORDER_Detail)
        Call<OrderDetailResponseModel> orderDetail(@Query("RequestData") String requsetDate, @Header("Authorization") String auth);
    }


