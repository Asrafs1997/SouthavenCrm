package com.thisit.southavencrm.changePassword.presenter;

public interface IChangePasswordPresenter {
    void apiCall(String currentpassword, String newpassword, String confirmpassword);
}
