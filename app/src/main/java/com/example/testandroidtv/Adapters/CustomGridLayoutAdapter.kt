package com.example.testandroidtv.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.testandroidtv.Models.Channel
import com.example.testandroidtv.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.catalog_item.view.*


class CustomGridLayoutAdapter(private var context: Context, private var channels: MutableList<Channel>) : BaseAdapter() {


    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val mInflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = mInflator.inflate(R.layout.catalog_item, null)

        view.TVchannel.text = channels[position].name

        Picasso.with(context)
            .load(channels[position].logo)
            .error(R.drawable.orange_tv)
            .into(view.IVchannel!!)

        return view
    }

    override fun getItem(position: Int): Any {
        return channels[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return channels.size
    }



}