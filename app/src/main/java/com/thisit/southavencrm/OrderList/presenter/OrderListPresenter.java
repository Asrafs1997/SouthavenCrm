package com.thisit.southavencrm.OrderList.presenter;

import android.util.Log;

import com.thisit.southavencrm.FAQList.model.FAQListResponseModel;
import com.thisit.southavencrm.FAQList.presenter.IFAQListPresenter;
import com.thisit.southavencrm.FAQList.view.FQAFragment;
import com.thisit.southavencrm.FAQList.view.IFAQListView;
import com.thisit.southavencrm.OrderList.model.OrderListResponseModel;
import com.thisit.southavencrm.OrderList.view.IOrderListView;
import com.thisit.southavencrm.OrderList.view.OrderListFragment;
import com.thisit.southavencrm.common.BasicAuth;
import com.thisit.southavencrm.common.ConfigApp;
import com.thisit.southavencrm.common.Constants;
import com.thisit.southavencrm.webClient.ApiClient;
import com.thisit.southavencrm.webClientHandler.FAQAPI;
import com.thisit.southavencrm.webClientHandler.OrderListAPI;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderListPresenter implements IOrderListPresenter {
    private IOrderListView iOrderListView;
    String requestData;
    public OrderListPresenter(OrderListFragment orderListFragment) {
        this.iOrderListView=orderListFragment;
    }

    @Override
    public void locationList(String CompanyCode,String fromDate, String toDate) {

        OrderListAPI orderListAPI = ApiClient.getClient(Constants.BASE_URL).create(OrderListAPI.class);
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("CompanyCode",CompanyCode);
            jsonObj.put("ContactID","6861");
            jsonObj.put("FromDate","01/02/2022");//"01/02/2022"
            jsonObj.put("ToDate","03/06/2022");//"03/06/2022"
           // jsonObj.put("ContactID",ConfigApp.getContactID());
           // jsonObj.put("FromDate",fromDate);//"01/04/2022"
           // jsonObj.put("ToDate",toDate);//"10/05/2022"
            requestData = jsonObj.toString();
            System.out.println("previewQR\t\t" + requestData);
        } catch (JSONException e) {

            e.printStackTrace();
        }
       Log.d("getHoldOrderList", requestData);
        Call<OrderListResponseModel> call = orderListAPI.holdList(requestData, BasicAuth.getAuth());
        iOrderListView.showProgress();
        call.enqueue(new Callback<OrderListResponseModel>() {
            @Override
            public void onResponse(Call<OrderListResponseModel> call, Response<OrderListResponseModel> response) {
                iOrderListView.hideProgress();
                Log.d(" toString ", response.toString());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if(response.body().isStatus()){
                            iOrderListView.getLocationList(response.body().getData());
                            System.out.println("response.body().getData()"+response.body().getData().get(0).getTranNo());
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<OrderListResponseModel> call, Throwable t) {
                Log.i("onFailureResponse 001", t.getMessage().toString());
                //ToastMessage.toast(t.getMessage());
                iOrderListView.hideProgress();

            }
        });



    }
}
