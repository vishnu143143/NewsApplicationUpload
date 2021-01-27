package com.example.webproject.Models.ItemsModelClass;

import com.google.gson.annotations.SerializedName;

public class ProductDetails {
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
