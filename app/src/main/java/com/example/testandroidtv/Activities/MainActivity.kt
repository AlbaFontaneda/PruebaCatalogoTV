package com.example.testandroidtv.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import com.example.testandroidtv.Adapters.CustomGridLayoutAdapter
import com.example.testandroidtv.Models.Channel
import com.example.testandroidtv.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val channels = ArrayList<Channel>()
        channels.add(Channel("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c8/Orange_logo.svg/1200px-Orange_logo.svg.png", "Orange"))
        channels.add(Channel("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c8/Orange_logo.svg/1200px-Orange_logo.svg.png", "Orange"))
        channels.add(Channel("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c8/Orange_logo.svg/1200px-Orange_logo.svg.png", "Orange"))
        channels.add(Channel("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c8/Orange_logo.svg/1200px-Orange_logo.svg.png", "Orange"))
        channels.add(Channel("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c8/Orange_logo.svg/1200px-Orange_logo.svg.png", "Orange"))
        channels.add(Channel("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c8/Orange_logo.svg/1200px-Orange_logo.svg.png", "Orange"))
        channels.add(Channel("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c8/Orange_logo.svg/1200px-Orange_logo.svg.png", "Orange"))

        val adapter = CustomGridLayoutAdapter(this@MainActivity, channels)
        gridlayout.adapter = adapter

        gridlayout.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this@MainActivity, "Click on : " + channels[position].name, Toast.LENGTH_LONG).show()
        }
        /*val facilities = mutableListOf("HOLA", "QUE", "TAL")

        val total = facilities.size
        val column = 2
        val row = total / column
        gridlayout.alignmentMode = GridLayout.LAYOUT_MODE_CLIP_BOUNDS
        gridlayout.columnCount = column
        gridlayout.rowCount = row + 1
        var titleText: TextView
        var i = 0
        var c = 0
        var r = 0
        while (i < total) {
            if (c == column) {
                c = 0
                r++
            }
            titleText = TextView(this)
            titleText.text = facilities[i]
            gridlayout.addView(titleText)
            titleText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
            val param = GridLayout.LayoutParams()
            param.height = GridLayout.LayoutParams.WRAP_CONTENT
            param.width = GridLayout.LayoutParams.WRAP_CONTENT
            param.rightMargin = 5
            param.topMargin = 5
            param.setGravity(Gravity.CENTER)
            param.columnSpec = GridLayout.spec(c)
            param.rowSpec = GridLayout.spec(r)
            titleText.layoutParams = param
            i++
            c++
        }*/
    }
}
