package com.thisit.softwaregroup.changePassword.view;

import com.thisit.softwaregroup.common.IBaseView;

public interface IChangePasswordFragment extends IBaseView {
    void onSuccess(String msg);
    void onFailure(String msg);
}
