package com.thisit.southavencrm.registration.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.thisit.southavencrm.login.view.LoginActivity;
import com.thisit.southavencrm.registration.model.RegistrationRequestModel;
import com.thisit.southavencrm.registration.presenter.IRegistrationPresenter;
import com.thisit.southavencrm.registration.presenter.RegistrationPresenter;

public class RegisterActivity extends BaseActivity implements View.OnClickListener,IRegistrationView  {
    private Activity activity;
    private Spinner title_spi;
    private String[] titles = new String[]{"Mr", "Ms", "Mrs", "Mdm"};
    private EditText firstname_EditText,lastname_EditText,mobilenum_EditText,
            emailID_EditText,password_EditText,conformpassword_EditText,postalcode_EditText,
            Address_EditText,date_Of_Birth_EditText;
    private IRegistrationPresenter iRegistrationPresenter;
    private TextView btn_login;
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
        date_Of_Birth_EditText = (EditText) findViewById(R.id.date_Of_Birth_EditText);
        btn_login = (TextView) findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, LoginActivity.class);
                startActivity(intent);
                finish();
            }


        });

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, titles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        title_spi.setAdapter(adapter);

        findViewById(R.id.arrowback_img).setOnClickListener(this);
        findViewById(R.id.Savebutton).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.arrowback_img) {
            activity.finish();
        }else if (id == R.id.Savebutton) {
            if (firstname_EditText.getText().toString().length() == 0) {
                firstname_EditText.setError("Enter the First Name");
                firstname_EditText.requestFocus();
            }else if (lastname_EditText.getText().toString().length() == 0) {
                lastname_EditText.setError("Enter the Last Name");
                lastname_EditText.requestFocus();
            }else if (mobilenum_EditText.getText().toString().length() == 0) {
                mobilenum_EditText.setError("Enter the Mobile Number");
                mobilenum_EditText.requestFocus();
            }else if (emailID_EditText.getText().toString().length() == 0) {
                emailID_EditText.setError("Enter the Email ID");
                emailID_EditText.requestFocus();
            }else if (password_EditText.getText().toString().length() == 0) {
                password_EditText.setError("Enter the Password");
                password_EditText.requestFocus();
            }else if (conformpassword_EditText.getText().toString().length() == 0) {
                conformpassword_EditText.setError("Enter the confirm  password");
                conformpassword_EditText.requestFocus();
            }else if (postalcode_EditText.getText().toString().length() == 0) {
                postalcode_EditText.setError("Enter the postal code");
                postalcode_EditText.requestFocus();
            }else if (Address_EditText.getText().toString().length() == 0) {
                Address_EditText.setError("Enter the Address");
                Address_EditText.requestFocus();
            }else if (date_Of_Birth_EditText.getText().toString().length() == 0) {
                date_Of_Birth_EditText.setError("Enter the date Of Birth");
                date_Of_Birth_EditText.requestFocus();
            }else {
                if (ConfigApp.isNetworkAvailable(activity)) {
                    RegistrationRequestModel registrationRequestModel = new RegistrationRequestModel();
                    registrationRequestModel.setCompanyCode("1");
                    registrationRequestModel.setContactID("0");
                    registrationRequestModel.setSalutation("SOUTHAVEN");
                    registrationRequestModel.setContactName(firstname_EditText.getText().toString());
                    registrationRequestModel.setLastName(lastname_EditText.getText().toString());
                    registrationRequestModel.setHandphoneNo(mobilenum_EditText.getText().toString());
                    registrationRequestModel.setEmail(emailID_EditText.getText().toString());
                    registrationRequestModel.setPostalcode(postalcode_EditText.getText().toString());
                    registrationRequestModel.setAddress1(Address_EditText.getText().toString());
                    registrationRequestModel.setDOB(date_Of_Birth_EditText.getText().toString());
                    registrationRequestModel.setPassword(password_EditText.getText().toString());
                    iRegistrationPresenter.apiCall(registrationRequestModel);
                } else {
                    Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }

            }
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
        startActivity(new Intent(activity, ECardActivity.class));
        finish();
    }

    @Override
    public void onFailure(String msg) {
        ToastMessage.toast("Username or Password is incorrect");
    }
}