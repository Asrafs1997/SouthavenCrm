package com.thisit.southavencrm.common;

public interface IBaseView {
    void showProgress();
    void hideProgress();
    void offlineDialog();
    void hideOffLineDialog();
    void convertToOffLineDialog(boolean isConvertToOffLine);
    void convertToOffLineDialog(final boolean isConvertToOffLine, Object object);
    void onLineOffLineClickListener(boolean isConvertToOffLine);
    void onLineOffLineCancelClickListener();
}
