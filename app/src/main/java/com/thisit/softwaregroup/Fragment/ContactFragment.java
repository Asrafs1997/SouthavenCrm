package com.thisit.softwaregroup.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thisit.softwaregroup.R;
import com.thisit.softwaregroup.common.BaseFragment;
import com.thisit.softwaregroup.dashboard.view.ECardActivity;

public class ContactFragment extends BaseFragment {

    public ContactFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ECardActivity) getActivity()).title_tv.setText(R.string.contactus); // here are other names according to each fragment
    }
}

