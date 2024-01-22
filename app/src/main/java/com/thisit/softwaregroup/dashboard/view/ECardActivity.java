package com.thisit.softwaregroup.dashboard.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.thisit.softwaregroup.Fragment.AboutFragment;
import com.thisit.softwaregroup.Fragment.CardFragment;
import com.thisit.softwaregroup.Fragment.HistroyFragment;
import com.thisit.softwaregroup.Fragment.ProfileFragment;
import com.thisit.softwaregroup.Notification.view.NotificationActivity;

import com.thisit.softwaregroup.R;
import com.thisit.softwaregroup.common.BaseActivity;
import com.thisit.softwaregroup.locateUs.view.LocationFragment;


public class ECardActivity extends BaseActivity {
    private Activity activity;
    public TextView title_tv;
    private FloatingActionButton card_fab;
    private BottomNavigationView bottomNavigationView;
    private ImageView notification_img;
    public boolean ishome = false, isprofile = false, isabout = false;
    private boolean backPressed;
    MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_e_card);
        activity = ECardActivity.this;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryVariant));
        }
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomnavigationbar);
        card_fab = findViewById(R.id.card_fab);
        title_tv = (TextView) findViewById(R.id.title_tv);
        notification_img = (ImageView) findViewById(R.id.notification_img);
        notification_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, NotificationActivity.class);
                startActivity(intent);
                //  finish();
            }
        });

        card_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer, new CardFragment()).commit();

                if (menuItem!= null) {
                    bottomNavigationView.getMenu().findItem(menuItem.getItemId()).setCheckable(false);
                }
            }
        });



        bottomNavigationView.setBackground(null);
        getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer, new CardFragment()).commit();
        bottomNavigationView.setSelectedItemId(R.id.card);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.Camera:
                    item.setCheckable(true);
                    selectedFragment = new HistroyFragment();
                    break;
                case R.id.location:
                    item.setCheckable(true);
                    selectedFragment = new LocationFragment();
                    break;
                case R.id.profile:
                    item.setCheckable(true);
                    selectedFragment = new ProfileFragment();
                    break;
                case R.id.about:
                    item.setCheckable(true);
                    selectedFragment = new AboutFragment();
                    break;
            }
            menuItem=item;
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framecontainer, selectedFragment)
                    .commit();
            return true;
        }
    };

    @Override
    public void onBackPressed() {
        if (ishome) {

        } else if (isprofile) {
            getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer, new ProfileFragment()).commit();
            bottomNavigationView.setSelectedItemId(R.id.profile);
        } else if (isabout) {
            getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer, new AboutFragment()).commit();
            bottomNavigationView.setSelectedItemId(R.id.about);
        } else {
            activity.finish();
        }

    }

}