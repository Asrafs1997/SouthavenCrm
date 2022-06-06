package com.thisit.southavencrm.webClientHandler;

import com.thisit.southavencrm.EditSettings.model.EditSettingsResponseModel;
import com.thisit.southavencrm.GeneralSettings.model.GeneralSettingsRequestModel;
import com.thisit.southavencrm.common.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface EditSettingsApi {
    @GET(Constants.Edit_SETTINGS)
    Call<EditSettingsResponseModel> general(@Query("RequestData") String requestdata, @Header("Authorization") String auth);
}

