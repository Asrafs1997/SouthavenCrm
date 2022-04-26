package com.thisit.southavencrm.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.thisit.southavencrm.ECardActivity;
import com.thisit.southavencrm.R;

public class ChangePasswordFragment extends Fragment {
    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_changepassword, container, false);
        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ECardActivity) getActivity()).title_tv.setText(R.string.change_password); // here are other names according to each fragment
    }
}

