package com.thisit.southavencrm.GeneralSettings.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GeneralSettingsRequestModel {

 private String $id;

 private String msg;

 private boolean Status;

 private String SettingID ;

 private String SettingValue ;

 private String SettingDescription ;

 @SerializedName("data")
 @Expose
 private ArrayList<GeneralSettingsRequestModel> data;

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

 public ArrayList<GeneralSettingsRequestModel> getData() {
  return data;
 }

 public void setData(ArrayList<GeneralSettingsRequestModel> data) {
  this.data = data;
 }

}
