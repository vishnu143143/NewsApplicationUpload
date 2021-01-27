package com.localshorts.newsapplication.Activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.localshorts.newsapplication.Model.JsonPlaceHolderApi
import com.localshorts.newsapplication.Model.NewsApiModels.FirstModel
import com.localshorts.newsapplication.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime

class Main2Activity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        call()
        //    everything?q=business&from=2020-07-04T01:35:02&to=2020-07-04T01:35:02&sortBy=popularity&apiKey=8546dc03f5584737a336775736452a52
        //      https://newsapi.org/v2/everything?q=telugu&from=2020-06-30&to=2020-06-30&sortBy=popularity&apiKey=8546dc03f5584737a336775736452a52
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun call() {
        var str: String = ""
        val intent = intent
        val name = intent.getStringExtra("Name")

        val current = LocalDateTime.now()

        val currentTime = current.toString()


        for (i in 0 until currentTime.length - 4) {
            str = str + currentTime[i]
        }
        "https://newsapi.org/v2/everything?q=" + name + "&from=" + str + "&to=" + str + "&sortBy=popularity&apiKey=8546dc03f5584737a336775736452a52"

        var tailUrl =
            "everything?q=" + name + "&from=" + str + "&to=" + str + "&sortBy=popularity&apiKey=8546dc03f5584737a336775736452a52"

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://newsapi.org/v2/")
            .build()
        val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)
        val myCall = jsonPlaceHolderApi.getUsersTwo("https://newsapi.org/v2/" + tailUrl)
        myCall.enqueue(object : Callback<FirstModel> {
            override fun onFailure(call: Call<FirstModel>, t: Throwable) {
                Log.e("ERROR", t.message.toString())

            }
            // https://newsapi.org/v2/everything?q=telugu&from=2020-06-30&to=2020-06-30&sortBy=popularity&apiKey=8546dc03f5584737a336775736452a52

            //  https://newsapi.org/v2/everything?q=business&from=2020-07-03&to=2020-07-04&sortBy=popularity&apiKey=8546dc03f5584737a336775736452a52
            override fun onResponse(call: Call<FirstModel>, response: Response<FirstModel>) {
                val toast =
                    Toast.makeText(applicationContext, "Hello Javatpoint", Toast.LENGTH_LONG)
                toast.show()
//                val User: FirstModel = response.body()!!
//                val  Users:ArrayList<ThirdModel> = User.articles
//                val stringBuilder = StringBuilder()
//                val adapter = CustomAdapter(Users,this@Main2Activity)
//                viewPager.adapter = adapter
//                viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
//                viewPager.fakeDragBy(-10f)

//                    val adapter = CustomAdapter(Users)
//                    recyclerView.adapter = adapter

            }

        })
    }
}
