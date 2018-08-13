package com.phuocthang.model;

import java.io.Serializable;

/**
 * Created by THANH_UIT on 4/25/2017.
 */

public class Rating implements Serializable{
    private long id;
    private long place_id;
    private int rating_delicious;
    private int rating_cheap;

    public Rating(){}

    public Rating(long id, long place_id, int rating_delicious, int rating_cheap) {
        this.id = id;
        this.place_id = place_id;
        this.rating_delicious = rating_delicious;
        this.rating_cheap = rating_cheap;
    }

    public Rating(long place_id, int rating_delicious, int rating_cheap) {
        this.place_id = place_id;
        this.rating_delicious = rating_delicious;
        this.rating_cheap = rating_cheap;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPlace_id() {
        return place_id;
    }

    public void setPlace_id(long place_id) {
        this.place_id = place_id;
    }

    public int getRating_delicious() {
        return rating_delicious;
    }

    public void setRating_delicious(int rating_delicious) {
        this.rating_delicious = rating_delicious;
    }

    public int getRating_cheap() {
        return rating_cheap;
    }

    public void setRating_cheap(int rating_cheap) {
        this.rating_cheap = rating_cheap;
    }
}
