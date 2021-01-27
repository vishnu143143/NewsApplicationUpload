package com.example.webproject.Models.CouponDisplayingModelClass;

import com.google.gson.annotations.SerializedName;

public class CouponProductDetails {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;

    public int getId() {

        return id;
    }

    public String getName() {
        return name;
    }
}
