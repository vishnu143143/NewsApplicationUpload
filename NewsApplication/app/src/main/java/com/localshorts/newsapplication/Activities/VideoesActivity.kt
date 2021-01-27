package com.localshorts.newsapplication.Activities

import android.os.Bundle
import com.localshorts.newsapplication.R
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_videoes.*
//AIzaSyB-sfCSYE9FFm7HOVKPuFAVDTaXztkQXuw
class VideoesActivity :  YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener{

    var name =" "
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videoes)
        val intent = intent
        val link = intent.getStringExtra("Message")
name = link.toString()
        yt_pv.initialize("AIzaSyB-sfCSYE9FFm7HOVKPuFAVDTaXztkQXuw", this)
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {

    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        if (!p2) {
            p1?.cueVideo(name);
        }

    }
//    fun call(name:String){
//             //   webView.webChromeClient( WebChromeClient())
//           webView.settings.javaScriptEnabled = true
//
//        webView.webViewClient = object : WebViewClient() {
//            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//                view?.loadUrl(url)
//                return true
//            }
//        }
// webView.loadUrl(name)
//    }
}
