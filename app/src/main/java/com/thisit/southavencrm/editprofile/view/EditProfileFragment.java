package com.thisit.southavencrm.editprofile.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
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


import androidx.annotation.RequiresApi;

import com.thisit.southavencrm.Fragment.ProfileFragment;
import com.thisit.southavencrm.OrderList.view.OrderListFragment;
import com.thisit.southavencrm.R;
import com.thisit.southavencrm.common.BaseFragment;
import com.thisit.southavencrm.common.ConfigApp;
import com.thisit.southavencrm.dashboard.view.ECardActivity;
import com.thisit.southavencrm.editprofile.model.EditProfileResponseModel;
import com.thisit.southavencrm.editprofile.presenter.EditProfilePresenter;
import com.thisit.southavencrm.editprofile.presenter.IEditProfilePresenter;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class EditProfileFragment extends BaseFragment implements iEditProfile {
    private View root;
    private Activity activity;
    private Spinner title_spi;
    private IEditProfilePresenter iEditProfilePresenter;
    private ArrayList<String> titles = new ArrayList<String>();
   // private String[] titles = new String[]{"Mr", "Ms", "Mrs", "Mdm"};
    private Button Savebutton;
    private SimpleDateFormat dateFormatter;
    private EditText name_et, mobile_number_et, email_et, postalcode_et, Address_et;
    private static TextView dob_et;
    private String Titlestr;

    @RequiresApi(api = Build.VERSION_CODES.N)
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


            //dateFormatter = new SimpleDateFormat("YYYY-MM-DD", Locale.US);
        dateFormatter = new SimpleDateFormat("YYYY-MM-dd", Locale.US);

        titles.add("Mr");
        titles.add("Ms");
        titles.add("Mrs");
        titles.add("Mdm");

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, titles);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);

      /*  ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.item_layout_spinner, titles);
        adapter.setDropDownViewResource(R.layout.item_layout_spinner);*/

        title_spi.setAdapter(adapter);

        title_spi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub
                Titlestr = title_spi.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });


        name_et.setText( ConfigApp.getContactName());
     //   lastname_EditText.setText( ConfigApp.getLastName());
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
            //        editProfileResponseModel.setLastName(lastname_EditText.getText().toString());
                    editProfileResponseModel.setHandphoneNo(mobile_number_et.getText().toString());
                    editProfileResponseModel.setEmail(email_et.getText().toString());
                    editProfileResponseModel.setPostalcode(postalcode_et.getText().toString());
                    editProfileResponseModel.setAddress1(Address_et.getText().toString());
                    editProfileResponseModel.setDOB(dob_et.getText().toString());
                    editProfileResponseModel.setSalutation(Titlestr);
                    iEditProfilePresenter.apiCall(editProfileResponseModel);
                } else {
                    Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (!TextUtils.isEmpty(ConfigApp.getTitle())) {
            for (int c = 0; c < titles.size(); c++) {
                if (ConfigApp.getTitle().equalsIgnoreCase(titles.get(c).toString())) {
                    title_spi.setSelection(c);
                }
            }
        }
        dob_et.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance(Locale.getDefault());
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                //todo
                                Calendar newDate = Calendar.getInstance();
                                newDate.set(year, month, dayOfMonth);
                                dob_et.setText(dateFormatter.format(newDate.getTime()));

                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });


        return root;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ECardActivity) getActivity()).title_tv.setText(R.string.edit_profile);
        ((ECardActivity) getActivity()).ishome = false;
        ((ECardActivity) getActivity()).isabout = false;
        ((ECardActivity) getActivity()).isprofile = true;
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
                .setCancelable(true)
                .setMessage(msg);
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        ConfigApp.setTitle(Titlestr);
                        ConfigApp.setContactName("\t"+name_et.getText().toString());
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


}

