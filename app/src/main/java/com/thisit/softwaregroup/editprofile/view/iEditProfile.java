package com.thisit.softwaregroup.editprofile.view;

import com.thisit.softwaregroup.common.IBaseView;

public interface iEditProfile extends IBaseView {
    void emptyUserName();
    void emptyMobileNumber();
    void emptyEmail();
    void emptyPostalCode();
    void emptyAddress();
    void emptyDateOfBirth();
    void onSuccess(String msg);
    void onFailure(String msg);

}
