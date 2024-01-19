package com.thisit.softwaregroup.ForgotPassword.view;

import com.thisit.softwaregroup.common.IBaseView;

public interface IForgotPasswordView extends IBaseView {

    void emptyEmailId();
    void onSuccess(String msg);
    void onFailure(String msg);

}
