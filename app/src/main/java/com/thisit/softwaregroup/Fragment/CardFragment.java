package com.thisit.softwaregroup.Fragment;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thisit.softwaregroup.R;

import com.thisit.softwaregroup.common.BaseFragment;
import com.thisit.softwaregroup.dashboard.view.ECardActivity;

public class CardFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (Validation1.isTabletScreen(this)) {
            setRequestedOrientation(SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onCreate(savedInstanceState);
        setContentView();

        return inflater.inflate(R.layout.fragment_card, container, false);
    }

    private void setContentView() {
    }

    private void setRequestedOrientation(int screenOrientationLandscape) {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ECardActivity) getActivity()).title_tv.setText(R.string.my_card);
        ((ECardActivity) getActivity()).ishome = true;
        ((ECardActivity) getActivity()).isabout = false;
        ((ECardActivity) getActivity()).isprofile = false;
    }

}

