package com.thisit.southavencrm.common;

import android.util.Base64;

public class BasicAuth {
    private static String auth = null;
    private static String stripeAuth = null;

    public static String getAuth() {
        if (auth == null) {
            String userNameAndPassword = "wincom" + ":" + "admin";
            auth = "Basic " + Base64.encodeToString(userNameAndPassword.getBytes(), Base64.NO_WRAP);
        }
        return auth;
    }

}
