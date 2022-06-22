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


import com.thisit.southavencrm.Fragment.AboutFragment;
import com.thisit.southavencrm.Fragment.ProfileFragment;
import com.thisit.southavencrm.R;
import com.thisit.southavencrm.changePassword.presenter.ChangePasswordPresenter;
import com.thisit.southavencrm.changePassword.presenter.IChangePasswordPresenter;
import com.thisit.southavencrm.common.BaseFragment;
import com.thisit.southavencrm.common.ConfigApp;
import com.thisit.southavencrm.dashboard.view.ECardActivity;

public class ChangePasswordFragment extends BaseFragment implements IChangePasswordFragment {
    private View root;
    private Activity activity;
    private IChangePasswordPresenter iChangePasswordPresentr;
    private EditText current_password, new_password, confirm_password;
    private Button Savebutton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_changepassword, container, false);
        activity = getActivity();

        iChangePasswordPresentr = new ChangePasswordPresenter(this);
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
        ((ECardActivity) getActivity()).ishome=false;
        ((ECardActivity) getActivity()).isabout=false;
        ((ECardActivity) getActivity()).isprofile=true;
    }


   @Override
    public void onSuccess(String msg) {
       AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
       alertDialogBuilder
               .setTitle("Password Changed")
               .setCancelable(true)
               .setMessage("Password successfully changed.");
       alertDialogBuilder.setPositiveButton("OK",
               new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface arg0, int arg1) {
                       getActivity().getSupportFragmentManager()
                               .beginTransaction()
                               .replace(R.id.framecontainer, new ProfileFragment())
                               .commit();
                   }
               });

       AlertDialog alertDialog = alertDialogBuilder.create();
       alertDialog.show();

   }
    @Override
    public void onFailure(String msg) {
        new AlertDialog.Builder(getActivity())
                .setTitle("Invalid Password")
                .setMessage("Please try again with the correct password.")
                .setCancelable(true)
                .setNegativeButton("close", null)
                .show();
    }

}



