package com.thisit.softwaregroup.EditSettings.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EditSettingsResponseModel {

    private String $id;

    private boolean Status;

    private boolean HasPromoNotification;

    private boolean HasOrderNotification;
    private String msg;
    @SerializedName("data")
    @Expose
    private ArrayList<EditSettingsResponseModel> data;

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

    public boolean isHasPromoNotification() {
        return HasPromoNotification;
    }

    public void setHasPromoNotification(boolean hasPromoNotification) {
        HasPromoNotification = hasPromoNotification;
    }

    public boolean isHasOrderNotification() {
        return HasOrderNotification;
    }

    public void setHasOrderNotification(boolean hasOrderNotification) {
        HasOrderNotification = hasOrderNotification;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<EditSettingsResponseModel> getData() {
        return data;
    }

    public void setData(ArrayList<EditSettingsResponseModel> data) {
        this.data = data;
    }
}
