package com.thisit.southavencrm.EditSettings.view;

import android.widget.Switch;

import com.thisit.southavencrm.R;
import com.thisit.southavencrm.common.IBaseView;

public interface IEditSettingsView  extends IBaseView {
    void onSuccess();
    void onFailure();
    void HasPromoNotification( boolean HasPromoNotification);
    void HasOrderNotification( boolean isHasOrderNotification);
}


