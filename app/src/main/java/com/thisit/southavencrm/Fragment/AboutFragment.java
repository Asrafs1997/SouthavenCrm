package com.thisit.southavencrm.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


import com.thisit.southavencrm.R;

import com.thisit.southavencrm.contactUs.view.ContactFragment;

import com.thisit.southavencrm.dashboard.view.ECardActivity;


public class AboutFragment extends Fragment {
    private View root;
    private LinearLayout contact_llv, privacy_llv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_about, container, false);
        contact_llv = root.findViewById(R.id.contact_llv);
        privacy_llv = root.findViewById(R.id.privacy_llv);
        contact_llv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.framecontainer, new ContactFragment()).commit();
            }
        });
        privacy_llv.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Uri uri = Uri.parse("https://southaven.com.sg/privacy/");

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        return root;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ECardActivity) getActivity()).title_tv.setText(R.string.about); // here are other names according to each fragment
    }
}

