package com.thisit.southavencrm.login.view;


import com.thisit.southavencrm.common.IBaseView;

public interface ILoginView extends IBaseView {
    void emptyUserName();
    void emptyPassword();
    void onSuccess(String massage);
    void onFailure(String massage);

}
