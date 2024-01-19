package com.thisit.softwaregroup.login.view;


import com.thisit.softwaregroup.common.IBaseView;

public interface ILoginView extends IBaseView {
    void emptyUserName();
    void emptyPassword();
    void onSuccess();
    void onFailure(String msg);

}
