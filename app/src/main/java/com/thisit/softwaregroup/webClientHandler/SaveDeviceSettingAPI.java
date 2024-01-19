package com.thisit.softwaregroup.webClientHandler;

import com.thisit.softwaregroup.EditSettings.model.DeviceSettingResponseModel;
import com.thisit.softwaregroup.common.Constants;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SaveDeviceSettingAPI {
    @POST(Constants.Device_SETTINGS)
    Call<DeviceSettingResponseModel>SaveDeviceAPI(@Body RequestBody rawString, @Header("Authorization") String auth);


}

