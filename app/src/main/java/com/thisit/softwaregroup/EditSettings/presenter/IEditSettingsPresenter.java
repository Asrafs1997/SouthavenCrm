package com.thisit.softwaregroup.EditSettings.presenter;

public interface IEditSettingsPresenter {
    void apiCall(String DeviceID);
    void SaveDeviceapiCall(String DeviceID,String DeviceName,boolean HasPromoNotification ,boolean HasOrderNotification);
}
