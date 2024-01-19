package com.thisit.softwaregroup.Notification.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.thisit.softwaregroup.R;
import com.thisit.softwaregroup.common.BaseActivity;
import com.thisit.softwaregroup.dashboard.view.ECardActivity;

public class NotificationActivity extends BaseActivity implements View.OnClickListener  {
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

    @Override
    public void onClick(View view) {

    }
}