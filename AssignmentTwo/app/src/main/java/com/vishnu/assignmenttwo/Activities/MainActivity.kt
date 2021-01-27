package com.vishnu.assignmenttwo.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.vishnu.assignmenttwo.Adapters.CustomAdapter
import com.vishnu.assignmenttwo.Models.ApiClient
import com.vishnu.assignmenttwo.Models.DataModel
import com.vishnu.assignmenttwo.Models.SecondModel
import com.vishnu.assignmenttwo.R
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var arrayList: ArrayList<SecondModel>? = null
    var arrayListDummy: ArrayList<SecondModel>? = null
    var arrayListDummyTwo: ArrayList<SecondModel>? = ArrayList()
    var count = 0
    var User: DataModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getData()
        b1.setOnClickListener {
            var editTextHello = findViewById(R.id.searchBar) as TextInputEditText

            if (editTextHello.text.toString().equals("")) {
                Toast.makeText(this, "Not entered anything??", Toast.LENGTH_SHORT).show()

            } else {

                arrayListDummy = User!!.nearby_restaurants
                for (i in arrayListDummy!!) {
                    if (i.restaurant.name.equals(editTextHello.text.toString())) {
                        arrayListDummyTwo?.add(i)
                        Toast.makeText(this, "$arrayListDummyTwo", Toast.LENGTH_SHORT).show()

                    }
                }

                val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
                recyclerView.layoutManager = LinearLayoutManager(MainActivity())


                val adapterSecond = CustomAdapter(arrayListDummyTwo!!)
                recyclerView.adapter = adapterSecond
            }


        }
    }

    private fun getData() {

        val call: Call<DataModel> = ApiClient.getClient.hitData()
        call.enqueue(object : Callback<DataModel> {
            override fun onFailure(call: Call<DataModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
                recyclerView.layoutManager = LinearLayoutManager(MainActivity())


                User = response.body()!!
                Toast.makeText(this@MainActivity, "nearby_restaurants", Toast.LENGTH_LONG).show()
                arrayList = User!!.nearby_restaurants

                val adapter = CustomAdapter(arrayList!!)
                recyclerView.adapter = adapter
            }


        })
    }
}
