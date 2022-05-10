package com.thisit.southavencrm.FAQList.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FAQListResponseModel {
    private String $id;

    private boolean Status;

    private String msg;


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

    @SerializedName("data")
    @Expose
    private ArrayList<FAQListResponseModel> data;

    public ArrayList<FAQListResponseModel> getData() {
        return data;
    }

    public void setData(ArrayList<FAQListResponseModel> data) {
        this.data = data;
    }

}




