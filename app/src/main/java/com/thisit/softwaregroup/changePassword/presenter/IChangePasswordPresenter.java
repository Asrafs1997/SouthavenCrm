package com.thisit.softwaregroup.changePassword.presenter;

public interface IChangePasswordPresenter {
    void apiCall(String currentpassword, String newpassword, String confirmpassword);
}
