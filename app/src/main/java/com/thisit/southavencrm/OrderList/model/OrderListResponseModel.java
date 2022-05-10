package com.thisit.southavencrm.OrderList.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OrderListResponseModel {
    private String $id;

    private boolean Status;

    private String msg;

    private String CompanyCode;

    private String TranNo;

    private String TranDate;

    private String DeliveryDate;

    private String DeliveryName;

    private String ContactID;

    private String ContactCode;

    private String ContactName;

    private String TotalDiscount;

    private String SubTotal;

    private String Tax;

    private String NetTotal;

    private String FinalTotal;

    private String BalanceAmount;

    private String LocationCode;

    private String LocationName;

    @SerializedName("data")
    @Expose
    private ArrayList<OrderListResponseModel> data;

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

    public String getTranNo() {
        return TranNo;
    }

    public void setTranNo(String tranNo) {
        TranNo = tranNo;
    }

    public String getTranDate() {
        return TranDate;
    }

    public void setTranDate(String tranDate) {
        TranDate = tranDate;
    }

    public String getDeliveryDate() {
        return DeliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        DeliveryDate = deliveryDate;
    }

    public String getDeliveryName() {
        return DeliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        DeliveryName = deliveryName;
    }

    public String getContactID() {
        return ContactID;
    }

    public void setContactID(String contactID) {
        ContactID = contactID;
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

    public String getTotalDiscount() {
        return TotalDiscount;
    }

    public void setTotalDiscount(String totalDiscount) {
        TotalDiscount = totalDiscount;
    }

    public String getSubTotal() {
        return SubTotal;
    }

    public void setSubTotal(String subTotal) {
        SubTotal = subTotal;
    }

    public String getTax() {
        return Tax;
    }

    public void setTax(String tax) {
        Tax = tax;
    }

    public String getNetTotal() {
        return NetTotal;
    }

    public void setNetTotal(String netTotal) {
        NetTotal = netTotal;
    }

    public String getFinalTotal() {
        return FinalTotal;
    }

    public void setFinalTotal(String finalTotal) {
        FinalTotal = finalTotal;
    }

    public String getBalanceAmount() {
        return BalanceAmount;
    }

    public void setBalanceAmount(String balanceAmount) {
        BalanceAmount = balanceAmount;
    }

    public String getLocationCode() {
        return LocationCode;
    }

    public void setLocationCode(String locationCode) {
        LocationCode = locationCode;
    }

    public String getLocationName() {
        return LocationName;
    }

    public void setLocationName(String locationName) {
        LocationName = locationName;
    }

    public ArrayList<OrderListResponseModel> getData() {
        return data;
    }

    public void setData(ArrayList<OrderListResponseModel> data) {
        this.data = data;
    }

}




