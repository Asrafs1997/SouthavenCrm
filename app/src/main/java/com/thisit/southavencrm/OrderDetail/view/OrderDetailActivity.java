package com.thisit.southavencrm.OrderDetail.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.thisit.southavencrm.OrderDetail.Presenter.IOrderDetailPresenter;
import com.thisit.southavencrm.OrderDetail.Presenter.OrderDetailPresenter;
import com.thisit.southavencrm.OrderDetail.model.OrderDetailResponseModel;
import com.thisit.southavencrm.R;
import com.thisit.southavencrm.common.BaseActivity;
import com.thisit.southavencrm.common.ConfigApp;
import com.thisit.southavencrm.common.IBaseView;
import com.thisit.southavencrm.login.presenter.ILoginPresenter;
import com.thisit.southavencrm.login.presenter.LoginPresenter;
import com.thisit.southavencrm.login.view.LoginActivity;

import java.util.ArrayList;

public class OrderDetailActivity extends BaseActivity implements IOrderDetailView {
    private Activity activity;
    private IOrderDetailPresenter iOrderDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        activity = OrderDetailActivity.this;
        iOrderDetailPresenter = new OrderDetailPresenter(this);
        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();

        String CompanyCode = bundle.getString("CompanyCode");
        String TranNo = bundle.getString("TranNo");

        if (ConfigApp.isNetworkAvailable(activity)) {
            iOrderDetailPresenter.apiCall(CompanyCode,TranNo);
        } else {
            Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getOrderDetail(ArrayList<OrderDetailResponseModel> holdListResponseModelArrayList) {

        System.out.println("response123\n"+holdListResponseModelArrayList.get(0).getDetail().get(0).getCreateDate());

    }

    @Override
    public void onFailure() {

    }
}