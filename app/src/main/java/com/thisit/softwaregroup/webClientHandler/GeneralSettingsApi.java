package com.thisit.softwaregroup.webClientHandler;

import com.thisit.softwaregroup.GeneralSettings.model.GeneralSettingsRequestModel;
import com.thisit.softwaregroup.common.Constants;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface GeneralSettingsApi {
    @GET(Constants.GENERAL_SETTINGS)
    Call<GeneralSettingsRequestModel> general(@Query("RequestData") String requestdata, @Header("Authorization") String auth);
}