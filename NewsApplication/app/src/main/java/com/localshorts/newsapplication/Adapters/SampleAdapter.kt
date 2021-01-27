package com.localshorts.newsapplication.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.localshorts.newsapplication.R

import android.widget.ImageView
import com.localshorts.newsapplication.Model.AdapterModel


class SampleAdapter(
    val links: ArrayList<String>,
    val linksText: ArrayList<AdapterModel>,
    val callbackInterface: CallbackInterface
) : RecyclerView.Adapter<SampleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleAdapter.ViewHolder {
        val v = LayoutInflater.from(parent?.context)
            .inflate(R.layout.video_playing_cardview, parent, false)
        return SampleAdapter.ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return links.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var actionNumber = 0
        val user = links[position]
        holder.channelName.text = linksText[position].name + "News"
        holder.channelSecondName.text = "Live Updates On" + linksText[position].name
        holder.channelImage.setImageResource(linksText[position].icon!!)
        holder.itemView.setOnClickListener {
            if (actionNumber == 0) {
                callbackInterface.passDataCallback(links[position], linksText[position].name!!, 1)
                actionNumber = 1
            } else {
                callbackInterface.passDataCallback(links[position], linksText[position].name!!, 0)
                actionNumber = 0
            }

        }

    }

    interface CallbackInterface {
        fun passDataCallback(message: String, key: String, actionNumber: Int)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var channelName = itemView.findViewById(R.id.channelName) as TextView;
        var channelSecondName = itemView.findViewById(R.id.channelSecondName) as TextView;
        var channelImage = itemView.findViewById(R.id.videoImageView) as ImageView
    }


}


