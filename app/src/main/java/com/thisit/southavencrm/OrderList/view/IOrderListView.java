package com.thisit.southavencrm.OrderList.view;


import com.thisit.southavencrm.FAQList.model.FAQListResponseModel;
import com.thisit.southavencrm.OrderList.model.OrderListResponseModel;
import com.thisit.southavencrm.common.IBaseView;

import java.util.ArrayList;

public interface IOrderListView extends IBaseView {
    void getLocationList(ArrayList<OrderListResponseModel> holdListResponseModelArrayList);

    void onSuccess();

    void holdListClick(int position);
}
