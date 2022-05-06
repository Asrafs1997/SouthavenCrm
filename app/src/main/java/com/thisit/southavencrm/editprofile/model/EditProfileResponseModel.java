package com.thisit.southavencrm.editprofile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EditProfileResponseModel {

    private String $id;

    private boolean Status;

    private String msg;

    private String CompanyCode;

    private String Title;

    private String ContactName;

    private String HandphoneNo;

    private String Email;

    private String Postalcode;


    private String Address1;

    public String getAddress1() {
        return Address1;
    }

    public void setAddress1(String address1) {
        Address1 = address1;
    }

    private String DOB;

    @SerializedName("data")
    @Expose
    private ArrayList<EditProfileResponseModel> data;

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

    public String getCompanyCode() {
        return CompanyCode;
    }

    public void setCompanyCode(String companyCode) {
        CompanyCode = companyCode;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String contactName) {
        ContactName = contactName;
    }



    public String getHandphoneNo() {
        return HandphoneNo;
    }

    public void setHandphoneNo(String handphoneNo) {
        HandphoneNo = handphoneNo;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPostalcode() {
        return Postalcode;
    }

    public void setPostalcode(String postalcode) {
        Postalcode = postalcode;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public ArrayList<EditProfileResponseModel> getData() {
        return data;
    }

    public void setData(ArrayList<EditProfileResponseModel> data) {
        this.data = data;
    }
}
