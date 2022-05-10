package com.thisit.southavencrm.FAQList.view;


import com.thisit.southavencrm.FAQList.model.FAQListResponseModel;
import com.thisit.southavencrm.common.IBaseView;
import com.thisit.southavencrm.locateUs.model.LocationListResponseModel;

import java.util.ArrayList;

public interface IFAQListView extends IBaseView {
    void getLocationList(ArrayList<FAQListResponseModel> holdListResponseModelArrayList);

    //void getOrderDetail(ArrayList<HoldDetailResponseModel> holdDetailResponseModelArrayList);

    void onSuccess();

    void holdListClick(int position);
}
