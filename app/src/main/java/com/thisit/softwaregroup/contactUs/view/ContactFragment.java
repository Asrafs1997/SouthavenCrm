package com.thisit.softwaregroup.contactUs.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.thisit.softwaregroup.Fragment.AboutFragment;
import com.thisit.softwaregroup.R;
import com.thisit.softwaregroup.common.BaseFragment;
import com.thisit.softwaregroup.common.ConfigApp;
import com.thisit.softwaregroup.contactUs.presenter.ContactUsPresenter;
import com.thisit.softwaregroup.contactUs.presenter.IContactUsPresenter;
import com.thisit.softwaregroup.dashboard.view.ECardActivity;

public class ContactFragment  extends  BaseFragment implements iContactUsFragment {

    private View root;
    private Activity activity;
    private IContactUsPresenter iContactUsPresenter;
    private Button Savebutton;
    private EditText subjectmessage_EditText,message_et;
    private TextView txtUsername,txtEmail,txtPhoneNo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_contact, container, false);
        activity = getActivity();
        Savebutton = root.findViewById(R.id.Savebutton);
        subjectmessage_EditText = root.findViewById(R.id.subjectmessage_EditText);
        message_et = root.findViewById(R.id.message_et);
        txtUsername =  root.findViewById(R.id.username_textView);
        txtEmail=  root.findViewById(R.id.txt_email);
        txtPhoneNo=  root.findViewById(R.id.txt_phoneno);
        txtUsername.setText(ConfigApp.getContactName());
        txtEmail.setText(ConfigApp.getEMAIL());
        txtPhoneNo.setText(ConfigApp.getMOBILE_NUMBER());
        iContactUsPresenter = new ContactUsPresenter(this);
        Savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (subjectmessage_EditText.getText().toString().length() == 0) {
                    subjectmessage_EditText.setError("Enter the Subject Message");
                    subjectmessage_EditText.requestFocus();
                } else if (message_et.getText().toString().length() == 0) {
                    message_et.setError("Enter the Massage");
                    message_et.requestFocus();
                }else {
                    if (ConfigApp.isNetworkAvailable(activity)) {
                        iContactUsPresenter.apiCall(subjectmessage_EditText.getText().toString(),message_et.getText().toString());
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
        ((ECardActivity) getActivity()).title_tv.setText(R.string.contactus);
        ((ECardActivity) getActivity()).ishome=false;
        ((ECardActivity) getActivity()).isabout=true;
        ((ECardActivity) getActivity()).isprofile=false;
    }




    @Override
    public void onSuccess() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder
                .setTitle("Membership Updated")
                .setCancelable(true)
                .setMessage("Thank you for contacting us. We have received your message");
        alertDialogBuilder.setPositiveButton("Close",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.framecontainer, new AboutFragment())
                                .commit();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @Override
    public void onFailure() {
        new AlertDialog.Builder(getActivity())
                .setTitle("Contact Failed")
                .setMessage("We are unable to receive your message at this time. please try again later.")
                .setCancelable(true)
                .setNegativeButton("close", null)
                .show();
    }




}


