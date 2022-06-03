package com.thisit.southavencrm.common;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.Window;

import com.thisit.southavencrm.R;


import java.util.Objects;

public class BaseActivity extends AppCompatActivity implements IBaseView {
    private Dialog dialog;
//    private boolean apiInProgress;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putBoolean("APICallInProgress", apiInProgress);
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

//        if (savedInstanceState != null) {
//            apiInProgress = savedInstanceState.getBoolean("APICallInProgress");
//        }
//
//        if (apiInProgress) {
//            showProgress();
//        }
    }

    @Override
    public void showProgress() {
        if (dialog != null) {
            dialog.show();
//            apiInProgress = true;
        }
    }

    @Override
    public void hideProgress() {
        if (dialog != null) {
//            apiInProgress = false;
            dialog.dismiss();
        }
    }

}
