package com.thisit.southavencrm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ForgotpasswordActivity extends AppCompatActivity {
    private Activity activity;
    private TextView btn_backlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        activity=ForgotpasswordActivity.this;
        btn_backlogin = (TextView) findViewById(R.id.btn_backlogin);

        btn_backlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}