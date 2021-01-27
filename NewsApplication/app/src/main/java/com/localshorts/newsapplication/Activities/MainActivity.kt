package com.localshorts.newsapplication.Activities

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.graphics.Color
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import com.localshorts.newsapplication.Adapters.NewsAdapter
import com.localshorts.newsapplication.Model.AdapterModel
import com.localshorts.newsapplication.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

import androidx.annotation.RequiresApi
import androidx.core.app.ShareCompat
import androidx.viewpager2.widget.ViewPager2
import com.localshorts.newsapplication.Adapters.CustomAdapter
import com.localshorts.newsapplication.Adapters.SpinnerAdapter
import com.localshorts.newsapplication.Model.JsonPlaceHolderApi
import com.localshorts.newsapplication.Model.NewsApiModels.FirstModel
import com.localshorts.newsapplication.Model.NewsApiModels.ThirdModel
import kotlinx.android.synthetic.main.activity_main.bottomNavigationView
import kotlinx.android.synthetic.main.activity_main.viewPager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.collections.ArrayList


//https://www.naukri.com/step-up-list-of-companies-hiring-during-coronavirus-covid-19
//https://www.naukri.com/job-listings-android-developer-anynews-hyderabad-secunderabad-1-to-3-years-240620007424?src=sortby&sid=15932539140482048_10&xp=13&px=1
//https://www.naukri.com/job-listings-trainee-fresher-android-developer-jardens-capability-developers-hyderabad-secunderabad-telangana-0-to-1-years-260620901489?src=jobsearchDesk&sid=15932530442546613_6&xp=3&px=1

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener ,AdapterView.OnItemSelectedListener,CustomAdapter.CallbackInterface,CustomAdapter.CallbackInterfaceTwo,CustomAdapter.CallbackInterfaceThree{
    override fun passDataCallbackFinal(actionNumber: String, title: String) {
        val shareIntent = ShareCompat.IntentBuilder
            .from(this)
            .setType("text/plain")
            .setChooserTitle("Share URL")
            .setText(title+"\n"+actionNumber)
            .intent

        if (shareIntent.resolveActivity(this.packageManager) != null) {
            this.startActivity(shareIntent)
        }
    }

    override fun passDataCallback(actionNumber: String) {
        var intent =  Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(actionNumber));
        startActivity(intent);    }

    override fun passDataCallback(actionNumber: Int,notifyString :String) {
     //
        //   Toast.makeText(this,"yes ViewPager",Toast.LENGTH_SHORT).show()
    }
    private var arrayList:ArrayList<AdapterModel>? = null

    private var newsAdapterClass:NewsAdapter ? = null


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  gridView = findViewById(R.id.gridView)
        setContentView(R.layout.activity_main)


        var wordtoSpan = SpannableString("Local News");
        wordtoSpan.setSpan( ForegroundColorSpan(Color.BLUE), 0, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val BVMenu = navView.getMenu()
        bottomNavigationView.setItemIconTintList(null);
        BVMenu.findItem(R.id.newsImage).setIcon(R.drawable.ic_assignment_black_24dp);
        BVMenu.findItem(R.id.videoesAll).setIcon(R.drawable.ic_ondemand_video_black_24dp);
        BVMenu.findItem(R.id.locaNews).setIcon(R.drawable.ic_location_city_blacktwo_24dp);
        bottomNavigationView.menu.findItem(R.id.locaNews).title = wordtoSpan


        val bottomNavigationView = findViewById<View>(R.id.bottomNavigationView) as BottomNavigationView
        bottomNavigationView.visibility = View.VISIBLE


        val listItemsTxt = arrayOf("India", "AndhraPradesh","Arunachal Pradesh","Assam","Bihar","Chhattisgarh","Goa","Gujarat","Haryana","Himachal Pradesh","Jammu and Kashmir","Jharkhand","Karnataka","Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Odisha","Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana","Tripura")

        val listItemsImages = arrayOf(R.drawable.india,R.drawable.andhrapradesh, R.drawable.arunachal,R.drawable.assamimage,R.drawable.bihar,R.drawable.chhattisgarh,R.drawable.goaimm,R.drawable.gujarath,R.drawable.haryana,R.drawable.himachal,R.drawable.jammu,R.drawable.jharkhand,R.drawable.karnataka,R.drawable.kerala,R.drawable.madhyapradesh,R.drawable.maharastra,R.drawable.mainipur,R.drawable.meghalayaimm,R.drawable.mizoram,R.drawable.nagaland,R.drawable.odisha,R.drawable.punjab,R.drawable.rajasthan,R.drawable.sikkim,R.drawable.tamilnadu,R.drawable.telanganaimm,R.drawable.tripuraimm)

        var spinnerAdapter: SpinnerAdapter = SpinnerAdapter(applicationContext, listItemsTxt,listItemsImages)
        var spinner: Spinner = findViewById(R.id.spinnerTariffCalculator) as Spinner
        spinner?.adapter = spinnerAdapter

        spinner.onItemSelectedListener = this



        bottomNavigationView.setOnNavigationItemSelectedListener {item ->
            when (item.itemId)
            {
                R.id.videoesAll ->
                    call("videoesActivity")

                R.id.newsImage ->
                    call("secondActivity")
            }
            false
        }

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
     if(p2 == 0)
     {
         val yesterday = LocalDate.now().minusDays(1)
         var yesterdayDate = yesterday.toString()
      //   Toast.makeText(this,yesterday.toString(),Toast.LENGTH_LONG).show()
         gridView!!.visibility = View.VISIBLE
         viewPager!!.visibility = View.INVISIBLE
         arrayList = ArrayList()

         arrayList = setData()

         newsAdapterClass = NewsAdapter(applicationContext, arrayList!!)
         gridView?.adapter = newsAdapterClass
         gridView?.onItemClickListener = this
     }
        else if (p2 == 1){
         dataFromUrl("Andhra News")
     }
     else if (p2 == 2){
         dataFromUrl("Arunachal Pradesh News")
     }
     else if (p2 == 3){
         dataFromUrl("Assam News")
     }
     else if (p2 == 4){
         dataFromUrl("Bihar News")
     }
     else if (p2 == 5){
         dataFromUrl("Chhattisgarh News")
     }
     else if (p2 == 6){
         dataFromUrl("Goa News")
     }
     else if (p2 == 7){
         dataFromUrl("Gujarat News")
     }
     else if (p2 == 8){
         dataFromUrl("Haryana News")
     }
     else if (p2 == 9){
         dataFromUrl("Himachal Pradesh News")
     }
     else if (p2 == 10){
         dataFromUrl("Jammu and Kashmir News")
     }
     else if (p2 == 11){
         dataFromUrl("Jharkhand News")
     }
     else if (p2 == 12){
         dataFromUrl("Karnataka News")
     }
     else if (p2 == 13){
         dataFromUrl("Kerala News")
     }
     else if (p2 == 14){
         dataFromUrl("Madhya Pradesh News")
     }
     else if (p2 == 15){
         dataFromUrl("Maharashtra News")
     }
     else if (p2 == 16){
         dataFromUrl("Manipur News")
     }
     else if (p2 == 17){
         dataFromUrl("Meghalaya News")
     }
     else if (p2 == 18){
         dataFromUrl("Mizoram News")
     }
     else if (p2 == 19){
         dataFromUrl("Nagaland News")
     }
     else if (p2 == 20){
         dataFromUrl("Odisha News")
     }
     else if (p2 == 21){
         dataFromUrl("Punjab News")
     }
     else if (p2 == 22){
         dataFromUrl("Rajasthan News")
     }
     else if (p2 == 23){
         dataFromUrl("Sikkim News")
     }
     else if (p2 == 24){
         dataFromUrl("Tamil Nadu News")
     }
     else if (p2 == 25){
         dataFromUrl("Telangana News")
     }
     else {
         dataFromUrl("Tripura News")
     }

    }
    @TargetApi(Build.VERSION_CODES.O)
    fun dataFromUrl(state : String)
    {

        var baseUrl = "https://newsapi.org/v2/"
        // Toast.makeText(this,yesterday.toString(),Toast.LENGTH_LONG).show()
        var str = " "
        var stateName = state

        val yesterday = LocalDate.now().minusDays(1)
        var yesterdayDate = yesterday.toString()
        val current = LocalDateTime.now()

        val currentTime = current.toString()


        for (i in 0 until currentTime.length - 4) {
            str = str + currentTime[i]
        }
        var tailUrl = "everything?q=" + stateName + "&from=" + yesterdayDate + "&to=" + str + "&sortBy=popularity&apiKey=8546dc03f5584737a336775736452a52"

        gridView!!.visibility = View.INVISIBLE
        viewPager!!.visibility = View.VISIBLE
        val viewPager = findViewById(R.id.viewPager) as ViewPager2

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://newsapi.org/v2/")
            .build()
        val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)
        val myCall = jsonPlaceHolderApi.getUsers(baseUrl+tailUrl)
        myCall.enqueue(object: Callback<FirstModel> {
            override fun onFailure(call: Call<FirstModel>, t: Throwable) {
                Log.e("ERROR",t.message.toString())

            }

            override fun onResponse(call: Call<FirstModel>, response: Response<FirstModel>) {
              //  val toast = Toast.makeText(applicationContext, "Hello Javatpoint", Toast.LENGTH_LONG)
              //  toast.show()
                val User: FirstModel = response.body()!!
                val  Users:ArrayList<ThirdModel> = User.articles
                val stringBuilder = StringBuilder()
                val adapter = CustomAdapter(Users,this@MainActivity,this@MainActivity,this@MainActivity)
                viewPager.adapter = adapter
                viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
                viewPager.fakeDragBy(-10f)

//                    val adapter = CustomAdapter(Users)
//                    recyclerView.adapter = adapter

            }

        })

    }


    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        var itemsSelected = arrayList!!.get(p2)
        if(itemsSelected.name.equals("Sports"))
        {
            callSportsData("sports")
        }
        else if(itemsSelected.name.equals("Business"))
        {
            callSportsData("business")
        }
        else if(itemsSelected.name.equals("Entertainment"))
        {
callSportsData("entertainment")
        }
        else if(itemsSelected.name.equals("Education"))
        {
            dataFromUrl("education")
        }
        else  if (itemsSelected.name.equals("Politics"))
        {
            dataFromUrl("politics")
        }
        else  if (itemsSelected.name.equals("Science"))
        {
            callSportsData("science")
        }
        else{
          dataFromUrl("international")
        }
