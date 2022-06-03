package com.thisit.southavencrm.changePassword.view;

import com.thisit.southavencrm.common.IBaseView;

public interface IChangePasswordFragment extends IBaseView {
    void onSuccess(String msg);
    void onFailure(String msg);
}
