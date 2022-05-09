package com.thisit.southavencrm.contactUs.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import com.thisit.southavencrm.R;
import com.thisit.southavencrm.common.ConfigApp;
import com.thisit.southavencrm.contactUs.presenter.ContactUsPresenter;
import com.thisit.southavencrm.contactUs.presenter.IContactUsPresenter;
import com.thisit.southavencrm.dashboard.view.ECardActivity;

public class ContactFragment  extends Fragment implements iContactUsFragment {

    private View root;
    private Activity activity;
    private IContactUsPresenter iContactUsPresenter;
    private Button Savebutton;
    private EditText subjectmessage_EditText,message_et;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_contact, container, false);
        activity = getActivity();

        iContactUsPresenter = new ContactUsPresenter(this);
        Savebutton = (Button) root.findViewById(R.id.Savebutton);
        subjectmessage_EditText = (EditText) root.findViewById(R.id.subjectmessage_EditText);
        message_et = (EditText) root.findViewById(R.id.message_et);


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
    }




    @Override
    public void onSuccess() {
        new AlertDialog.Builder(getActivity())
                .setTitle("Thank you")
                .setMessage("Thank you for contacting us. We have received your message")
                .setCancelable(false)
                .setNegativeButton("ok", null)
                .show();
    }

    @Override
    public void onFailure() {
        new AlertDialog.Builder(getActivity())
                .setTitle("Contact Failed")
                .setMessage("We are unable to receive your message at this time. please try again later.")
                .setCancelable(false)
                .setNegativeButton("close", null)
                .show();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void offlineDialog() {

    }

    @Override
    public void hideOffLineDialog() {

    }

    @Override
    public void onLineOffLineCancelClickListener() {

    }
}



