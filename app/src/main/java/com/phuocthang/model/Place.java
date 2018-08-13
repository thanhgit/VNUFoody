package com.phuocthang.model;

import java.io.Serializable;

/**
 * Created by THANH_UIT on 4/23/2017.
 */

public class Place implements Serializable{
    private long id;
    private int category_id;
    private String placeName;
    private double longitude;
    private double latitude;
    private String address;
    private String image_url;

    public Place(){}

    public Place(long id,int category_id, String placeName, double latitude, double longitude, String address, String image) {
        this.id = id;
        this.placeName = placeName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.image_url = image;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public Place(String placeName, int category_id, double latitude, double longitude, String address, String image) {
        this.id = 0;
        this.placeName = placeName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.image_url = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage_Url() {
        return image_url;
    }

    public void setImage_url(String image) {
        this.image_url = image;
    }

    @Override
    public String toString() {
        return this.placeName+" "+this.address;
    }
}
