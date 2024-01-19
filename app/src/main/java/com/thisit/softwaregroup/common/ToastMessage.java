package com.thisit.softwaregroup.common;

import android.widget.Toast;

public class ToastMessage {
    public static void toast(String s){
        Toast.makeText(ConfigApp.getApplicationInstance(), s, Toast.LENGTH_SHORT).show();
    }
}
