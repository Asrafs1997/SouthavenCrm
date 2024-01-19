package com.thisit.softwaregroup.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.thisit.softwaregroup.R;

import com.thisit.softwaregroup.common.BaseFragment;
import com.thisit.softwaregroup.contactUs.view.ContactFragment;

import com.thisit.softwaregroup.dashboard.view.ECardActivity;


public class AboutFragment extends BaseFragment {
    private View root;
    private LinearLayout contact_llv, privacy_llv,membership_llv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_about, container, false);
        contact_llv = root.findViewById(R.id.contact_llv);
        privacy_llv = root.findViewById(R.id.privacy_llv);
        membership_llv = root.findViewById(R.id.membership_llv);
        contact_llv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.framecontainer, new ContactFragment()).commit();
            }
        });

        membership_llv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        privacy_llv.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.softwaregroup.com/about-us");

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        return root;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ECardActivity) getActivity()).title_tv.setText(R.string.about_us);
        ((ECardActivity) getActivity()).ishome=true;
        ((ECardActivity) getActivity()).isabout=false;
        ((ECardActivity) getActivity()).isprofile=false;
    }
}

