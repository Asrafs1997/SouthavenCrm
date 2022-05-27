package com.thisit.southavencrm.common;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


import com.thisit.southavencrm.R;
import com.thisit.southavencrm.login.view.LoginActivity;

import java.util.Objects;

public class BaseActivity extends AppCompatActivity implements IBaseView {
    private Dialog dialog;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new Dialog(this) {
            @Override
            public void onBackPressed() {
            }
        };
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.base_activity);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    }

    @Override
    public void showProgress() {
        if (dialog != null && !isFinishing()) {
            try {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.show();
                    }
                }, 100);
            } catch (Exception e) {

            }

        }
    }

    @Override
    public void hideProgress() {
        if (dialog != null && !isFinishing()) {
            try {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                    }
                }, 100);
            } catch (Exception e) {

            }
        }
    }

    @Override
    public void offlineDialog() {
        Dialog offlineDialog = new Dialog(this) {
            @Override
            public void onBackPressed() {
            }
        };
        offlineDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        offlineDialog.setContentView(R.layout.offline_dialog);
        Objects.requireNonNull(offlineDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        offlineDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        offlineDialog.show();
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
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null) {
            dialog.dismiss();
        }
    }
    public void backexitPopup() {
        androidx.appcompat.app.AlertDialog.Builder builder1 = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder1.setTitle("Back");
        builder1.setMessage("Do you want to go back?");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        androidx.appcompat.app.AlertDialog alert11 = builder1.create();
        alert11.show();
        alert11.setCanceledOnTouchOutside(false);
    }

    public void navigateToLogin() {
        Intent i = new Intent(this, LoginActivity.class);
        i.putExtra("isFromLogin", true);
        i.putExtra("FromActivity", "Splash");
        startActivity(i);
    }
}
