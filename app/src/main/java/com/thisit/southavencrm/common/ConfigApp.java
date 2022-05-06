package com.thisit.southavencrm.common;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.util.Log;

public class ConfigApp extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    static SharedPreferences sharedPreferences;
    static SharedPreferences.Editor editor;

    static String USER_NAME = "userName";
    static String PASSWORD = "password";
    static String LOGIN_ID = "Login_ID";

    static String CONTACTCODE = "ContactCode";
    static String CONTACTNAME = "ContactName";
    static String COMPANY_CODE = "companyCode";
    static String CONTACT_ID = "ContactID";
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        sharedPreferences = getSharedPreferences("", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

    }

    public static boolean isNetworkAvailable(Context context) {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            @SuppressLint("MissingPermission") NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public static Context getApplicationInstance() {
        return context;
    }

    public static String getUserName() {
        return sharedPreferences.getString(USER_NAME, "");
    }

    public static void setUserName(String userName) {
        editor.putString(USER_NAME, userName);
        editor.commit();
    }

    public static String getPassword() {
        return sharedPreferences.getString(PASSWORD, "");
    }

    public static void setPassword(String Password) {
        editor.putString(PASSWORD, Password);
        editor.commit();
    }

    public static String getLogin_ID() {
        return sharedPreferences.getString(LOGIN_ID, "");
    }

    public static void setLogin_ID(String LOGIN_ID) {
        editor.putString(LOGIN_ID, LOGIN_ID);
        editor.commit();
    }



    public static String getContactCode() {
        return sharedPreferences.getString(CONTACTCODE, "");
    }

    public static void setContactCode(String ContactCode) {
        editor.putString(CONTACTCODE, ContactCode);
        editor.commit();
    }

    public static String getContactName() {
        return sharedPreferences.getString(CONTACTNAME, "");
    }

    public static void setContactName(String ContactName) {
        editor.putString(CONTACTNAME, ContactName);
        editor.commit();
    }

    public static String getCompanyCode() {
        return sharedPreferences.getString(COMPANY_CODE, "");
    }

    public static void setCompanyCode(String companyCode) {
        editor.putString(COMPANY_CODE, companyCode);
        editor.commit();
    }


    public static String getContactID() {
        return sharedPreferences.getString(CONTACT_ID, "");
    }

    public static void setContactID(String ContactID) {
        editor.putString(CONTACT_ID, ContactID);
        editor.commit();
    }


}
