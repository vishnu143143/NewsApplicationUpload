package com.example.webproject.Models.ReviewDisplay;

import com.example.webproject.Models.SigninModelClass.Data;

public class Review {
    String message;
    boolean is_success;
    ReviewData data;

    public String getMessage() {
        return message;
    }

    public boolean isIs_success() {
        return is_success;
    }

    public ReviewData getData() {
        return data;
    }
}
