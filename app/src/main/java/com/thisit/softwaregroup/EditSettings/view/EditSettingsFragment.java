package com.thisit.softwaregroup.EditSettings.view;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.thisit.softwaregroup.EditSettings.presenter.EditSettingsPresenter;
import com.thisit.softwaregroup.EditSettings.presenter.IEditSettingsPresenter;
import com.thisit.softwaregroup.R;
import com.thisit.softwaregroup.common.BaseFragment;
import com.thisit.softwaregroup.common.ConfigApp;
import com.thisit.softwaregroup.dashboard.view.ECardActivity;

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
        ((ECardActivity) getActivity()).ishome=false;
        ((ECardActivity) getActivity()).isabout=false;
        ((ECardActivity) getActivity()).isprofile=true;
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
        System.out.println("HasPromoNotification"+HasPromoNotification);
        HasPromo = HasPromoNotification;
        System.out.println("HasPromo"+HasPromo);
    }

    @Override
    public void HasOrderNotification(boolean HasOrderNotification) {
        HasOrderNotification_switch.setChecked(HasOrderNotification);
        System.out.println("HasOrderNotification"+HasOrderNotification);
        HasOrder = HasOrderNotification;
        System.out.println("HasOrder"+HasOrder);
    }
}
