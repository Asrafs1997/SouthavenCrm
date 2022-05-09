package com.thisit.southavencrm.Notification.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.thisit.southavencrm.R;
import com.thisit.southavencrm.dashboard.view.ECardActivity;

public class NotificationActivity extends AppCompatActivity {
    private Activity activity;
    private TextView close_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        activity = NotificationActivity.this;
        close_btn = (TextView) findViewById(R.id.close_btn);

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ECardActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}