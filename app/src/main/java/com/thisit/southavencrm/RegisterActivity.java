package com.thisit.southavencrm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RegisterActivity extends AppCompatActivity {
    private Spinner title_spi;
    private String[] titles = new String[]{"Mr", "Ms", "Mrs", "Mdm"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        title_spi = (Spinner) findViewById(R.id.title_spi);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, titles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        title_spi.setAdapter(adapter);
    }
}