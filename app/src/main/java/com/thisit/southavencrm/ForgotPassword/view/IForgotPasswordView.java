package com.thisit.southavencrm.ForgotPassword.view;

import com.thisit.southavencrm.common.IBaseView;

public interface IForgotPasswordView extends IBaseView {

    void emptyEmailId();
    void onSuccess(String msg);
    void onFailure(String msg);

}
