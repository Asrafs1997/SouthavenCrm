package com.thisit.southavencrm.dashboard.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.thisit.southavencrm.Fragment.AboutFragment;
import com.thisit.southavencrm.Fragment.CardFragment;
import com.thisit.southavencrm.Fragment.ProfileFragment;
import com.thisit.southavencrm.Notification.view.NotificationActivity;
import com.thisit.southavencrm.OrderList.view.OrderListFragment;
import com.thisit.southavencrm.R;
import com.thisit.southavencrm.common.BaseActivity;
import com.thisit.southavencrm.common.ConfigApp;
import com.thisit.southavencrm.common.ToastMessage;
import com.thisit.southavencrm.dashboard.presenter.GetprofilePresenter;
import com.thisit.southavencrm.dashboard.presenter.IGetprofilePresenter;
import com.thisit.southavencrm.locateUs.view.LocationFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


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
                bottomNavigationView.getMenu().findItem(menuItem.getItemId()).setCheckable(false);
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
                case R.id.history:
                    item.setCheckable(true);
                    selectedFragment = new OrderListFragment();
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
