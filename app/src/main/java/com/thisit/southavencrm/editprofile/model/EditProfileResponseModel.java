package com.thisit.southavencrm.editprofile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EditProfileResponseModel {
    private String $id;

    private String msg;

    private boolean Status;

    private String CompanyCode = "";

    private String ContactID = "";

    private String Salutation = "";

    private String ContactName = "";

    private String LastName = "";

    private String HandphoneNo = "";

    private String Email = "";

    private String Postalcode = "";

    private String Address1 = "";

    private String DOB = "";

    private String Password = "";

    public String getCompanyCode() {
        return CompanyCode;
    }


    public String get$id() {
        return $id;
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

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public void setCompanyCode(String companyCode) {
        CompanyCode = companyCode;
    }

    public String getContactID() {
        return ContactID;
    }

    public void setContactID(String contactID) {
        ContactID = contactID;
    }

    public String getSalutation() {
        return Salutation;
    }

    public void setSalutation(String salutation) {
        Salutation = salutation;
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

    public String getAddress1() {
        return Address1;
    }

    public void setAddress1(String address1) {
        Address1 = address1;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
