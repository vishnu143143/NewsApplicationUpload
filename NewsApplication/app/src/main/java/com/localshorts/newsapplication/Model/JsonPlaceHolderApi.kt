package com.localshorts.newsapplication.Model

import com.localshorts.newsapplication.Model.NewsApiModels.FirstModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface JsonPlaceHolderApi {
    @GET
    fun getUsers(@Url string: String):Call<FirstModel>

    @GET("")
    fun getUsersTwo(@Url string: String):Call<FirstModel>
}
