package com.thisit.softwaregroup.login.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import com.thisit.softwaregroup.ForgotPassword.view.ForgotpasswordActivity;

import com.thisit.softwaregroup.R;
import com.thisit.softwaregroup.common.BaseActivity;
import com.thisit.softwaregroup.common.ConfigApp;
import com.thisit.softwaregroup.common.ToastMessage;
import com.thisit.softwaregroup.dashboard.view.ECardActivity;
import com.thisit.softwaregroup.login.presenter.ILoginPresenter;
import com.thisit.softwaregroup.login.presenter.LoginPresenter;
import com.thisit.softwaregroup.registration.view.RegisterActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener, ILoginView {

    private Activity activity;
    private ILoginPresenter iLoginPresenter;
    private EditText LoginIDEditText, passwordEditText;
    private CheckBox rememberMeCheckBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        activity = LoginActivity.this;

        iLoginPresenter = new LoginPresenter(this);
        LoginIDEditText = findViewById(R.id.login_useremail);
        passwordEditText = findViewById(R.id.login_password);
        rememberMeCheckBox = findViewById(R.id.remember_me);

        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_forgotpassword).setOnClickListener(this);
        findViewById(R.id.btn_register).setOnClickListener(this);



        if (!ConfigApp.getLogin_ID().isEmpty()) {
            LoginIDEditText.setText(ConfigApp.getLogin_ID());
            passwordEditText.setText(ConfigApp.getPassword());
           // LoginIDEditText.setSelection(ConfigApp.getLogin_ID().length());
            rememberMeCheckBox.setChecked(true);
        }

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_forgotpassword) {
            Intent intent = new Intent(activity, ForgotpasswordActivity.class);
            startActivity(intent);
        } else if (id == R.id.btn_register) {
            Intent intent = new Intent(activity, RegisterActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.btn_login) {
            if (ConfigApp.isNetworkAvailable(activity)) {
                iLoginPresenter.apiCall(LoginIDEditText.getText().toString(), passwordEditText.getText().toString());
            } else {
                Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void emptyUserName() {
      //  ToastMessage.toast("username is empty");

        LoginIDEditText.setError("Enter the Email");
        LoginIDEditText.requestFocus();
    }

    @Override
    public void emptyPassword() {
       // ToastMessage.toast("password is empty");
        passwordEditText.setError("Enter the password");
        passwordEditText.requestFocus();
    }

    @Override
    public void onSuccess() {
        startActivity(new Intent(LoginActivity.this, ECardActivity.class));
        finish();
    }

    @Override
    public void onFailure(String msg) {
        ToastMessage.toast("username or password is incorrect");
        new AlertDialog.Builder(this)
                .setTitle("Login Failed")
                .setMessage(msg)
                .setCancelable(false)
                .setNegativeButton("OKAY", null)
                .show();
    }
}