package com.thisit.southavencrm.OrderDetail.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.thisit.southavencrm.OrderList.model.OrderListResponseModel;

import java.util.ArrayList;

public class OrderDetailResponseModel {
    private String $id;
    private boolean Status;
    private String msg;
    private String CompanyCode;
    private String TranNo;
    private String TranDate;
    private String ContactCode;
    private String ContactName;
    private String Total;
    private String Tax;
    private String ModifyDate;
    private String Remarks;
    private String SubTotal;
    private String NetTotal;
    private String Address1;
    private String Address2;
    private String Address3;
    private String BalanceAmount;
    private String BillDiscount;
    private String BillDiscountPercenatge;
    private String ContactID;
    private String ContactPerson;
    private String Country;
    private String CurrencyCode;
    private String CurrencyRate;
    private String DeliveryDate;
    private String Postalcode;
    private String DeliveryName;
    private String DeliveryAddress1;
    private String DeliveryAddress2;
    private String DeliveryAddress3;
    private String DeliveryCountry;
    private String DeliveryPostalcode;
    private String DeliveryCharges;
    private String CouponCode;
    private String CouponAmount;
    private String ShippingSlNo;
    private String TaxCode;
    @SerializedName("data")
    @Expose
    private ArrayList<OrderDetailResponseModel> data;

    private String CartonPrice;
    private String CQty;
    private String CreateDate;
    private String CreateUser;
    private String DOCqty;
    private String DOFOcQty;
    private String DOQty;
    private String ExchangeQty;
    private String FBillDiscount;
    private String FCartonPrice;
    private String FItemDiscount;
    private String FKiloPrice;
    private String FNetTotal;
    private String FocQty;
    private String FRoundOff;
    private String FSubTotal;
    private String FTax;
    private String FTotal;
    private String FTotalDiscount;
    private String Price;
    private String InvoiceCQty;
    private String InvoiceFOCQty;
    private String InvoiceQty;
    private String IsPacked;
    private String IsScanned;
    private String ItemDiscount;
    private String ItemDiscountPercentage;
    private String ItemRemarks1;
    private String ItemRemarks2;
    private String KiloPrice;
    private String LQty;
    private String ModifyUser;
    private String OriginalCartonPrice;
    private String OriginalRetailPrice;
    private String OriginalTotal;
    private String OriginalWholesalePrice;
    private String PcsPerCarton;
    private String ProductCode;
    private String ProductName;
    private String Qty;
    private String RoundOff;
    private String SlNo;
    private String TaxPercentage;
    private String TaxType;
    private String TotalDiscount;
    private String UOMCode;
    private String UOMQty;
    private boolean IsVariable;
    private String ProductWeight;
    private String WeightQty;
    private String FPrice;

    @SerializedName("Detail")
    @Expose
    private ArrayList<OrderDetailResponseModel> Detail;

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

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getTax() {
        return Tax;
    }

    public void setTax(String tax) {
        Tax = tax;
    }

    public String getModifyDate() {
        return ModifyDate;
    }

