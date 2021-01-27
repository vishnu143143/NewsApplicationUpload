package com.example.webproject.Models.CouponDisplayingModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CouponProductData {


    @SerializedName("product")
    @Expose
    ArrayList<CouponProductDetails> product;

    public ArrayList<CouponProductDetails> getProduct() {
        return product;
    }

    public void setProduct(ArrayList<CouponProductDetails> product) {
        this.product = product;
    }

}
