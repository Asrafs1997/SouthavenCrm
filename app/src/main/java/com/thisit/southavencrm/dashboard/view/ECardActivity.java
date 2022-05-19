package com.thisit.southavencrm.dashboard.view;

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
import com.thisit.southavencrm.Fragment.ProfileFragment;
import com.thisit.southavencrm.OrderList.view.OrderListFragment;
import com.thisit.southavencrm.R;
import com.thisit.southavencrm.common.ConfigApp;
import com.thisit.southavencrm.common.ToastMessage;
import com.thisit.southavencrm.dashboard.presenter.GetprofilePresenter;
import com.thisit.southavencrm.dashboard.presenter.IGetprofilePresenter;
import com.thisit.southavencrm.locateUs.view.LocationFragment;
import com.thisit.southavencrm.login.presenter.ILoginPresenter;
import com.thisit.southavencrm.login.presenter.LoginPresenter;

public class ECardActivity extends AppCompatActivity  implements  IGetprofileView{
    private Activity activity;
    public TextView title_tv;
    private FloatingActionButton card_fab;
    private BottomNavigationView bottomNavigationView;
    private IGetprofilePresenter iGetprofilePresenter;
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
        iGetprofilePresenter = (IGetprofilePresenter) new GetprofilePresenter(this);
        iGetprofilePresenter.apiCall(ConfigApp.getCompanyCode(), ConfigApp.getContactID());

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
                    selectedFragment = new OrderListFragment();
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





    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void offlineDialog() {

    }

    @Override
    public void CompanyCode() {
        ToastMessage.toast("Company Code is empty");
    }

    @Override
    public void ContactID() {
        ToastMessage.toast("Contact ID  is empty");
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailed() {

    }

    @Override
    public void PrefixonSucess() throws ClassNotFoundException {

    }

    @Override
    public void PrefixonFailed() {

    }

    @Override
    public void onemptyprefix() {

    }

    @Override
    public void onFailure() {

    }
}
