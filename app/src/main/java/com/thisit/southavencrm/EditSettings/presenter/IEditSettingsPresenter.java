package com.thisit.southavencrm.EditSettings.presenter;

public interface IEditSettingsPresenter {
    void apiCall(String DeviceID);
    void SaveDeviceapiCall(String DeviceID,String DeviceName,boolean HasPromoNotification ,boolean HasOrderNotification);
}
