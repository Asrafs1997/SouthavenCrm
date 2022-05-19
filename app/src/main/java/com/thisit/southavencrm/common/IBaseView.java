package com.thisit.southavencrm.common;

public interface IBaseView {
    void showProgress();
    void hideProgress();

    void offlineDialog();

    void onSuccess();

    void onFailed();

    void PrefixonSucess() throws ClassNotFoundException;

    void PrefixonFailed();

    void onemptyprefix();
}
