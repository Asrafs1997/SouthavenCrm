package com.thisit.softwaregroup.changePassword.model;

public class ChangePasswordRequestModel {
    private String $id;
    private String msg;
    private boolean Status;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }
}
