package com.thisit.southavencrm.locateUs.view;


import com.thisit.southavencrm.common.IBaseView;
import com.thisit.southavencrm.locateUs.model.LocationListResponseModel;

import java.util.ArrayList;

public interface ILocationListView extends IBaseView {
    void getLocationList(ArrayList<LocationListResponseModel> holdListResponseModelArrayList);

    //void getOrderDetail(ArrayList<HoldDetailResponseModel> holdDetailResponseModelArrayList);

    void onSuccess();

    void holdListClick(int position);
}
