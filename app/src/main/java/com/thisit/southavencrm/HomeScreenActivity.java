package com.thisit.southavencrm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.thisit.southavencrm.common.ConfigApp;
import com.thisit.southavencrm.dashboard.view.ECardActivity;
import com.thisit.southavencrm.login.view.LoginActivity;
import com.thisit.southavencrm.registration.view.RegisterActivity;

public class HomeScreenActivity extends AppCompatActivity {
    private Activity activity;
    private Button btn_login, btn_join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        activity = HomeScreenActivity.this;

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_join = (Button) findViewById(R.id.btn_join);

        System.out.println("********************"+ConfigApp.getCompanyCode());

        if (!TextUtils.isEmpty(ConfigApp.getCompanyCode()) && !ConfigApp.getCompanyCode().equalsIgnoreCase("null")) {
            if (ConfigApp.isNetworkAvailable(activity)) {
                Intent intent = new Intent(activity, ECardActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        } else {
            ConfigApp.setCompanyCode("");
        }


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(activity, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}