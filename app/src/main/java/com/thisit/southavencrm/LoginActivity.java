package com.thisit.southavencrm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private Activity activity;
    private Button btn_login;
    private TextView btn_forgotpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        activity = LoginActivity.this;

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_forgotpassword = (TextView) findViewById(R.id.btn_forgotpassword);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, SplashActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ForgotpasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}