package com.vishnu.assignmenttwo.Interfaces

import com.vishnu.assignmenttwo.Models.DataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface InterfaceApi {
    @Headers("Accept: application/json" ,"user-key: 1b3c8b37ea96785391fa55c288ac385c")
    @GET("geocode?lat=13.0368&lon=80.2675")
    fun hitData(): Call<DataModel>
}