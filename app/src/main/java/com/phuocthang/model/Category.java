package com.phuocthang.model;

import java.io.Serializable;

/**
 * Created by THANH_UIT on 4/25/2017.
 */

public class Category implements Serializable{
    private int id;
    private String categoryName;

    public Category(){}

    public Category(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
