package com.localshorts.newsapplication.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.localshorts.newsapplication.Model.NewsApiModels.ThirdModel
import com.localshorts.newsapplication.R

import com.squareup.picasso.Picasso


class CustomAdapter(
    val userList: ArrayList<ThirdModel>,
    val callbackInterface: CustomAdapter.CallbackInterface,val callbackInterfaceTwo: CustomAdapter.CallbackInterfaceTwo,val callbackInterfaceThree: CustomAdapter.CallbackInterfaceThree
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var actionNumber = 0
        val user = userList[position]
        holder?.textViewName.text = user.title
        holder.textViewAddress.text = user.description
        holder.shareImage.setOnClickListener {
            callbackInterfaceThree.passDataCallbackFinal(user.url.toString(),user.title.toString())
        }

        holder.textLink.setOnClickListener {
            callbackInterfaceTwo.passDataCallback(user.url.toString())
//            val browserIntent = Intent(Intent.ACTION_VIEW)
//            browserIntent.data = Uri.parse("http://www.google.com")
//            startActivity(browserIntent)
        }
        val base64String = user.urlToImage
        Picasso.get().load(base64String).into(holder.imageSlider)
        holder.alertImage.setOnClickListener {
            callbackInterface.passDataCallback(1,user.title.toString())
//            if (actionNumber == 0) {
//                callbackInterface.passDataCallback(1)
//                actionNumber = 1
//            } else {
//                callbackInterface.passDataCallback(0)
//                actionNumber = 0
//            }
        }


    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName = itemView.findViewById(R.id.textViewOne) as TextView
        val textViewAddress = itemView.findViewById(R.id.textViewTwo) as TextView
        var imageSlider = itemView.findViewById(R.id.slidingImage) as ImageView
        var textLink = itemView.findViewById(R.id.linkText) as TextView
        var shareImage = itemView.findViewById(R.id.shareImage) as ImageView
        var alertImage = itemView.findViewById(R.id.notificationImageTrigger) as ImageView
    }

    interface CallbackInterface {
        fun passDataCallback(actionNumber: Int,notifyString :String)
    }
    interface CallbackInterfaceTwo {
        fun passDataCallback(actionNumber: String)
    }
    interface CallbackInterfaceThree {
        fun passDataCallbackFinal(actionNumber: String,title:String)
    }
}
