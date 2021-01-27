package com.localshorts.newsapplication.Activities

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ShareCompat
import androidx.viewpager2.widget.ViewPager2
import com.localshorts.newsapplication.Activities.Alerts.NotificationUtils
import com.localshorts.newsapplication.Activities.Alerts.Utils

import com.localshorts.newsapplication.Adapters.CustomAdapter
import com.localshorts.newsapplication.Adapters.Time.Time
import com.localshorts.newsapplication.Model.JsonPlaceHolderApi
import com.localshorts.newsapplication.Model.NewsApiModels.FirstModel
import com.localshorts.newsapplication.Model.NewsApiModels.ThirdModel
import com.localshorts.newsapplication.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.activity_second.bottomNavigationView
import kotlinx.android.synthetic.main.toolbar.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList


class SecondActivity : AppCompatActivity(), CustomAdapter.CallbackInterface,
    CustomAdapter.CallbackInterfaceTwo, CustomAdapter.CallbackInterfaceThree {
    var finalStringSec = ""
    var finalStringDay = ""
    var finalStringHour = ""
    var finalStringMints = ""

    override fun passDataCallbackFinal(actionNumber: String, title: String) {


        val shareIntent = ShareCompat.IntentBuilder
            .from(this)
            .setType("text/plain")
            .setChooserTitle("Share URL")
            .setText(title + "\n" + actionNumber)
            .intent

        if (shareIntent.resolveActivity(this.packageManager) != null) {
            this.startActivity(shareIntent)
        }
    }

    override fun passDataCallback(actionNumber: String) {
        var intent = Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(actionNumber));
        startActivity(intent);
    }

    override fun passDataCallback(actionNumber: Int, notifyString: String) {
        val toast =
            Toast.makeText(applicationContext, "Remind you in 5 minutes", Toast.LENGTH_LONG)
        toast.show()
        Utils.arrayList.add(notifyString)
        val SPLASH_TIME_OUT: Long = 3000
        val mNotificationTime =
            Calendar.getInstance().timeInMillis + 5000 * 30 //Set after 5 seconds from the current time.
        var mNotified = false
        if (!mNotified) {
            NotificationUtils().setNotification(mNotificationTime, this@SecondActivity)
        }


    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val progressBar = ProgressBar(this)
        //setting height and width of progressBar
        progressBar.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)

        //accessing our relative layout where the progressBar will add up
        val layout = findViewById<RelativeLayout>(R.id.layout)
        // Add ProgressBar to our layout
        layout?.addView(progressBar)
tool.toolBar.imageThree.setOnClickListener {
    var currentTimeTwo = Calendar.getInstance().getTime();
    var currentTimeString = currentTimeTwo.toString()
    var finalStringDayTwo = currentTimeString.substring(9,11)
     var finalStringHourTwo = currentTimeString.substring(8,10)
    var finalStringMintsTwo = currentTimeString.substring(14,16)
    var  finalStringSecTwo = currentTimeString.substring(17,19)
    val start = Time(Integer.parseInt(finalStringHourTwo), Integer.parseInt(finalStringMintsTwo), Integer.parseInt(finalStringSecTwo))
    val stop = Time(Integer.parseInt(finalStringHour), Integer.parseInt(finalStringMints), Integer.parseInt(finalStringSec))
    val diff: Time
    diff = difference(start, stop)
    if(diff.hours >= 1 || diff.seconds >= 30  )
    {
        val toast =
            Toast.makeText(applicationContext, "Refreshed News", Toast.LENGTH_LONG)
        toast.show()

        tool.toolBar.imageThree.setImageResource(R.drawable.ic_arrow_downward_black_24dp)
        val viewPager = findViewById(R.id.viewPager) as ViewPager2

        val intent = intent
        val name = intent.getStringExtra("Name")

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://newsapi.org/v2/")
            .build()
        val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)
        val myCall =
            jsonPlaceHolderApi.getUsers("https://newsapi.org/v2/top-headlines?country=in&category=health&apiKey=8546dc03f5584737a336775736452a52")

        if(isOnline(this) == true)
        {
            progressBar.visibility = View.INVISIBLE
            myCall.enqueue(object : Callback<FirstModel> {
                override fun onFailure(call: Call<FirstModel>, t: Throwable) {
                    Log.e("ERROR", t.message.toString())

                }

                override fun onResponse(call: Call<FirstModel>, response: Response<FirstModel>) {
                    val User: FirstModel = response.body()!!
                    val Users: ArrayList<ThirdModel> = User.articles
                    for(i in User.articles)
                    {
                        Utils.arrayListForRefreshTwo.add(i)
                    }
                     Utils.arrayListForRefreshTwo.addAll(Utils.arrayListForRefresh)

                    val stringBuilder = StringBuilder()
                    val adapter = CustomAdapter(
                        Utils.arrayListForRefreshTwo,
                        this@SecondActivity,
                        this@SecondActivity,
                        this@SecondActivity
                    )
                    viewPager.adapter = adapter
                    viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
                    viewPager.fakeDragBy(-10f)

//                    val adapter = CustomAdapter(Users)
//                    recyclerView.adapter = adapter




                }

            })

        }
        else{
            val timer = object: CountDownTimer(20000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    progressBar.visibility = View.VISIBLE
                }

                override fun onFinish() {
                    progressBar.visibility = View.INVISIBLE
                    noInternetRelativeLayout.visibility = View.VISIBLE
                }
            }
            timer.start()

        }


    }
    else
    {
        val toast =
            Toast.makeText(applicationContext, "Refreshed", Toast.LENGTH_LONG)
        toast.show()
    }
