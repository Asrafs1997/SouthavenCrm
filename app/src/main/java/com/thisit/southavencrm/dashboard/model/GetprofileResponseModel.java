package com.thisit.southavencrm.dashboard.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.thisit.southavencrm.login.model.LoginResponseModel;

import java.util.ArrayList;

public class GetprofileResponseModel {
    private String $id;

    private boolean Status;

    private String msg;

    private String CompanyCode;

    private String Title;

    private String ContactName;

    private String LastName;

    private String HandphoneNo;

    private String Email;

    private String Postalcode;

    private String Address;

    private String DOB;

    @SerializedName("data")
    @Expose
    private ArrayList<GetprofileResponseModel> data;

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

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }


    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public ArrayList<GetprofileResponseModel> getData() {
        return data;
    }

    public void setData(ArrayList<GetprofileResponseModel> data) {
        this.data = data;
    }
}




