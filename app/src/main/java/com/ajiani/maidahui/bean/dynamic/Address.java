package com.ajiani.maidahui.bean.dynamic;

public class Address {
    private String title;
    private String address;
    private String id;
    private String lat;
    private String lon;

    public Address(String title, String address, String id, String lat, String lon) {
        this.title = title;
        this.address = address;
        this.id = id;
        this.lat = lat;
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return title+","+address+","+id+","+lat+","+lon;
    }
}
