package com.vishnu.assignmenttwo.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vishnu.assignmenttwo.Models.SecondModel
import com.vishnu.assignmenttwo.R

class CustomAdapter (val listOfArray: ArrayList<SecondModel>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context)
            .inflate(R.layout.alldetails, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listOfArray.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewName.text=listOfArray[position].restaurant.name
        holder.typesOfFood.text=listOfArray[position].restaurant.cuisines
        holder.price.text="$"+listOfArray[position].restaurant.average_cost_for_two


        val base64String = listOfArray[position].restaurant.featured_image
       Picasso.get().load(base64String).into(holder.profileImage)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textViewName = itemView.findViewById(R.id.hotelName) as TextView
        var typesOfFood = itemView.findViewById(R.id.typesOfFood) as TextView
        var price = itemView.findViewById(R.id.price) as TextView
        var profileImage = itemView.findViewById(R.id.imageHotel) as ImageView

    }
}