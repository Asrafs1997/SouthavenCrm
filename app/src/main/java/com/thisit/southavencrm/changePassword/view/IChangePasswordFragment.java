package com.thisit.southavencrm.changePassword.view;

import com.thisit.southavencrm.common.IBaseView;

public interface IChangePasswordFragment extends IBaseView {
    void emptycurrentPassword();
    void emptynewPassword();
    void emptyconfirmPassword();
    void onSuccess(String msg);
    void onFailure(String msg);
}
