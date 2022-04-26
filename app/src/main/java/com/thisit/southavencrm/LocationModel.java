package com.thisit.southavencrm;

public class LocationModel {

    private String Location,address,telnumber,fexnumber;
    // Constructor
    public LocationModel(String Location, String address, String telnumber,String fexnumber) {
        this.Location = Location;
        this.address = address;
        this.telnumber = telnumber;
        this.fexnumber = fexnumber;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelnumber() {
        return telnumber;
    }

    public void setTelnumber(String telnumber) {
        this.telnumber = telnumber;
    }

    public String getFexnumber() {
        return fexnumber;
    }

    public void setFexnumber(String fexnumber) {
        this.fexnumber = fexnumber;
    }
}

