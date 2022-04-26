package com.thisit.southavencrm;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.thisit.southavencrm.Fragment.AboutFragment;
import com.thisit.southavencrm.Fragment.CardFragment;
import com.thisit.southavencrm.Fragment.HistroyFragment;
import com.thisit.southavencrm.Fragment.LocationFragment;
import com.thisit.southavencrm.Fragment.ProfileFragment;

public class ECardActivity extends AppCompatActivity {
    public TextView title_tv;
    private FloatingActionButton card_fab;
    private ConstraintLayout constraintLayout, constraintLayout2, constraintLayout3, constraintLayout4;
    private boolean isCard = false, isProfile = false, isAbout = false, isRecharge = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_card);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryVariant));
        }
        card_fab = findViewById(R.id.card_fab);
        constraintLayout = findViewById(R.id.constraintLayout);
        constraintLayout2 = findViewById(R.id.constraintLayout2);
        constraintLayout3 = findViewById(R.id.constraintLayout3);
        constraintLayout4 = findViewById(R.id.constraintLayout4);
        title_tv = (TextView) findViewById(R.id.title_tv);

        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new CardFragment()).commit();
        title_tv.setText(R.string.my_card);
        isCard = true;

        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_tv.setText(R.string.tran_history);
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new HistroyFragment()).commit();
            }
        });
        constraintLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_tv.setText(R.string.locat_us);
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new LocationFragment()).commit();
            }
        });
        constraintLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isCard = false;
                isProfile = true;
                isAbout = false;
                title_tv.setText(R.string.profile_acco);
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new ProfileFragment()).commit();
            }
        });
        constraintLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isProfile = false;
                isCard = false;
                isAbout = true;
                title_tv.setText(R.string.about_us);
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new AboutFragment()).commit();
            }
        });
        card_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCard = true;
                title_tv.setText(R.string.my_card);
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new CardFragment()).commit();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (isCard) {
            isCard = false;
            super.onBackPressed();
        } else if (isProfile) {
            isProfile = false;
            isCard = true;
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new ProfileFragment()).commit();
        } else if (isAbout) {
            isAbout = false;
            isProfile = true;
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new AboutFragment()).commit();
        }else {
            super.onBackPressed();
        }

    }
}