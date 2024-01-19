package com.thisit.softwaregroup.EditSettings.view;

import com.thisit.softwaregroup.common.IBaseView;

public interface IEditSettingsView  extends IBaseView {
    void onSuccess();
    void onFailure();
    void HasPromoNotification( boolean HasPromoNotification);
    void HasOrderNotification( boolean isHasOrderNotification);
}