//        val intent = Intent(this@MainActivity, Main2Activity::class.java)
//        intent.putExtra("Name", itemsSelected.name)
//
//        startActivity(intent)

    }
    fun callSportsData(name : String)
    {
//        gridView!!.visibility = View.INVISIBLE
//        viewPager!!.visibility = View.VISIBLE
//        val viewPager = findViewById(R.id.viewPager) as ViewPager2
//
//        var baseUrl = "https://newsapi.org/v2/"
//        var tailUrl = "top-headlines?country=in&category="+name+"&apiKey=8546dc03f5584737a336775736452a52"
//       // http://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=8546dc03f5584737a336775736452a52
//        val retrofit = Retrofit.Builder()
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .baseUrl("http://newsapi.org/v2/")
//                    .build()
//                val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)
//                val myCall = jsonPlaceHolderApi.getUsers(baseUrl+tailUrl)
//                myCall.enqueue(object: Callback<FirstModel> {
//                    override fun onFailure(call: Call<FirstModel>, t: Throwable) {
//                        Log.e("ERROR",t.message.toString())
//
//                    }
//
//                    override fun onResponse(call: Call<FirstModel>, response: Response<FirstModel>) {
//                        val toast = Toast.makeText(applicationContext, "Hello Javatpoint", Toast.LENGTH_LONG)
//                        toast.show()
//                        val User: FirstModel = response.body()!!
//                        val  Users:ArrayList<ThirdModel> = User.articles
//                        val stringBuilder = StringBuilder()
//                        val adapter = CustomAdapter(Users,this@MainActivity,this@MainActivity,this@MainActivity)
//                        viewPager.adapter = adapter
//                        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
//                        viewPager.fakeDragBy(-10f)
//
//        //                    val adapter = CustomAdapter(Users)
//        //                    recyclerView.adapter = adapter
//
//                    }
//
//                })
        gridView!!.visibility = View.INVISIBLE
        viewPager!!.visibility = View.VISIBLE
        val viewPager = findViewById(R.id.viewPager) as ViewPager2

        var baseUrl = "https://newsapi.org/v2/"
        var tailUrl = "top-headlines?country=in&category="+name+"&apiKey=8546dc03f5584737a336775736452a52"
        // http://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=8546dc03f5584737a336775736452a52
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://newsapi.org/v2/")
            .build()
        val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)
        val myCall = jsonPlaceHolderApi.getUsers(baseUrl+tailUrl)
        myCall.enqueue(object: Callback<FirstModel> {
            override fun onFailure(call: Call<FirstModel>, t: Throwable) {
                Log.e("ERROR",t.message.toString())

            }

            override fun onResponse(call: Call<FirstModel>, response: Response<FirstModel>) {
//                val toast = Toast.makeText(applicationContext, "Hello Javatpoint", Toast.LENGTH_LONG)
//                toast.show()
                val User: FirstModel = response.body()!!
                val  Users:ArrayList<ThirdModel> = User.articles
                val stringBuilder = StringBuilder()
                val adapter = CustomAdapter(Users,this@MainActivity,this@MainActivity,this@MainActivity)
                viewPager.adapter = adapter
                viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
                viewPager.fakeDragBy(-10f)

                //                    val adapter = CustomAdapter(Users)
                //                    recyclerView.adapter = adapter

            }

        })
    }

    fun call(name : String)
    {
        if(name.equals("videoesActivity"))
        {
            val intent = Intent(this@MainActivity, SampleVideoPlayingActivity::class.java)
            intent.setFlags(FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish();
        }
        else{
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            intent.setFlags(FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }

    }

    private fun setData():ArrayList<AdapterModel>{
        var arrayList:ArrayList<AdapterModel> = ArrayList()
        arrayList?.add(AdapterModel(R.drawable.sports,"Sports"))
        arrayList?.add(AdapterModel(R.drawable.business,"Business"))
        arrayList?.add(AdapterModel(R.drawable.entertainmnet,"Entertainment"))
        arrayList?.add(AdapterModel(R.drawable.education,"Education"))
        arrayList?.add(AdapterModel(R.drawable.politics,"Politics"))
        arrayList?.add(AdapterModel(R.drawable.science,"Science"))
        arrayList?.add(AdapterModel(R.drawable.international,"International"))
        return arrayList
    }

//@RequiresApi(Build.VERSION_CODES.O)
//fun callDate()
//{
////    val current = LocalDateTime.now()
////
////    val currentTime = current.toString()
////
////    for (i in 0 until currentTime.length - 4) {
////        str = str + currentTime[i]
////    }
//    var yesterdayYear = ""
//    var yesterdayMonth = ""
//    var yesterdayDay = ""
//
//    var currentYear = 2018
//    var currentMonth = 1
//    var currentDate = 1
//    if (currentDate > currentDate-1 && currentMonth >= currentMonth-1)
//    {
//        if(currentDate - 1 != 0  && currentMonth - 1 != 0)
//        {
//            if(currentMonth-1 != 0)
//            {
//                yesterdayYear = currentYear.toString()
//                yesterdayMonth = currentMonth.toString()
//                yesterdayDay = (currentDate-1).toString()
//
//                Toast.makeText(this,yesterdayDay+"-"+yesterdayMonth+""+yesterdayYear,Toast.LENGTH_LONG).show()
//            }
//            else{
//                yesterdayYear = currentYear.toString()
//                yesterdayMonth = currentMonth.toString()
//                yesterdayDay = (currentDate-1).toString()
//            }
//
//        }
//
//        else{
//            yesterdayYear = currentYear.toString()
//            yesterdayMonth = (currentMonth).toString()
//            yesterdayDay = (currentDate-1).toString()
//            Toast.makeText(this,yesterdayDay+"-"+yesterdayMonth+""+yesterdayYear,Toast.LENGTH_LONG).show()
//        }
//
//      //  System.out.println(String.valueOf(currentDate-1)+"-"+String.valueOf(currentMonth)+"-"+String.valueOf());
//
//    }
//    else if(currentDate<currentDate-1 && currentMonth >= currentMonth-1 )
//    {
//
//        yesterdayYear = currentYear.toString()
//        yesterdayMonth = (currentMonth-1).toString()
//        yesterdayDay = (30).toString()
//        Toast.makeText(this,yesterdayDay+"-"+yesterdayMonth+"+"+yesterdayYear,Toast.LENGTH_LONG).show()
//
//        //  System.out.println(String.valueOf(30)+"-"+String.valueOf(currentMonth-1)+"-"+String.valueOf(currentYear));
//
//    }
//    else if(currentMonth<currentMonth-1){
//        yesterdayYear = (currentYear - 1).toString()
//        yesterdayMonth = (12).toString()
//        yesterdayDay = (31).toString()
//        Toast.makeText(this,yesterdayDay+"-"+yesterdayMonth+"+"+yesterdayYear,Toast.LENGTH_LONG).show()
//
//     //   System.out.println(String.valueOf(31)+"-"+String.valueOf(12)+"-"+String.valueOf(currentYear-1));
//    }
//    else {
//        System.out.println("wrong assumption");
//    }
//
//}

}
