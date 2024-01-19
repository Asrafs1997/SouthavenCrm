package com.thisit.softwaregroup.locateUs.view;


import com.thisit.softwaregroup.common.IBaseView;
import com.thisit.softwaregroup.locateUs.model.LocationListResponseModel;

import java.util.ArrayList;

public interface ILocationListView extends IBaseView {
    void getLocationList(ArrayList<LocationListResponseModel> holdListResponseModelArrayList);

    //void getOrderDetail(ArrayList<HoldDetailResponseModel> holdDetailResponseModelArrayList);

    void onSuccess();

    void holdListClick(int position);
}
