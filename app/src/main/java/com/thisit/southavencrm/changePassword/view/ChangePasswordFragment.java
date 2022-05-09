package com.thisit.southavencrm.changePassword.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import com.thisit.southavencrm.R;
import com.thisit.southavencrm.changePassword.presenter.ChangePasswordPresentr;
import com.thisit.southavencrm.changePassword.presenter.IChangePasswordPresentr;
import com.thisit.southavencrm.common.ConfigApp;
import com.thisit.southavencrm.dashboard.view.ECardActivity;

import java.util.ArrayList;

public class ChangePasswordFragment extends Fragment implements IChangePasswordFragment {
    private View root;
    private Activity activity;
    private IChangePasswordPresentr iChangePasswordPresentr;
    private EditText current_password, new_password, confirm_password;
    private Button Savebutton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_changepassword, container, false);
        activity = getActivity();

        iChangePasswordPresentr = new ChangePasswordPresentr(this);
        current_password = (EditText) root.findViewById(R.id.current_password);
        new_password = (EditText) root.findViewById(R.id.new_password);
        confirm_password = (EditText) root.findViewById(R.id.confirm_password);
        Savebutton = (Button) root.findViewById(R.id.Savebutton);

        Savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (current_password.getText().toString().length() == 0) {
                    current_password.setError("Enter the Current Password");
                    current_password.requestFocus();
                } else if (new_password.getText().toString().length() == 0) {
                    new_password.setError("Enter the new Password");
                    new_password.requestFocus();
                } else if (confirm_password.getText().toString().length() == 0) {
                    confirm_password.setError("Enter the confirm Password");
                    confirm_password.requestFocus();
                } else {
                    if (ConfigApp.isNetworkAvailable(activity)) {
                        iChangePasswordPresentr.apiCall(current_password.getText().toString(),
                                new_password.getText().toString(), confirm_password.getText().toString());
                    } else {
                        Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ECardActivity) getActivity()).title_tv.setText(R.string.change_password); // here are other names according to each fragment

        //((ECardActivity) getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer, new CardFragment()).commit();
    }


    @Override
    public void emptycurrentPassword() {

    }

    @Override
    public void emptynewPassword() {

    }

    @Override
    public void emptyconfirmPassword() {

    }

    @Override
    public void onSuccess(String msg) {
        new AlertDialog.Builder(getActivity())
                .setTitle("Successfully")
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void onFailure(String msg) {
        new AlertDialog.Builder(getActivity())
                .setTitle("Invalid Password")
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                })
                .show();
    }



    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

}