//    if((Integer.parseInt(finalStringMintsTwo) - Integer.parseInt(finalStringMints) > 5 && finalStringHourTwo == finalStringHour) ||
//        (Integer.parseInt(finalStringMintsTwo) - Integer.parseInt(finalStringMints) > 5 && finalStringHourTwo != finalStringHour) )

}



       // Mon Jul 13 17:02:29 GMT+05:30 2020
        var currentTime = Calendar.getInstance().getTime();
        var currentTimeString = currentTime.toString()
      finalStringDay = currentTimeString.substring(9,11)
        finalStringHour = currentTimeString.substring(8,10)
        finalStringMints = currentTimeString.substring(14,16)
        finalStringSec = currentTimeString.substring(17,19)

//        ////////////////////
        bottomNavigationView.setItemIconTintList(null);
        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val BVMenu = navView.getMenu()

        var wordtoSpan = SpannableString("News");
        wordtoSpan.setSpan(
            ForegroundColorSpan(Color.BLUE),
            0,
            4,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        bottomNavigationView.menu.findItem(R.id.newsImage).title = wordtoSpan
        BVMenu.findItem(R.id.newsImage).setIcon(R.drawable.ic_assignment_blacktwo_24dp);
        BVMenu.findItem(R.id.videoesAll).setIcon(R.drawable.ic_ondemand_video_black_24dp);
        BVMenu.findItem(R.id.locaNews).setIcon(R.drawable.ic_location_city_black_24dp);


        bottomNavigationView.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {

                R.id.videoesAll ->
                    call("videoesActivity")
                R.id.locaNews ->
                    call("mainActivity")
            }
            false
        }
        val viewPager = findViewById(R.id.viewPager) as ViewPager2

        val intent = intent
        val name = intent.getStringExtra("Name")

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://newsapi.org/v2/")
            .build()
        val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)
        val myCall =
            jsonPlaceHolderApi.getUsers("https://newsapi.org/v2/top-headlines?country=in&category=general&apiKey=8546dc03f5584737a336775736452a52")

       if(isOnline(this) == true)
       {
           progressBar.visibility = View.INVISIBLE
           myCall.enqueue(object : Callback<FirstModel> {
               override fun onFailure(call: Call<FirstModel>, t: Throwable) {
                   Log.e("ERROR", t.message.toString())

               }

               override fun onResponse(call: Call<FirstModel>, response: Response<FirstModel>) {
                   val User: FirstModel = response.body()!!
                   val Users: ArrayList<ThirdModel> = User.articles
                   for(i in User.articles)
                   {
                       Utils.arrayListForRefresh.add(i)
                   }

                   val stringBuilder = StringBuilder()
                   val adapter = CustomAdapter(
                       Users,
                       this@SecondActivity,
                       this@SecondActivity,
                       this@SecondActivity
                   )
                   viewPager.adapter = adapter
                   viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
                   viewPager.fakeDragBy(-10f)

//                    val adapter = CustomAdapter(Users)@TargetApi(Build.VERSION_CODES.O)
//                    recyclerView.adapter = adapter




               }

           })

       }
        else{
           val timer = object: CountDownTimer(20000, 1000) {
               override fun onTick(millisUntilFinished: Long) {
                    progressBar.visibility = View.VISIBLE
               }

               override fun onFinish() {
                   progressBar.visibility = View.INVISIBLE
                  noInternetRelativeLayout.visibility = View.VISIBLE
               }
           }
           timer.start()

       }


    }



    fun call(name: String) {
        if (name.equals("videoesActivity")) {
            val intent = Intent(this@SecondActivity, SampleVideoPlayingActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this@SecondActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

    fun difference(start: Time, stop: Time): Time {
        val diff = Time(0, 0, 0)

        if (stop.seconds > start.seconds) {
            --start.minutes
            start.seconds += 60
        }

        diff.seconds = start.seconds - stop.seconds
        if (stop.minutes > start.minutes) {
            --start.hours
            start.minutes += 60
        }

        diff.minutes = start.minutes - stop.minutes
        diff.hours = start.hours - stop.hours

        return diff
    }



}