    public void setModifyDate(String modifyDate) {
        ModifyDate = modifyDate;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getSubTotal() {
        return SubTotal;
    }

    public void setSubTotal(String subTotal) {
        SubTotal = subTotal;
    }

    public String getNetTotal() {
        return NetTotal;
    }

    public void setNetTotal(String netTotal) {
        NetTotal = netTotal;
    }

    public String getAddress1() {
        return Address1;
    }

    public void setAddress1(String address1) {
        Address1 = address1;
    }

    public String getAddress2() {
        return Address2;
    }

    public void setAddress2(String address2) {
        Address2 = address2;
    }

    public String getAddress3() {
        return Address3;
    }

    public void setAddress3(String address3) {
        Address3 = address3;
    }

    public String getBalanceAmount() {
        return BalanceAmount;
    }

    public void setBalanceAmount(String balanceAmount) {
        BalanceAmount = balanceAmount;
    }

    public String getBillDiscount() {
        return BillDiscount;
    }

    public void setBillDiscount(String billDiscount) {
        BillDiscount = billDiscount;
    }

    public String getBillDiscountPercenatge() {
        return BillDiscountPercenatge;
    }

    public void setBillDiscountPercenatge(String billDiscountPercenatge) {
        BillDiscountPercenatge = billDiscountPercenatge;
    }

    public String getContactID() {
        return ContactID;
    }

    public void setContactID(String contactID) {
        ContactID = contactID;
    }

    public String getContactPerson() {
        return ContactPerson;
    }

    public void setContactPerson(String contactPerson) {
        ContactPerson = contactPerson;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }

    public String getCurrencyRate() {
        return CurrencyRate;
    }

    public void setCurrencyRate(String currencyRate) {
        CurrencyRate = currencyRate;
    }

    public String getDeliveryDate() {
        return DeliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        DeliveryDate = deliveryDate;
    }

    public String getPostalcode() {
        return Postalcode;
    }

    public void setPostalcode(String postalcode) {
        Postalcode = postalcode;
    }

    public String getDeliveryName() {
        return DeliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        DeliveryName = deliveryName;
    }

    public String getDeliveryAddress1() {
        return DeliveryAddress1;
    }

    public void setDeliveryAddress1(String deliveryAddress1) {
        DeliveryAddress1 = deliveryAddress1;
    }

    public String getDeliveryAddress2() {
        return DeliveryAddress2;
    }

    public void setDeliveryAddress2(String deliveryAddress2) {
        DeliveryAddress2 = deliveryAddress2;
    }

    public String getDeliveryAddress3() {
        return DeliveryAddress3;
    }

    public void setDeliveryAddress3(String deliveryAddress3) {
        DeliveryAddress3 = deliveryAddress3;
    }

    public String getDeliveryCountry() {
        return DeliveryCountry;
    }

    public void setDeliveryCountry(String deliveryCountry) {
        DeliveryCountry = deliveryCountry;
    }

    public String getDeliveryPostalcode() {
        return DeliveryPostalcode;
    }

    public void setDeliveryPostalcode(String deliveryPostalcode) {
        DeliveryPostalcode = deliveryPostalcode;
    }

    public String getDeliveryCharges() {
        return DeliveryCharges;
    }

    public void setDeliveryCharges(String deliveryCharges) {
        DeliveryCharges = deliveryCharges;
    }

    public String getCouponCode() {
        return CouponCode;
    }

    public void setCouponCode(String couponCode) {
        CouponCode = couponCode;
    }

    public String getCouponAmount() {
        return CouponAmount;
    }

    public void setCouponAmount(String couponAmount) {
        CouponAmount = couponAmount;
    }

    public String getShippingSlNo() {
        return ShippingSlNo;
    }

    public void setShippingSlNo(String shippingSlNo) {
        ShippingSlNo = shippingSlNo;
    }

    public String getTaxCode() {
        return TaxCode;
    }

    public void setTaxCode(String taxCode) {
        TaxCode = taxCode;
    }

    public ArrayList<OrderDetailResponseModel> getData() {
        return data;
    }

    public void setData(ArrayList<OrderDetailResponseModel> data) {
        this.data = data;
    }

    public String getCartonPrice() {
        return CartonPrice;
    }

    public void setCartonPrice(String cartonPrice) {
        CartonPrice = cartonPrice;
    }

    public String getCQty() {
        return CQty;
    }

    public void setCQty(String CQty) {
        this.CQty = CQty;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public String getCreateUser() {
        return CreateUser;
    }

    public void setCreateUser(String createUser) {
        CreateUser = createUser;
    }

    public String getDOCqty() {
        return DOCqty;
    }

    public void setDOCqty(String DOCqty) {
        this.DOCqty = DOCqty;
    }

    public String getDOFOcQty() {
        return DOFOcQty;
    }

    public void setDOFOcQty(String DOFOcQty) {
        this.DOFOcQty = DOFOcQty;
    }

    public String getDOQty() {
        return DOQty;
    }

    public void setDOQty(String DOQty) {
        this.DOQty = DOQty;
    }

    public String getExchangeQty() {
        return ExchangeQty;
    }

    public void setExchangeQty(String exchangeQty) {
        ExchangeQty = exchangeQty;
    }

    public String getFBillDiscount() {
        return FBillDiscount;
    }

    public void setFBillDiscount(String FBillDiscount) {
        this.FBillDiscount = FBillDiscount;
    }

    public String getFCartonPrice() {
        return FCartonPrice;
    }

    public void setFCartonPrice(String FCartonPrice) {
        this.FCartonPrice = FCartonPrice;
    }

    public String getFItemDiscount() {
        return FItemDiscount;
    }

    public void setFItemDiscount(String FItemDiscount) {
        this.FItemDiscount = FItemDiscount;
    }

    public String getFKiloPrice() {
        return FKiloPrice;
    }

    public void setFKiloPrice(String FKiloPrice) {
        this.FKiloPrice = FKiloPrice;
    }

    public String getFNetTotal() {
        return FNetTotal;
    }

    public void setFNetTotal(String FNetTotal) {
        this.FNetTotal = FNetTotal;
    }

    public String getFocQty() {
        return FocQty;
    }

    public void setFocQty(String focQty) {
        FocQty = focQty;
    }

    public String getFRoundOff() {
        return FRoundOff;
    }

    public void setFRoundOff(String FRoundOff) {
        this.FRoundOff = FRoundOff;
    }

    public String getFSubTotal() {
        return FSubTotal;
    }

    public void setFSubTotal(String FSubTotal) {
        this.FSubTotal = FSubTotal;
    }

    public String getFTax() {
        return FTax;
    }

    public void setFTax(String FTax) {
        this.FTax = FTax;
    }

    public String getFTotal() {
        return FTotal;
    }

    public void setFTotal(String FTotal) {
        this.FTotal = FTotal;
    }

    public String getFTotalDiscount() {
        return FTotalDiscount;
    }

    public void setFTotalDiscount(String FTotalDiscount) {
        this.FTotalDiscount = FTotalDiscount;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getInvoiceCQty() {
        return InvoiceCQty;
    }

    public void setInvoiceCQty(String invoiceCQty) {
        InvoiceCQty = invoiceCQty;
    }

    public String getInvoiceFOCQty() {
        return InvoiceFOCQty;
    }

    public void setInvoiceFOCQty(String invoiceFOCQty) {
        InvoiceFOCQty = invoiceFOCQty;
    }

    public String getInvoiceQty() {
        return InvoiceQty;
    }

    public void setInvoiceQty(String invoiceQty) {
        InvoiceQty = invoiceQty;
    }

    public String getIsPacked() {
        return IsPacked;
    }

    public void setIsPacked(String isPacked) {
        IsPacked = isPacked;
    }

    public String getIsScanned() {
        return IsScanned;
    }

    public void setIsScanned(String isScanned) {
        IsScanned = isScanned;
    }

    public String getItemDiscount() {
        return ItemDiscount;
    }

    public void setItemDiscount(String itemDiscount) {
        ItemDiscount = itemDiscount;
    }

    public String getItemDiscountPercentage() {
        return ItemDiscountPercentage;
    }

    public void setItemDiscountPercentage(String itemDiscountPercentage) {
        ItemDiscountPercentage = itemDiscountPercentage;
    }

    public String getItemRemarks1() {
        return ItemRemarks1;
    }

    public void setItemRemarks1(String itemRemarks1) {
        ItemRemarks1 = itemRemarks1;
    }

    public String getItemRemarks2() {
        return ItemRemarks2;
    }

    public void setItemRemarks2(String itemRemarks2) {
        ItemRemarks2 = itemRemarks2;
    }

    public String getKiloPrice() {
        return KiloPrice;
    }

    public void setKiloPrice(String kiloPrice) {
        KiloPrice = kiloPrice;
    }

    public String getLQty() {
        return LQty;
    }

    public void setLQty(String LQty) {
        this.LQty = LQty;
    }

    public String getModifyUser() {
        return ModifyUser;
    }

    public void setModifyUser(String modifyUser) {
        ModifyUser = modifyUser;
    }

    public String getOriginalCartonPrice() {
        return OriginalCartonPrice;
    }

    public void setOriginalCartonPrice(String originalCartonPrice) {
        OriginalCartonPrice = originalCartonPrice;
    }

    public String getOriginalRetailPrice() {
        return OriginalRetailPrice;
    }

    public void setOriginalRetailPrice(String originalRetailPrice) {
        OriginalRetailPrice = originalRetailPrice;
    }

    public String getOriginalTotal() {
        return OriginalTotal;
    }

    public void setOriginalTotal(String originalTotal) {
        OriginalTotal = originalTotal;
    }

    public String getOriginalWholesalePrice() {
        return OriginalWholesalePrice;
    }

    public void setOriginalWholesalePrice(String originalWholesalePrice) {
        OriginalWholesalePrice = originalWholesalePrice;
    }

    public String getPcsPerCarton() {
        return PcsPerCarton;
    }

    public void setPcsPerCarton(String pcsPerCarton) {
        PcsPerCarton = pcsPerCarton;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getRoundOff() {
        return RoundOff;
    }

    public void setRoundOff(String roundOff) {
        RoundOff = roundOff;
    }

    public String getSlNo() {
        return SlNo;
    }

    public void setSlNo(String slNo) {
        SlNo = slNo;
    }

    public String getTaxPercentage() {
        return TaxPercentage;
    }

    public void setTaxPercentage(String taxPercentage) {
        TaxPercentage = taxPercentage;
    }

    public String getTaxType() {
        return TaxType;
    }

    public void setTaxType(String taxType) {
        TaxType = taxType;
    }

    public String getTotalDiscount() {
        return TotalDiscount;
    }

    public void setTotalDiscount(String totalDiscount) {
        TotalDiscount = totalDiscount;
    }

    public String getUOMCode() {
        return UOMCode;
    }

    public void setUOMCode(String UOMCode) {
        this.UOMCode = UOMCode;
    }

    public String getUOMQty() {
        return UOMQty;
    }

    public void setUOMQty(String UOMQty) {
        this.UOMQty = UOMQty;
    }

    public boolean isVariable() {
        return IsVariable;
    }

    public void setVariable(boolean variable) {
        IsVariable = variable;
    }

    public String getProductWeight() {
        return ProductWeight;
    }

    public void setProductWeight(String productWeight) {
        ProductWeight = productWeight;
    }

    public String getWeightQty() {
        return WeightQty;
    }

    public void setWeightQty(String weightQty) {
        WeightQty = weightQty;
    }

    public String getFPrice() {
        return FPrice;
    }

    public void setFPrice(String FPrice) {
        this.FPrice = FPrice;
    }

    public ArrayList<OrderDetailResponseModel> getDetail() {
        return Detail;
    }

    public void setDetail(ArrayList<OrderDetailResponseModel> detail) {
        Detail = detail;
    }
}
