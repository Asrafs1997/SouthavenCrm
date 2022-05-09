package com.thisit.southavencrm.registration.view;

import com.thisit.southavencrm.common.IBaseView;

public interface IRegistrationView extends IBaseView {
    void emptyName();
    void emptyEmail();
    void emptyMobileno();
    void onSuccess(String msg);
    void onFailure(String msg);
}