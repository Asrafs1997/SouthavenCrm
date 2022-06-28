package com.thisit.southavencrm.GeneralSettings.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import com.thisit.southavencrm.GeneralSettings.presenter.GeneralSettingsPresenter;
import com.thisit.southavencrm.GeneralSettings.presenter.IGeneralSettingsPresenter;
import com.thisit.southavencrm.HomeScreenActivity;
import com.thisit.southavencrm.R;

import com.thisit.southavencrm.common.BaseActivity;
import com.thisit.southavencrm.common.ConfigApp;
import com.thisit.southavencrm.common.ToastMessage;


public class GeneralSettingsActivity extends BaseActivity implements IGeneralSettingsView{
    private IGeneralSettingsPresenter iGeneralSettingsPresenter;
    ImageView image;
    private static int SPLASH_SCREEN_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        image = findViewById(R.id.image);
        if (ConfigApp.getCompanyCode().isEmpty()) {
          ConfigApp.setCompanyCode("1");
        }
        iGeneralSettingsPresenter = new GeneralSettingsPresenter(this);
        if (ConfigApp.isNetworkAvailable(this)) {
            iGeneralSettingsPresenter.apiCall(ConfigApp.getCompanyCode());
        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(GeneralSettingsActivity.this, HomeScreenActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }


    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure() {

    }
}
