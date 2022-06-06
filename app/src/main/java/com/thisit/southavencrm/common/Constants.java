package com.thisit.southavencrm.common;

public interface Constants {
     String BASE_URL = "http://61.8.213.204/southavenapi/";

     String LOGIN = "api/B2B_Service/GetValidateLoginCustomer?";
     String EDIT_PROFILE = "api/B2B_Service/SaveCustomer_Southaven";
     String REGISTRATION = "api/B2B_Service/SaveCustomer_Southaven";
     String CHANGEPASSWORED = "api/B2B_Service/ChangePassword_Southaven";
     String GENERAL_SETTINGS = "api/B2B_Service/GetGeneralSetting_Southaven";
     String Device_SETTINGS = "api/B2B_Service/SaveDeviceSetting_Southaven";
     String Edit_SETTINGS = "api/B2B_Service/GetDeviceSetting_Southaven";
     String Contact_Us = "api/B2B_Service/SendContactUs_Southaven";
     String RESETPASSWORD = "api/B2B_Service/ResetPasswordEmailSend";
     String LOCATION_LIST = "api/B2B_Service/GetLocateUs_Southaven";
     String FAQ_LIST = "api/B2B_Service/GetFAQ_Southaven";
     String ORDER_LIST = "api/B2B_Service/GetOrderList_Southaven";
     String GETPROFILE = "api/B2B_Service/GetCustomerProfile_Southaven";
}
//{{base_url}}/api/B2B_Service/GetOrderList_Southaven?Requestdata={"CompanyCode":1,"ContactID":"7930","FromDate":"20/04/2022","ToDate":"19/05/2022"}