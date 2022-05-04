package com.thisit.southavencrm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.thisit.southavencrm.login.view.LoginActivity;

public class RegisterActivity extends AppCompatActivity {
    private Activity activity;
    private Spinner title_spi;
    private String[] titles = new String[]{"Mr", "Ms", "Mrs", "Mdm"};
    private ImageView arrowback_img;
    private TextView btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        activity = RegisterActivity.this;
        title_spi = (Spinner) findViewById(R.id.title_spi);

        btn_login = (TextView) findViewById(R.id.btn_login);
        arrowback_img = (ImageView) findViewById(R.id.arrowback_img);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, titles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        title_spi.setAdapter(adapter);

        arrowback_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}