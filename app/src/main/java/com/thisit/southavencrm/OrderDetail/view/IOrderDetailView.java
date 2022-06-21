package com.thisit.southavencrm.OrderDetail.view;

import com.thisit.southavencrm.OrderDetail.model.OrderDetailResponseModel;
import com.thisit.southavencrm.common.IBaseView;

import java.util.ArrayList;

public interface IOrderDetailView extends IBaseView {
    void getOrderDetail(ArrayList<OrderDetailResponseModel> holdListResponseModelArrayList);
    void onFailure();
}
