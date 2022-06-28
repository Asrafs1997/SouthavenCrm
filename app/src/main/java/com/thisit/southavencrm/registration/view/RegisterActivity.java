package com.thisit.southavencrm.registration.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.thisit.southavencrm.Notification.view.NotificationActivity;
import com.thisit.southavencrm.R;
import com.thisit.southavencrm.common.BaseActivity;
import com.thisit.southavencrm.common.ConfigApp;
import com.thisit.southavencrm.common.ToastMessage;
import com.thisit.southavencrm.dashboard.view.ECardActivity;
import com.thisit.southavencrm.editprofile.view.EditProfileFragment;
import com.thisit.southavencrm.login.view.LoginActivity;
import com.thisit.southavencrm.registration.model.RegistrationRequestModel;
import com.thisit.southavencrm.registration.presenter.IRegistrationPresenter;
import com.thisit.southavencrm.registration.presenter.RegistrationPresenter;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class RegisterActivity extends BaseActivity implements View.OnClickListener, IRegistrationView {
    private Activity activity;
    private Spinner title_spi;
    private String[] titles = new String[]{"Mr", "Ms", "Mrs", "Mdm"};
    private EditText firstname_EditText, lastname_EditText, mobilenum_EditText,
            emailID_EditText, password_EditText, conformpassword_EditText, postalcode_EditText,
            Address_EditText;
    private IRegistrationPresenter iRegistrationPresenter;
    private static TextView btn_login, date_Of_Birth_EditText;
    private SimpleDateFormat dateFormatter;
    private String postalcode = "",Titlestr="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        activity = RegisterActivity.this;

        iRegistrationPresenter = new RegistrationPresenter(this);
        title_spi = (Spinner) findViewById(R.id.title_spi);
        firstname_EditText = (EditText) findViewById(R.id.firstname_EditText);
        lastname_EditText = (EditText) findViewById(R.id.lastname_EditText);
        mobilenum_EditText = (EditText) findViewById(R.id.mobilenum_EditText);
        emailID_EditText = (EditText) findViewById(R.id.emailID_EditText);
        password_EditText = (EditText) findViewById(R.id.password_EditText);
        conformpassword_EditText = (EditText) findViewById(R.id.conformpassword_EditText);
        postalcode_EditText = (EditText) findViewById(R.id.postalcode_EditText);
        Address_EditText = (EditText) findViewById(R.id.Address_EditText);
        date_Of_Birth_EditText = (TextView) findViewById(R.id.date_Of_Birth_EditText);
        btn_login = (TextView) findViewById(R.id.btn_login);
        //   dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        dateFormatter = new SimpleDateFormat("YYYY-MM-dd", Locale.US);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, LoginActivity.class);
                startActivity(intent);
                finish();
            }


        });

        date_Of_Birth_EditText.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance(Locale.getDefault());
                DatePickerDialog datePickerDialog = new DatePickerDialog(activity,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                //todo
                                Calendar newDate = Calendar.getInstance();
                                newDate.set(year, month, dayOfMonth);
                                date_Of_Birth_EditText.setText(dateFormatter.format(newDate.getTime()));

                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });


        postalcode_EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub
                postalcode = postalcode_EditText.getText().toString();
                if (postalcode_EditText.getText().toString().length() >= 6) {
                    getAddressByPostalCode(postalcode);
                }

            }
        });

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, titles);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        title_spi.setAdapter(adapter);



        title_spi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub
                Titlestr= title_spi.getSelectedItem().toString();
                //Toast.makeText(getActivity(), title_spi.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });


        findViewById(R.id.arrowback_img).setOnClickListener(this);
        findViewById(R.id.Savebutton).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.arrowback_img) {
            Intent intent = new Intent(activity, LoginActivity.class);
            startActivity(intent);
            activity.finish();
        } else if (id == R.id.Savebutton) {
            if (firstname_EditText.getText().toString().length() == 0) {
                firstname_EditText.setError("Enter the First Name");
                firstname_EditText.requestFocus();
            } else if (lastname_EditText.getText().toString().length() == 0) {
                lastname_EditText.setError("Enter the Last Name");
                lastname_EditText.requestFocus();
            } else if (mobilenum_EditText.getText().toString().length() == 0) {
                mobilenum_EditText.setError("Enter the Mobile Number");
                mobilenum_EditText.requestFocus();
            } else if (emailID_EditText.getText().toString().length() == 0) {
                emailID_EditText.setError("Enter the Email ID");
                emailID_EditText.requestFocus();
            } else if (password_EditText.getText().toString().length() == 0) {
                password_EditText.setError("Enter the Password");
                password_EditText.requestFocus();
            } else if (conformpassword_EditText.getText().toString().length() == 0) {
                conformpassword_EditText.setError("Enter the confirm  password");
                conformpassword_EditText.requestFocus();
            } else if (postalcode_EditText.getText().toString().length() == 0) {
                postalcode_EditText.setError("Enter the postal code");
                postalcode_EditText.requestFocus();
            } else if (Address_EditText.getText().toString().length() == 0) {
                Address_EditText.setError("Enter the Address");
                Address_EditText.requestFocus();
            } else if (date_Of_Birth_EditText.getText().toString().length() == 0) {
                date_Of_Birth_EditText.setError("Enter the date Of Birth");
                date_Of_Birth_EditText.requestFocus();
            } else {
                if (ConfigApp.isNetworkAvailable(activity)) {
                    RegistrationRequestModel registrationRequestModel = new RegistrationRequestModel();
                    registrationRequestModel.setCompanyCode("1");
                    registrationRequestModel.setContactID("0");
                    registrationRequestModel.setSalutation(title_spi.getSelectedItem().toString());
                    registrationRequestModel.setContactName(firstname_EditText.getText().toString());
                    registrationRequestModel.setLastName(lastname_EditText.getText().toString());
                    registrationRequestModel.setHandphoneNo(mobilenum_EditText.getText().toString());
                    registrationRequestModel.setEmail(emailID_EditText.getText().toString());
                    registrationRequestModel.setPostalcode(postalcode_EditText.getText().toString());
                    registrationRequestModel.setAddress1(Address_EditText.getText().toString());
                    registrationRequestModel.setDOB(date_Of_Birth_EditText.getText().toString());
                    registrationRequestModel.setPassword(password_EditText.getText().toString());
                    registrationRequestModel.setSalutation(Titlestr);
                    iRegistrationPresenter.apiCall(registrationRequestModel);
                } else {
                    Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    public void getAddressByPostalCode(String val_pcode) {

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        try {
            if (ConfigApp.isNetworkAvailable(activity)) {
            List<Address> addresses1 = geocoder.getFromLocationName(val_pcode, 1);
            if (!addresses1.isEmpty()) {
                Address obj1 = addresses1.get(0);
                if (obj1 != null) {
                    List<Address> addresses = geocoder.getFromLocation(obj1.getLatitude(), obj1.getLongitude(), 1);
                    Address obj = addresses.get(0);

                    String streetnum ="" ,address="",Country="";

                    if (!TextUtils.isEmpty(obj.getFeatureName()) && !obj.getFeatureName().equalsIgnoreCase("null")) {
                        streetnum=obj.getFeatureName();
                    }else {
                        streetnum="";
                    }

                    if (!TextUtils.isEmpty(obj.getThoroughfare()) && !obj.getThoroughfare().equalsIgnoreCase("null")) {
                        address=",\t"+obj.getThoroughfare();
                    }else {
                        address="";
                    }

                    if (!TextUtils.isEmpty(obj.getCountryName()) && !obj.getCountryName().equalsIgnoreCase("null")) {
                        Country=",\t"+obj.getCountryName();
                    }else {
                        Country="";
                    }

                    Address_EditText.setText(streetnum+address+Country);

             /*       System.out.println("getThoroughfare" + obj.getThoroughfare());
                    System.out.println("getPostalCode" + obj.getPostalCode());
                    System.out.println("getSubThoroughfare" + obj.getSubThoroughfare());
                    System.out.println("getPremises" + obj.getPremises());
                    System.out.println("getCountryName" + obj.getCountryName());
                    System.out.println("getFeatureName" + obj.getFeatureName());
                    System.out.println("getFeatureName" + "#" + obj.getFeatureName());*/
                }
            }
            }else {
                Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(activity, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void emptyName() {
        firstname_EditText.setError("Enter the First Name");
    }

    @Override
    public void emptyEmail() {
        emailID_EditText.setError("Enter the Email ID");
    }

    @Override
    public void emptyMobileno() {
        mobilenum_EditText.setError("Enter the Mobile Number");
    }

    @Override
    public void onSuccess(String msg) {
        ConfigApp.setTitle(Titlestr);
        startActivity(new Intent(activity, ECardActivity.class));
        finish();
    }

    @Override
    public void onFailure(String msg) {
        ToastMessage.toast("Username or Password is incorrect");
    }

}