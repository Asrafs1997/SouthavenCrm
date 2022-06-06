package com.thisit.southavencrm.EditSettings.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DeviceSettingResponseModel {

    private String $id;

    private boolean Status;

    private String msg;

    @SerializedName("data")
    @Expose
    private ArrayList<DeviceSettingResponseModel> data;


    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<DeviceSettingResponseModel> getData() {
        return data;
    }

    public void setData(ArrayList<DeviceSettingResponseModel> data) {
        this.data = data;
    }
}
