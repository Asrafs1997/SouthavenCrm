package com.thisit.southavencrm.contactUs.view;

import com.thisit.southavencrm.common.IBaseView;

public interface iContactUsFragment extends IBaseView {
    void onSuccess();

    void onFailure();

    void showProgress();

    void hideProgress();

    void offlineDialog();

    void hideOffLineDialog();
    void onLineOffLineCancelClickListener();
}
