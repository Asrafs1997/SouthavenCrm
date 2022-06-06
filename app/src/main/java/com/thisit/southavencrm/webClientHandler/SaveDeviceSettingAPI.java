package com.thisit.southavencrm.webClientHandler;

import com.thisit.southavencrm.EditSettings.model.DeviceSettingResponseModel;
import com.thisit.southavencrm.common.Constants;
import com.thisit.southavencrm.editprofile.model.EditProfileResponseModel;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SaveDeviceSettingAPI {
    @POST(Constants.Device_SETTINGS)
    Call<DeviceSettingResponseModel>SaveDeviceAPI(@Body RequestBody rawString, @Header("Authorization") String auth);


}

