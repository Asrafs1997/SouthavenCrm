package com.thisit.southavencrm.GeneralSettings.model;

public class GeneralSettingsRequestModel {

 private String $id;

 private String msg;

 private boolean Status;

 private String SettingID ;

 private String SettingValue ;

 private String SettingDescription ;

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

 public String getSettingID() {
  return SettingID;
 }

 public void setSettingID(String settingID) {
  SettingID = settingID;
 }

 public String getSettingValue() {
  return SettingValue;
 }

 public void setSettingValue(String settingValue) {
  SettingValue = settingValue;
 }

 public String getSettingDescription() {
  return SettingDescription;
 }

 public void setSettingDescription(String settingDescription) {
  SettingDescription = settingDescription;
 }
}
