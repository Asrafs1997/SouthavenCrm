package com.thisit.southavencrm.GeneralSettings.presenter;

import android.util.Log;

import com.thisit.southavencrm.GeneralSettings.view.GeneralSettingsActivity;
import com.thisit.southavencrm.GeneralSettings.view.IGeneralSettingsView;
import com.thisit.southavencrm.common.BasicAuth;
import com.thisit.southavencrm.common.ConfigApp;
import com.thisit.southavencrm.common.Constants;
import com.thisit.southavencrm.login.model.LoginResponseModel;
import com.thisit.southavencrm.login.view.ILoginView;
import com.thisit.southavencrm.login.view.LoginActivity;
import com.thisit.southavencrm.webClient.ApiClient;
import com.thisit.southavencrm.webClientHandler.LoginAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneralSettingsPresenter implements IGeneralSettingsPresenter {


    private IGeneralSettingsView iGeneralSettingsView;

    public GeneralSettingsPresenter(GeneralSettingsActivity iGeneralSettingsView) {
        this.iGeneralSettingsView =iGeneralSettingsView;

    }

    @Override
    public void apiCall(String CompanyCode) {
        System.out.println("Companycode/msd/" +CompanyCode.toString());

    }


}
