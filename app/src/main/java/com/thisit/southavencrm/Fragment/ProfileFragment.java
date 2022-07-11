package com.thisit.southavencrm.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.thisit.southavencrm.EditSettings.view.EditSettingsFragment;
import com.thisit.southavencrm.ProfileSliderAdapter;
import com.thisit.southavencrm.ProfileSliderBean;
import com.thisit.southavencrm.R;
import com.thisit.southavencrm.common.BaseFragment;
import com.thisit.southavencrm.common.ConfigApp;
import com.thisit.southavencrm.customfonts.TextviewSourceSansProBold;
import com.thisit.southavencrm.dashboard.view.ECardActivity;
import com.thisit.southavencrm.editprofile.view.EditProfileFragment;
import com.thisit.southavencrm.changePassword.view.ChangePasswordFragment;
import com.thisit.southavencrm.login.view.LoginActivity;

import java.util.ArrayList;

public class ProfileFragment extends BaseFragment {
    private View root;
    private Activity activity;
    private LinearLayout editprofile_llv, changepassword_llv, edit_settings_llv, logout_llv;
    private TextviewSourceSansProBold txtUsername;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_profile, container, false);
        activity = getActivity();
        ArrayList<ProfileSliderBean> sliderDataArrayList = new ArrayList<>();

        SliderView sliderView = root.findViewById(R.id.slider);
        editprofile_llv = root.findViewById(R.id.editprofile_llv);
        changepassword_llv =  root.findViewById(R.id.changepassword_llv);
        edit_settings_llv =  root.findViewById(R.id.edit_settings_llv);
        logout_llv =  root.findViewById(R.id.logout_llv);
        txtUsername =  root.findViewById(R.id.username);
        sliderDataArrayList.add(new ProfileSliderBean(R.drawable.background2));
        sliderDataArrayList.add(new ProfileSliderBean(R.drawable.shop));
        sliderDataArrayList.add(new ProfileSliderBean(R.drawable.background1));

        ProfileSliderAdapter adapter = new ProfileSliderAdapter(activity, sliderDataArrayList);
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        sliderView.setSliderAdapter(adapter);
        sliderView.setScrollTimeInSec(5);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
        sliderView.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);

        editprofile_llv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.framecontainer, new EditProfileFragment()).commit();
            }
        });
        changepassword_llv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.framecontainer, new ChangePasswordFragment()).commit();
            }
        });
        edit_settings_llv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.framecontainer, new EditSettingsFragment()).commit();
            }
        });

        logout_llv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Confirm")
                        .setMessage("Do you want to logout?")
                        .setCancelable(false)
                        .setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ConfigApp.setContactID("");
                                ConfigApp.setCompanyCode("");
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                getActivity().startActivity(intent);
                                getActivity().finish();
                            }
                        })
                        .setNegativeButton("CANCEL", null)
                        .show();
            }
        });

        return root;
    }


    @Override
    public void onResume() {

        txtUsername.setText(ConfigApp.getTitle()+"\t "+ConfigApp.getContactName());
        super.onResume();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ECardActivity) getActivity()).title_tv.setText(R.string.profile_acco);
        ((ECardActivity) getActivity()).ishome=true;
        ((ECardActivity) getActivity()).isabout=false;
        ((ECardActivity) getActivity()).isprofile=false;
    }
}


