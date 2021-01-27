package com.example.webproject.Apipackage;

import com.example.webproject.Models.PriceDisplayingClass.Cost;
import com.example.webproject.Models.CouponDisplayingModelClass.CouponProduct;

import com.example.webproject.Models.ReviewDisplay.Review;
import com.example.webproject.Models.SigninModelClass.LoginResponse;
import com.example.webproject.Models.ItemsModelClass.Product;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface Api {
//SIGNUP

    @FormUrlEncoded
    @POST("sign_up")
    Call<ResponseBody> signUp(@FieldMap Map<String,Object> map);

//    @FormUrlEncoded
//    @GET("sign_in")
//    Call<LoginResponse>userSignin(@FieldMap Map<String,Object>map);
//


//SIGNIN

    @FormUrlEncoded
    @POST("sign_in")
    Call<LoginResponse>userSignin(
            @Field("user[email]") String email,
            @Field("user[password]") String password

    );

//DISPLAYING PRODUCTS

    @GET("products")
    Call<Product>getData();



    @GET
    Call<ResponseBody> coupon( @Url String url);


    @GET
    Call<CouponProduct>getCouponData(@Url String url);


    @FormUrlEncoded
    @POST("buy")
    Call<Cost> postingId(@FieldMap Map<String,Object> map);






    @POST
    Call<ResponseBody> hittingReview( @Url String url);

    @FormUrlEncoded
    @POST
    Call<ResponseBody> hittingRating( @Url String url,@FieldMap Map<String,String> map);


    //ReviewDisplay

    @GET
    Call<Review>getReview(@Url String url);

}
