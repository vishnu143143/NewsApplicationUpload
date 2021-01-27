package com.localshorts.newsapplication.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.localshorts.newsapplication.R

class VideoesAlert( val names: ArrayList<String> ): RecyclerView.Adapter<VideoesAlert.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context)
            .inflate(R.layout.state_names, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
return names.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
holder.textViewName.text = names[position]
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textViewName = itemView.findViewById(R.id.stateName) as TextView

    }

}
