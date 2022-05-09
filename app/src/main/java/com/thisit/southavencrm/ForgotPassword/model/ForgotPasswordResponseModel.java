package com.thisit.southavencrm.ForgotPassword.model;

public class ForgotPasswordResponseModel {
    private String $id;

    private boolean Result;

    private String msg;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public boolean isResult() {
        return Result;
    }

    public void setResult(boolean result) {
        Result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
