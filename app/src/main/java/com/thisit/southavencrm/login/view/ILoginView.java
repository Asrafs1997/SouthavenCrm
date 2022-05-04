package com.thisit.southavencrm.login.view;


import com.thisit.southavencrm.common.IBaseView;

public interface ILoginView extends IBaseView {
    void emptyUserName();
    void emptyPassword();
    void onSuccess();
    void onFailure();
    void onForgotPasswordSuccess();
    void workStationNotAvailable();
    void onSuccessForPermission(String roleId,String menuName);
}
