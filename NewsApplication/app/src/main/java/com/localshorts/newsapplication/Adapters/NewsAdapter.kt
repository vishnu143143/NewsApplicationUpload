package com.localshorts.newsapplication.Adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.localshorts.newsapplication.Model.AdapterModel
import com.localshorts.newsapplication.R


class NewsAdapter(var context: Context,var array: ArrayList<AdapterModel>):BaseAdapter() {
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view = View.inflate(context, R.layout.card_view_item_grid,null)
        var icons:ImageView= view.findViewById(R.id.cardImageView)
        var names:TextView= view.findViewById(R.id.cardTextView)
        var list :AdapterModel = array.get(p0)


        icons.setImageResource(list.icon!!)

        names.text = list.name
   return view
    }

    override fun getItem(p0: Int): Any {
    return  array.get(p0)
    }

    override fun getItemId(p0: Int): Long {
return p0.toLong()
    }

    override fun getCount(): Int {
return array.size
    }


}