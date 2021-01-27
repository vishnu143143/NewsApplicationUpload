package com.localshorts.newsapplication.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.localshorts.newsapplication.R

class SpinnerAdapter(val context: Context, var listItemsTxt: Array<String>, var listItemImages: Array<Int>) : BaseAdapter() {
    val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view: View
        val vh: ItemRowHolder
        if (p1 == null) {
            view = mInflater.inflate(R.layout.view_dropdown_menu, p2, false)
            vh = ItemRowHolder(view)
            view?.tag = vh
        } else {
            view = p1
            vh = view.tag as ItemRowHolder
        }

        // setting adapter item height programatically.

        val params = view.layoutParams
        params.height = 120
        view.layoutParams = params

        vh.label.text = listItemsTxt.get(p0)
        vh.imageIconSpinner.setImageResource(listItemImages.get(p0))

        return view

    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return listItemsTxt.size
    }

}

private class ItemRowHolder(row: View?) {

    val label: TextView
    val imageIconSpinner:ImageView

    init {
        this.label = row?.findViewById(R.id.txtDropDownLabel) as TextView
        this.imageIconSpinner = row?.findViewById(R.id.imgDropDownMenuIcon) as ImageView
    }
}