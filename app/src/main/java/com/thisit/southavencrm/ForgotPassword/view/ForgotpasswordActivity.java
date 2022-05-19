package com.thisit.southavencrm.ForgotPassword.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.thisit.southavencrm.ForgotPassword.presenter.ForgotPasswordPresenter;
import com.thisit.southavencrm.ForgotPassword.presenter.IForgotPasswordPresenter;
import com.thisit.southavencrm.R;
import com.thisit.southavencrm.common.ConfigApp;
import com.thisit.southavencrm.common.ToastMessage;
import com.thisit.southavencrm.login.view.LoginActivity;

public class ForgotpasswordActivity extends AppCompatActivity implements View.OnClickListener, IForgotPasswordView {
    private Activity activity;
    private EditText email_EditText;
    private IForgotPasswordPresenter iForgotPasswordPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        activity=ForgotpasswordActivity.this;
        iForgotPasswordPresenter = (IForgotPasswordPresenter) new ForgotPasswordPresenter(this);
        email_EditText=(EditText)findViewById(R.id.email_EditText);
        findViewById(R.id.btn_backlogin).setOnClickListener(this);
        findViewById(R.id.send_btn).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_backlogin) {
            Intent intent = new Intent(activity, LoginActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.send_btn) {
            if (ConfigApp.isNetworkAvailable(activity)) {
                iForgotPasswordPresenter.apiCall( email_EditText.getText().toString());
            } else {
                Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void emptyEmailId() {
        ToastMessage.toast("Email ID is empty");
    }

    @Override
    public void onSuccess(String msg) {
        new AlertDialog.Builder(activity)
                .setTitle("Thank you")
                .setMessage(msg)
                .setCancelable(false)
                .setNegativeButton("ok", null)
                .show();
    }

    @Override
    public void onFailure(String msg) {
        new AlertDialog.Builder(activity)
                .setTitle("Contact Failed")
                .setMessage(msg)
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
    public void onSuccess() {

    }

    @Override
    public void onFailed() {

    }

    @Override
    public void PrefixonSucess() throws ClassNotFoundException {

    }

    @Override
    public void PrefixonFailed() {

    }

    @Override
    public void onemptyprefix() {

    }


}