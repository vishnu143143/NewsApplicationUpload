package com.localshorts.newsapplication.Activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.localshorts.newsapplication.Adapters.SampleAdapter
import com.localshorts.newsapplication.Model.AdapterModel
import com.localshorts.newsapplication.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_sample_video_playing.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*

class SampleVideoPlayingActivity : AppCompatActivity(),SampleAdapter.CallbackInterface{
    override fun passDataCallback(message: String, key: String,action : Int) {
        val intent = Intent(this@SampleVideoPlayingActivity, VideoesActivity::class.java)
        intent.putExtra("Message", message)
        startActivity(intent)
    }

    private var arrayList:ArrayList<AdapterModel>? = null
    private var arrayListTwo:ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_video_playing)

        val recyclerView = findViewById(R.id.recycleview) as RecyclerView
        arrayList = ArrayList<AdapterModel>()
        arrayListTwo = ArrayList<String>()
//      val recyclerView = findViewById(R.id.recyclerview) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        arrayList?.add(AdapterModel(R.drawable.skynews,"Sky News live"))
        arrayList?.add(AdapterModel(R.drawable.cnn,"CNN News18 Live "))
        arrayList?.add(AdapterModel(R.drawable.indiatoday,"India Today Live TV"))
        arrayList?.add(AdapterModel(R.drawable.repub,"Republic TV Live"))
        arrayList?.add(AdapterModel(R.drawable.newsx,"NewsX"))
        arrayListTwo?.add("9Auq9mYxFEE")
        arrayListTwo?.add("VUhPwLtqByA")
        arrayListTwo?.add("3k_JUUCv9rI")
        arrayListTwo?.add("ckKA-0MUm4E")
        arrayListTwo?.add("O7FCpHFtfZI")


        val adapter = SampleAdapter(arrayListTwo!!,arrayList!!,this)
        recyclerView.adapter = adapter


        bottomNavigationViewVideoes.setItemIconTintList(null);
        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationViewVideoes)
        val BVMenu = navView.getMenu()

        var wordtoSpan = SpannableString("Videoes");
        wordtoSpan.setSpan(
            ForegroundColorSpan(Color.BLUE),
            0,
            7,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        bottomNavigationViewVideoes.menu.findItem(R.id.videoesAll).title = wordtoSpan
        BVMenu.findItem(R.id.newsImage).setIcon(R.drawable.ic_assignment_black_24dp);
        BVMenu.findItem(R.id.videoesAll).setIcon(R.drawable.ic_ondemand_video_blacktwo_24dp);
        BVMenu.findItem(R.id.locaNews).setIcon(R.drawable.ic_location_city_black_24dp);


        bottomNavigationViewVideoes.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {

                R.id.newsImage ->
                    callActivities("secondActivity")
                R.id.locaNews ->
                    callActivities("mainActivity")
            }
            false
        }


        toolBar.imageThree.setImageResource(R.drawable.ic_language_black_24dp)

        ///////////////////////////
        toolBar.imageThree.setOnClickListener {
            callAlert()

        }
    }
    fun callAlert()
    {
        val array = arrayOf("Hindi News","Gujarati news","Bengali news","Kannada news","Malayalam news","Marathi news","Odia news","Punjabi news","Tamil news","Telugu news")

        val builder = AlertDialog.Builder(this)

        // Set a title for alert dialog
        builder.setTitle("Choose Local State")

        builder.setItems(array,{_, which ->

            val selected = array[which]
if(selected.equals("Hindi News"))
{
    call("Hindi News Channel")
}
            else if(selected.equals("Gujarati news"))
{
    call("Gujarati news channels")
}
else if(selected.equals("Bengali news"))
{
    call("Bengali news channels")
}
else if(selected.equals("Kannada news"))
{
    call("Kannada news channels")
}

else if(selected.equals("Malayalam news"))
{
    call("Malayalam news channels")
}

else if(selected.equals("Marathi news"))
{
    call("Marathi news channels")
}
else if(selected.equals("Odia news"))
{
    call("Odia news channels")
}
else if(selected.equals("Punjabi news"))
{
    call("Punjabi news channels")
}
else if(selected.equals("Tamil news"))
{
    call("Tamil news channels")
}

else if(selected.equals("Telugu news"))
{
    call("Telugu news channels")
}

        })

        // Create a new AlertDialog using builder object
        val dialog = builder.create()

        // Finally, display the alert dialog
        dialog.show()

    }
    fun call(stateName : String)
    {
        if(stateName.equals("Hindi News Channel"))
        {
            val recyclerView = findViewById(R.id.recycleview) as RecyclerView
            arrayList = ArrayList<AdapterModel>()
            arrayListTwo = ArrayList<String>()
//        val recyclerView = findViewById(R.id.recyclerview) as RecyclerView
            recyclerView.layoutManager = LinearLayoutManager(this)
            arrayList?.add(AdapterModel(R.drawable.aonenews,"A1 News Live"))
            arrayList?.add(AdapterModel(R.drawable.aajthak,"AajTak LIVE"))
            arrayList?.add(AdapterModel(R.drawable.abpnews,"ABP News Live"))
            arrayList?.add(AdapterModel(R.drawable.aawaj,"Awaaz LIVE"))
            arrayList?.add(AdapterModel(R.drawable.dd,"DD News live"))
            arrayList?.add(AdapterModel(R.drawable.aajthak,"Aajtak LIVE TV"))
            arrayList?.add(AdapterModel(R.drawable.indiatv,"IndiaTV LIVE"))
            arrayList?.add(AdapterModel(R.drawable.jan,"Jan TV"))
            arrayList?.add(AdapterModel(R.drawable.janata,"Janta TV"))
            arrayList?.add(AdapterModel(R.drawable.loksabha,"LOKSABHA TV "))
            arrayList?.add(AdapterModel(R.drawable.ndtv,"NDTV India"))
            arrayList?.add(AdapterModel(R.drawable.newsnation,"News Nation"))
            arrayList?.add(AdapterModel(R.drawable.newsetimage,"News18 India"))
            arrayList?.add(AdapterModel(R.drawable.rajsabha,"Rajya Sabha TV"))
            arrayList?.add(AdapterModel(R.drawable.republic,"Republic Bharat"))
            arrayList?.add(AdapterModel(R.drawable.tez,"Tez TV"))
            arrayList?.add(AdapterModel(R.drawable.total,"TOTAL TV"))
            arrayList?.add(AdapterModel(R.drawable.zeebusiness,"Zee Business"))

            arrayListTwo?.add("vJADvQ-2pC4")
            arrayListTwo?.add("0w_fM3ub5nA")
            arrayListTwo?.add("ufeGM9_Jn7M")
            arrayListTwo?.add("VN_qAz4P0qI")
            arrayListTwo?.add("ncwSddEygPs")
            arrayListTwo?.add("0w_fM3ub5nA")
            arrayListTwo?.add("F6riDx4XxHI")
            arrayListTwo?.add("vcQ7nGRD3bU")
            arrayListTwo?.add("lFVn19_8TQs")
            arrayListTwo?.add("zUrcQymAaFo")
            arrayListTwo?.add("l9ViEIip9q4")
            arrayListTwo?.add("QC1cs-EKOJM")
            arrayListTwo?.add("doWOTOWBcG8")
            arrayListTwo?.add("imF0lYxJ7KA")
            arrayListTwo?.add("wmbkEmiTbCI")
            arrayListTwo?.add("ZUcpps1dkj0")
            arrayListTwo?.add("s80_OJDHOR4")
            arrayListTwo?.add("NcWtG1IgJzc")

            val adapter = SampleAdapter(arrayListTwo!!,arrayList!!,this)
            recyclerView.adapter = adapter
        }
        else if(stateName.equals("English news channels"))
        {
            val recyclerView = findViewById(R.id.recycleview) as RecyclerView
            arrayList = ArrayList<AdapterModel>()
            arrayListTwo = ArrayList<String>()
//        val recyclerView = findViewById(R.id.recyclerview) as RecyclerView
            recyclerView.layoutManager = LinearLayoutManager(this)


            arrayListTwo?.add("dp8PhLsUcFE")
            arrayListTwo?.add("VUhPwLtqByA")
            arrayListTwo?.add("3k_JUUCv9rI")
            arrayListTwo?.add("AFNUeUed8Ro")
            arrayListTwo?.add("fcb3xyvtwyY")


            val adapter = SampleAdapter(arrayListTwo!!,arrayList!!,this)
            recyclerView.adapter = adapter
        }


        else if(stateName.equals("Gujarati news channels"))
        {
            val recyclerView = findViewById(R.id.recycleview) as RecyclerView
            arrayList = ArrayList<AdapterModel>()
            arrayListTwo = ArrayList<String>()
//        val recyclerView = findViewById(R.id.recyclerview) as RecyclerView
            recyclerView.layoutManager = LinearLayoutManager(this)
            arrayList?.add(AdapterModel(R.drawable.gujaratitvnine,"TV9 Gujarati"))
            arrayList?.add(AdapterModel(R.drawable.asmitha,"ABP Asmita "))
            arrayList?.add(AdapterModel(R.drawable.sandesh,"Sandesh News"))


            arrayListTwo?.add("tkUvWJiTf9A")
            arrayListTwo?.add("54r0kya_o7o")
            arrayListTwo?.add("2c7rYVdhjV8")

            val adapter = SampleAdapter(arrayListTwo!!,arrayList!!,this)
            recyclerView.adapter = adapter
        }


        else if(stateName.equals("Bengali news channels"))
        {
            val recyclerView = findViewById(R.id.recycleview) as RecyclerView
            arrayList = ArrayList<AdapterModel>()
            arrayListTwo = ArrayList<String>()
//        val recyclerView = findViewById(R.id.recyclerview) as RecyclerView
            recyclerView.layoutManager = LinearLayoutManager(this)
            arrayList?.add(AdapterModel(R.drawable.abpanand,"ABP Ananda"))
            arrayList?.add(AdapterModel(R.drawable.dinrat,"Channel Dinraat"))
            arrayList?.add(AdapterModel(R.drawable.kolkatta,"Kolkata TV"))
            arrayList?.add(AdapterModel(R.drawable.newsetbangla,"News18 Bangla"))
            arrayList?.add(AdapterModel(R.drawable.vanguard,"News Vanguard"))

            arrayList?.add(AdapterModel(R.drawable.abpanand,"ABP Ananda News Live TV"))
            arrayList?.add(AdapterModel(R.drawable.newsetbangla,"News18 Bangla"))
            arrayList?.add(AdapterModel(R.drawable.sadhan,"Sadhna News"))
            arrayListTwo?.add("1r2w-b5laYo")
            arrayListTwo?.add("F_WFn4_hadA")
            arrayListTwo?.add("qMeakZw2t_4")
            arrayListTwo?.add("uhwXEUk7VOo")
            arrayListTwo?.add("4Bh0iIcOVC8")


            arrayListTwo?.add("qMeakZw2t_4")
            arrayListTwo?.add("1r2w-b5laYo")
            arrayListTwo?.add("KSu6xFSmu74")


            val adapter = SampleAdapter(arrayListTwo!!,arrayList!!,this)
            recyclerView.adapter = adapter
        }

        else if(stateName.equals("Kannada news channels"))
        {
            val recyclerView = findViewById(R.id.recycleview) as RecyclerView
            arrayList = ArrayList<AdapterModel>()
            arrayListTwo = ArrayList<String>()
//        val recyclerView = findViewById(R.id.recyclerview) as RecyclerView
            recyclerView.layoutManager = LinearLayoutManager(this)
            arrayList?.add(AdapterModel(R.drawable.tvfivekanada,"TV5 Kannada"))
            arrayList?.add(AdapterModel(R.drawable.kannadatvnine,"TV9 Kannada"))

            arrayListTwo?.add("AuxO5ykchpc")
            arrayListTwo?.add("jdJoOhqCipA")



            val adapter = SampleAdapter(arrayListTwo!!,arrayList!!,this)
            recyclerView.adapter = adapter
        }
        else if(stateName.equals("Malayalam news channels"))
        {
            val recyclerView = findViewById(R.id.recycleview) as RecyclerView
            arrayList = ArrayList<AdapterModel>()
            arrayListTwo = ArrayList<String>()
//        val recyclerView = findViewById(R.id.recyclerview) as RecyclerView
            recyclerView.layoutManager = LinearLayoutManager(this)
            arrayList?.add(AdapterModel(R.drawable.twentyfournews,"24 News Live TV"))
            arrayList?.add(AdapterModel(R.drawable.asianet,"Asianet News"))
            arrayList?.add(AdapterModel(R.drawable.reporter,"Reporter TV"))
            arrayList?.add(AdapterModel(R.drawable.jaihind,"Jaihind TV "))
            arrayList?.add(AdapterModel(R.drawable.janam,"Janam TV"))

            arrayList?.add(AdapterModel(R.drawable.manorama,"Manorama News"))
            arrayList?.add(AdapterModel(R.drawable.mathrubhumi,"Mathrubhumi News"))
            arrayList?.add(AdapterModel(R.drawable.mediaone,"MediaOne TV"))


            arrayList?.add(AdapterModel(R.drawable.newskerala,"News 18 Kerala"))



            arrayListTwo?.add("zcrUCvBD16k")
            arrayListTwo?.add("iL53Y28Rp84")
            arrayListTwo?.add("7dH66p8TYvE")
            arrayListTwo?.add("o9PEjPQq0XA")
            arrayListTwo?.add("_WK30gnY3_4")


            arrayListTwo?.add("jjH6v95z3Nw")
            arrayListTwo?.add("irF-4N_fHjs")
            arrayListTwo?.add("d1iwUB9YFnA")


            arrayListTwo?.add("sZlceO3VoeU")


            val adapter = SampleAdapter(arrayListTwo!!,arrayList!!,this)
            recyclerView.adapter = adapter
        }

        else if(stateName.equals("Marathi news channels"))
        {
            val recyclerView = findViewById(R.id.recycleview) as RecyclerView
            arrayList = ArrayList<AdapterModel>()
            arrayListTwo = ArrayList<String>()
//        val recyclerView = findViewById(R.id.recyclerview) as RecyclerView
            recyclerView.layoutManager = LinearLayoutManager(this)
            arrayList?.add(AdapterModel(R.drawable.twentyfourmarati,"24 News Live TV"))
            arrayList?.add(AdapterModel(R.drawable.lkmata,"News18 Lokmat"))
            arrayList?.add(AdapterModel(R.drawable.jaimaharastra,"Jai Maharashtra News Live"))
            arrayList?.add(AdapterModel(R.drawable.tvninemarati,"TV9 Marathi Live "))
            arrayList?.add(AdapterModel(R.drawable.saam,"Saam TV"))


            arrayListTwo?.add("YY0_jk5xm3w")
            arrayListTwo?.add("wMkhGml_9KY")
            arrayListTwo?.add("tQ_D-ls0nzo")
            arrayListTwo?.add("fraxH3rvU84")
            arrayListTwo?.add("Qen2UUhu_2I")



            val adapter = SampleAdapter(arrayListTwo!!,arrayList!!,this)
            recyclerView.adapter = adapter
        }


        else if(stateName.equals("Odia news channels"))
        {
            val recyclerView = findViewById(R.id.recycleview) as RecyclerView
            arrayList = ArrayList<AdapterModel>()
            arrayListTwo = ArrayList<String>()
//        val recyclerView = findViewById(R.id.recyclerview) as RecyclerView
            recyclerView.layoutManager = LinearLayoutManager(this)
            arrayList?.add(AdapterModel(R.drawable.kanak,"Kanak News "))
            arrayList?.add(AdapterModel(R.drawable.newsodia,"News18 Odia"))

            arrayListTwo?.add("oQFufR_vV5E")
            arrayListTwo?.add("j6gdt7w8oYw")

            val adapter = SampleAdapter(arrayListTwo!!,arrayList!!,this)
            recyclerView.adapter = adapter
        }

        else if(stateName.equals("Punjabi news channels"))
        {
            val recyclerView = findViewById(R.id.recycleview) as RecyclerView
            arrayList = ArrayList<AdapterModel>()
            arrayListTwo = ArrayList<String>()
//        val recyclerView = findViewById(R.id.recyclerview) as RecyclerView
            recyclerView.layoutManager = LinearLayoutManager(this)
            arrayList?.add(AdapterModel(R.drawable.pu,"ABP Sanjha"))
            arrayList?.add(AdapterModel(R.drawable.punjabharyana,"Punjab Haryana &Himachal LIVE"))
            arrayListTwo?.add("BtenRwisLFM")
            arrayListTwo?.add("PiGGaplWi2M")
            val adapter = SampleAdapter(arrayListTwo!!,arrayList!!,this)
            recyclerView.adapter = adapter
        }

        else if(stateName.equals("Tamil news channels"))
        {
            val recyclerView = findViewById(R.id.recycleview) as RecyclerView
            arrayList = ArrayList<AdapterModel>()
            arrayListTwo = ArrayList<String>()
//        val recyclerView = findViewById(R.id.recyclerview) as RecyclerView
            recyclerView.layoutManager = LinearLayoutManager(this)
            arrayList?.add(AdapterModel(R.drawable.captain,"Captain News"))
            arrayList?.add(AdapterModel(R.drawable.jayaplus,"Jaya Plus"))
            arrayList?.add(AdapterModel(R.drawable.newsettamil,"News18 Tamil Nadu"))
            arrayList?.add(AdapterModel(R.drawable.polimer,"Polimer News "))
            arrayList?.add(AdapterModel(R.drawable.puthi,"Puthiya Thalaimurai TV"))
            arrayList?.add(AdapterModel(R.drawable.kaliyagnar,"Kalaignar News"))
            arrayList?.add(AdapterModel(R.drawable.sun,"Sun News "))


            arrayListTwo?.add("fzKQ_A6xrdg")
            arrayListTwo?.add("D7A09L8NPtw")
            arrayListTwo?.add("KQ7a5mZ9z0Y")
            arrayListTwo?.add("Vq-9xWMiiVQ")
            arrayListTwo?.add("ZFqOkIZSFSM")
            arrayListTwo?.add("x6lClQLXkXE")
            arrayListTwo?.add("BDRkp-3fXfs")

            val adapter = SampleAdapter(arrayListTwo!!,arrayList!!,this)
            recyclerView.adapter = adapter
        }
        else if(stateName.equals("Telugu news channels"))
        {
            val recyclerView = findViewById(R.id.recycleview) as RecyclerView
            arrayList = ArrayList<AdapterModel>()
            arrayListTwo = ArrayList<String>()
//        val recyclerView = findViewById(R.id.recyclerview) as RecyclerView
            recyclerView.layoutManager = LinearLayoutManager(this)
            arrayList?.add(AdapterModel(R.drawable.tentv,"10tv live"))
            arrayList?.add(AdapterModel(R.drawable.ninenine,"99TV Telugu Live"))
            arrayList?.add(AdapterModel(R.drawable.abn,"ABN Andhra Jyothi"))
            arrayList?.add(AdapterModel(R.drawable.etv,"ETV Andhra Pradesh live"))
            arrayList?.add(AdapterModel(R.drawable.hmtvfina,"HMTV live"))
             arrayList?.add(AdapterModel(R.drawable.ntv,"NTV live"))
            arrayList?.add(AdapterModel(R.drawable.prime,"Prime9 News"))
            arrayList?.add(AdapterModel(R.drawable.sakshifinal,"Sakshi TV live"))
            arrayList?.add(AdapterModel(R.drawable.t,"T News live"))
            arrayList?.add(AdapterModel(R.drawable.tvfive,"TV5 live"))
            arrayList?.add(AdapterModel(R.drawable.tvnine,"TV9 Telugu"))
            arrayList?.add(AdapterModel(R.drawable.vsix,"V6 News"))

            arrayListTwo?.add("BqQPXNvg1II")
            arrayListTwo?.add("QN_yOb2cA4w")
            arrayListTwo?.add("OoS46Vel8fg")
            arrayListTwo?.add("mhqryxPtdpg")
            arrayListTwo?.add("ErZT6EPy_gI")
            arrayListTwo?.add("rlrASnaZRRE")
            arrayListTwo?.add("kGCxTRNzx0I")
            arrayListTwo?.add("8McTsOqeueE")
            arrayListTwo?.add("ifXbDYDfqss")


            arrayListTwo?.add("NezGUhHsl_8")
            arrayListTwo?.add("Q6QR4979KIQ")

            arrayListTwo?.add("bl9VaUaI0r0")
            val adapter = SampleAdapter(arrayListTwo!!,arrayList!!,this)
            recyclerView.adapter = adapter
        }


    }



    fun callActivities(name: String) {
        if (name.equals("secondActivity")) {
            val intent = Intent(this@SampleVideoPlayingActivity, SecondActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        } else {
            val intent = Intent(this@SampleVideoPlayingActivity, MainActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            startActivity(intent)
        }

    }
}
