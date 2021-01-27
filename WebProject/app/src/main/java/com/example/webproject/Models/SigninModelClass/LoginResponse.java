package com.example.webproject.Models.SigninModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("messages")
    @Expose
    private String messages;
    @SerializedName("is_success")
    @Expose
   private  boolean is_success;
   @SerializedName("data")
   @Expose
   private Data data;


    public LoginResponse(String messages, boolean is_success, Data data) {
        this.messages = messages;
        this.is_success = is_success;
        this.data = data;
    }

    public String getMessages() {
        return messages;
    }

    public boolean isIs_success() {
        return is_success;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "messages='" + messages + '\'' +
                ", is_success=" + is_success +
                ", data=" + data +
                '}';
    }

    public Data getData() {
        return data;
    }


}
