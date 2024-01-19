package com.thisit.softwaregroup.webClient;


import com.thisit.softwaregroup.common.Constants;
import com.thisit.softwaregroup.common.ToastMessage;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;
    private static Retrofit stripeRetrofit = null;
    public static Retrofit getClient(String url) {
        if (retrofit == null || !retrofit.baseUrl().toString().equals(url)) {
            try {
                retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            } catch (IllegalArgumentException e) {
                ToastMessage.toast("URL format is wrong");
                return null;
            } catch (Exception e) {
                ToastMessage.toast("URL is wrong");
                return null;
            }

        }
        return retrofit;
    }
    public static Retrofit getStripeClient() {
        if (stripeRetrofit == null) {
            try {
                stripeRetrofit = new Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            } catch (IllegalArgumentException e) {
                ToastMessage.toast("URL format is wrong");
                return null;
            } catch (Exception e) {
                ToastMessage.toast("URL is wrong");
                return null;
            }

        }
        return stripeRetrofit;
    }

}
