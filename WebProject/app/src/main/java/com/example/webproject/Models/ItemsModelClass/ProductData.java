package com.example.webproject.Models.ItemsModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductData {

    @SerializedName("product")
    @Expose
    ArrayList<ProductDetails>product;

    public ArrayList<ProductDetails> getProduct() {
        return product;
    }

    public void setProduct(ArrayList<ProductDetails> product) {
        this.product = product;
    }


}
