package com.thisit.southavencrm.FAQList.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FAQListResponseModel {
    private String $id;
    private String Code;
    private String Date;
    private String Title;
    private String ShortDescription;
    private boolean Status;

    private String msg;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getShortDescription() {
        return ShortDescription;
    }

    public void setShortDescription(String shortDescription) {
        ShortDescription = shortDescription;
    }

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




