package com.thisit.softwaregroup.registration.view;

import com.thisit.softwaregroup.common.IBaseView;

public interface IRegistrationView extends IBaseView {
    void emptyName();
    void emptyEmail();
    void emptyMobileno();
    void onSuccess(String msg);
    void onFailure(String msg);
}