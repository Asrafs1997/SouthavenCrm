package com.thisit.southavencrm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.thisit.southavencrm.Fragment.AboutFragment;
import com.thisit.southavencrm.Fragment.CardFragment;
import com.thisit.southavencrm.Fragment.HistroyFragment;
import com.thisit.southavencrm.Fragment.LocationFragment;
import com.thisit.southavencrm.Fragment.ProfileFragment;

public class ECardActivity extends AppCompatActivity {
    private Activity activity;
    public TextView title_tv;
    private FloatingActionButton card_fab;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_card);
        activity = ECardActivity.this;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryVariant));
        }
        card_fab = findViewById(R.id.card_fab);
        title_tv = (TextView) findViewById(R.id.title_tv);


        card_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title_tv.setText(R.string.my_card);
                getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer, new CardFragment()).commit();
            }
        });


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomnavigationbar);

        bottomNavigationView.setBackground(null);



        getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer, new CardFragment()).commit();
        title_tv.setText(R.string.my_card);
        bottomNavigationView.setSelectedItemId(R.id.card);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // By using switch we can easily get
            // the selected fragment
            // by using there id.
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.history:
                    selectedFragment = new HistroyFragment();
                    break;
                case R.id.location:
                    selectedFragment = new LocationFragment();
                    break;

                case R.id.profile:
                    selectedFragment = new ProfileFragment();
                    break;
                case R.id.about:
                    selectedFragment = new AboutFragment();
                    break;
            }
            // It will help to replace the
            // one fragment to other.
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framecontainer, selectedFragment)
                    .commit();
            return true;
        }
    };
}
