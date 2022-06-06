package com.thisit.southavencrm.EditSettings.view;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.thisit.southavencrm.EditSettings.presenter.EditSettingsPresenter;
import com.thisit.southavencrm.EditSettings.presenter.IEditSettingsPresenter;
import com.thisit.southavencrm.R;
import com.thisit.southavencrm.changePassword.presenter.ChangePasswordPresenter;
import com.thisit.southavencrm.changePassword.presenter.IChangePasswordPresenter;
import com.thisit.southavencrm.changePassword.view.IChangePasswordFragment;
import com.thisit.southavencrm.common.BaseFragment;
import com.thisit.southavencrm.common.ConfigApp;
import com.thisit.southavencrm.dashboard.view.ECardActivity;

public class EditSettingsFragment extends BaseFragment implements IEditSettingsView {

    private View root;
    private Activity activity;
    private IEditSettingsPresenter iEditSettingsPresenter;
    private Switch HasPromoNotification_switch, HasOrderNotification_switch;
    private boolean HasPromo, HasOrder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_settings, container, false);
        activity = getActivity();
        iEditSettingsPresenter = (IEditSettingsPresenter) new EditSettingsPresenter(this);
        HasPromoNotification_switch = (Switch) root.findViewById(R.id.HasPromoNotification_switch);
        HasOrderNotification_switch = (Switch) root.findViewById(R.id.HasOrderNotification_switch);

        if (ConfigApp.isNetworkAvailable(activity)) {
            iEditSettingsPresenter.apiCall(ConfigApp.getAndroidID(activity));
        } else {
            Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

        String deviceName = Build.MANUFACTURER + " " + Build.DEVICE;


        HasPromoNotification_switch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (HasPromoNotification_switch.isChecked()) {
                    HasPromo = true;
                    iEditSettingsPresenter.SaveDeviceapiCall(deviceName, ConfigApp.getAndroidID(activity), HasPromo, HasOrder);
                    Toast.makeText(activity, "Switch is in ON State", Toast.LENGTH_LONG).show();
                } else {
                    HasPromo = false;
                    iEditSettingsPresenter.SaveDeviceapiCall(deviceName, ConfigApp.getAndroidID(activity), HasPromo, HasOrder);
                    Toast.makeText(activity, "Switch is in OFF State", Toast.LENGTH_LONG).show();
                }
            }
        });
        HasOrderNotification_switch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (HasOrderNotification_switch.isChecked()) {
                    HasOrder = true;
                    iEditSettingsPresenter.SaveDeviceapiCall(deviceName, ConfigApp.getAndroidID(activity), HasPromo, HasOrder);
                    Toast.makeText(activity, "Switch is in ON State", Toast.LENGTH_LONG).show();
                } else {
                    HasOrder = false;
                    iEditSettingsPresenter.SaveDeviceapiCall(deviceName, ConfigApp.getAndroidID(activity), HasPromo, HasOrder);
                    Toast.makeText(activity, "Switch is in OFF State", Toast.LENGTH_LONG).show();
                }
            }
        });


        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ECardActivity) getActivity()).title_tv.setText(R.string.settings);
    }


    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure() {

    }

    @Override
    public void HasPromoNotification(boolean HasPromoNotification) {
        HasPromoNotification_switch.setChecked(HasPromoNotification);
        HasPromo = HasPromoNotification;
    }

    @Override
    public void HasOrderNotification(boolean HasOrderNotification) {
        HasOrderNotification_switch.setChecked(HasOrderNotification);
        HasOrder = HasOrderNotification;
    }
}
