package com.thisit.softwaregroup.GeneralSettings.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.thisit.softwaregroup.EditSettings.presenter.IEditSettingsPresenter;
import com.thisit.softwaregroup.GeneralSettings.presenter.IGeneralSettingsPresenter;
import com.thisit.softwaregroup.HomeScreenActivity;
import com.thisit.softwaregroup.R;

import com.thisit.softwaregroup.common.BaseActivity;


public class GeneralSettingsActivity extends BaseActivity implements IGeneralSettingsView{
   // private IGeneralSettingsPresenter iGeneralSettingsPresenter;
    ImageView image;
    private static int SPLASH_SCREEN_TIME_OUT = 2000;

    private IEditSettingsPresenter iEditSettingsPresenter;
    private IGeneralSettingsPresenter iGeneralSettingsPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        image = findViewById(R.id.image);

      /*  iGeneralSettingsPresenter = new GeneralSettingsPresenter(this);
        if (ConfigApp.isNetworkAvailable(this)) {
            iGeneralSettingsPresenter.apiCall("1");
        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }*/
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
