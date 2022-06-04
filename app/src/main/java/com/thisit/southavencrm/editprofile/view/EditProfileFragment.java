package com.thisit.southavencrm.editprofile.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.thisit.southavencrm.Fragment.ProfileFragment;
import com.thisit.southavencrm.OrderList.view.OrderListFragment;
import com.thisit.southavencrm.R;
import com.thisit.southavencrm.common.BaseFragment;
import com.thisit.southavencrm.common.ConfigApp;
import com.thisit.southavencrm.dashboard.view.ECardActivity;
import com.thisit.southavencrm.editprofile.model.EditProfileResponseModel;
import com.thisit.southavencrm.editprofile.presenter.EditProfilePresenter;
import com.thisit.southavencrm.editprofile.presenter.IEditProfilePresenter;

import java.text.DecimalFormat;
import java.util.Calendar;


public class EditProfileFragment extends BaseFragment implements iEditProfile {
    private View root;
    private Activity activity;
    private Spinner title_spi;
    private IEditProfilePresenter iEditProfilePresenter;
    private String[] titles = new String[]{"Mr", "Ms", "Mrs", "Mdm"};
    private Button Savebutton;
    private EditText name_et, mobile_number_et, email_et, postalcode_et, Address_et;
private static TextView dob_et;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_editprofile, container, false);
        activity = getActivity();
        iEditProfilePresenter = (IEditProfilePresenter) new EditProfilePresenter(this);
        title_spi = (Spinner) root.findViewById(R.id.title_spi);
        Savebutton = (Button) root.findViewById(R.id.Savebutton);
        name_et = (EditText) root.findViewById(R.id.name_et);
        mobile_number_et = (EditText) root.findViewById(R.id.mobile_number_et);
        email_et = (EditText) root.findViewById(R.id.email_et);
        postalcode_et = (EditText) root.findViewById(R.id.postalcode_et);
        Address_et = (EditText) root.findViewById(R.id.Address_et);
        dob_et = (TextView) root.findViewById(R.id.dob_et);


        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, titles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        title_spi.setAdapter(adapter);

        title_spi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub
                //Toast.makeText(getActivity(), title_spi.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });


        name_et.setText(ConfigApp.getContactName());
        mobile_number_et.setText(ConfigApp.getMOBILE_NUMBER());
        email_et.setText(ConfigApp.getEMAIL());
        postalcode_et.setText(ConfigApp.getPOSTALCODE());
        Address_et.setText(ConfigApp.getADDRESS());
        dob_et.setText(ConfigApp.parseDateToddMMyyyytime(ConfigApp.getDOB()));

        Savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ConfigApp.isNetworkAvailable(activity)) {

                    EditProfileResponseModel editProfileResponseModel = new EditProfileResponseModel();
                    editProfileResponseModel.setContactName(name_et.getText().toString());
                    editProfileResponseModel.setHandphoneNo(mobile_number_et.getText().toString());
                    editProfileResponseModel.setEmail(email_et.getText().toString());
                    editProfileResponseModel.setPostalcode(postalcode_et.getText().toString());
                    editProfileResponseModel.setAddress1(Address_et.getText().toString());
                    editProfileResponseModel.setDOB(dob_et.getText().toString());
                    iEditProfilePresenter.apiCall(editProfileResponseModel);

                } else {
                    Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dob_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new SelectDateFragment();
                newFragment.show(getActivity().getFragmentManager(), "DatePicker");

            }
        });

        return root;


    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ECardActivity) getActivity()).title_tv.setText(R.string.edit_profile);
    }


    @Override
    public void emptyUserName() {

    }

    @Override
    public void emptyMobileNumber() {

    }

    @Override
    public void emptyEmail() {

    }

    @Override
    public void emptyPostalCode() {

    }

    @Override
    public void emptyAddress() {

    }

    @Override
    public void emptyDateOfBirth() {

    }

    @Override
    public void onSuccess(String msg) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder
                .setTitle("Membership Updated")
                .setMessage(msg);
        alertDialogBuilder.setPositiveButton("yes",
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
                .setTitle("Membership Updated")
                .setMessage(msg)
                .setCancelable(false)
                .setNegativeButton("ok", null)
                .show();
    }
    public static class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);

            //return new DatePickerDialog(getActivity(), android.R.style.Theme_Material_Light_Dialog_NoActionBar, this, yy, mm, dd);
            return new DatePickerDialog(getActivity(), android.R.style.Theme_Material_Light_Dialog_Alert, this, yy, mm, dd);
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm + 1, dd);
        }

        public void populateSetDate(int year, int month, int day) {
            DecimalFormat mFormat = new DecimalFormat("00");
            String Dates = mFormat.format(Double.valueOf(day)) + "/" + mFormat.format(Double.valueOf(month)) + "/" + mFormat.format(Double.valueOf(year));
            dob_et.setText(Dates);
        }

    }

}

