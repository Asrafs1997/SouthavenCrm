package com.thisit.southavencrm.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LoginResponseModel {

    private String $id;

    private boolean Status;

    private String msg;

    private String CompanyCode;

    private String ContactCode;

    private String ContactName;

    private String ContactID;

    private String Email;

    private String ResetPassword;

    @SerializedName("data")
    @Expose
    private ArrayList<LoginResponseModel> data;


    public String getCompanyCode() { return CompanyCode; }

    public void setCompanyCode(String companyCode) {
        CompanyCode = companyCode;
    }

    public String getContactCode() {
        return ContactCode;
    }

    public void setContactCode(String contactCode) {
        ContactCode = contactCode;
    }

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String contactName) {
        ContactName = contactName;
    }

    public String getContactID() {
        return ContactID;
    }

    public void setContactID(String contactID) {
        ContactID = contactID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getResetPassword() {
        return ResetPassword;
    }

    public void setResetPassword(String resetPassword) {
        ResetPassword = resetPassword;
    }

    public String get$id() {
        return $id;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<LoginResponseModel> getData() {
        return data;
    }

    public void setData(ArrayList<LoginResponseModel> data) {
        this.data = data;
    }
}