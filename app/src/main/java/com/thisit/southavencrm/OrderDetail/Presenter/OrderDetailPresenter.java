package com.thisit.southavencrm.OrderDetail.Presenter;

import android.util.Log;

import com.thisit.southavencrm.OrderDetail.model.OrderDetailResponseModel;
import com.thisit.southavencrm.OrderDetail.view.IOrderDetailView;
import com.thisit.southavencrm.OrderDetail.view.OrderDetailActivity;
import com.thisit.southavencrm.common.BasicAuth;
import com.thisit.southavencrm.common.Constants;
import com.thisit.southavencrm.common.ToastMessage;
import com.thisit.southavencrm.webClient.ApiClient;
import com.thisit.southavencrm.webClientHandler.OrderDetailAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailPresenter implements IOrderDetailPresenter {
    private IOrderDetailView iOrderDetailView;
    public OrderDetailPresenter(OrderDetailActivity orderDetailActivity) {
        this.iOrderDetailView=orderDetailActivity;
    }

    @Override
    public void apiCall(String CompanyCode, String TranNo) {
        if (CompanyCode.isEmpty()) {
            ToastMessage.toast(" CompanyCode is empty");
        }
        else if (TranNo.isEmpty()) {
            ToastMessage.toast(" TranNo is empty");
        }
        else {
            String requestData = "{\"CompanyCode\":" + CompanyCode + ",\"TranNo\":\"" + TranNo + "\"}";
            Log.i("orderDetailApi", requestData);
            OrderDetailAPI orderDetailAPI = ApiClient.getClient(Constants.BASE_URL).create(OrderDetailAPI.class);
//            RequestBody rawString = RequestBody.create(MediaType.parse("application/json"), requestData);

            Call<OrderDetailResponseModel> call = orderDetailAPI.orderDetail(requestData, BasicAuth.getAuth());
            iOrderDetailView.showProgress();
            call.enqueue(new Callback<OrderDetailResponseModel>() {
                @Override

                public void onResponse(Call<OrderDetailResponseModel> call, Response<OrderDetailResponseModel> response) {
                    iOrderDetailView.hideProgress();
                    if (response.isSuccessful()) {
                        Log.i("response\t\t", response.toString());
                        if (response.body() != null) {
                            if (response.body().isStatus()) {
                                if (response.body().getData().size() > 0) {
                                    iOrderDetailView.getOrderDetail(response.body().getData());
                                }else {
                                    iOrderDetailView.onFailure();
                                }
                            } else {
                                iOrderDetailView.onFailure();
                            }

                        } else {
                            iOrderDetailView.onFailure();
                        }
                    }
                }

                @Override
                public void onFailure(Call<OrderDetailResponseModel> call, Throwable t) {
                    Log.i("onFailureResponse 001", t.getMessage().toString());
                    //ToastMessage.toast(t.getMessage());
                    iOrderDetailView.hideProgress();

                }
            });

        }
    }






    }