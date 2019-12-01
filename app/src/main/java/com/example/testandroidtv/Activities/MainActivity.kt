package com.example.testandroidtv.Activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import com.example.testandroidtv.Adapters.CustomGridLayoutAdapter
import com.example.testandroidtv.Models.Channel
import com.example.testandroidtv.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

enum class Filter {
    FILMS, CHANNELS
}

class MainActivity : AppCompatActivity() {
    var channels = mutableListOf<Channel>()
    var films = mutableListOf<Channel>()
    var isLoading: ObservableBoolean = ObservableBoolean(false)
    private var currentState = Filter.CHANNELS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val callback = object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(observable: Observable, i: Int) {
                if (!isLoading.get())
                    FormatGridView(channels)
                else {
                    //Toast.makeText(this@MainActivity, "Loading", Toast.LENGTH_LONG).show()
                }
            }
        }

        isLoading.addOnPropertyChangedCallback(callback)
        LLfilter.setOnClickListener {
            FilterList()
        }

        GetChannels()
    }

    fun GetChannels() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("response")

        isLoading.set(true)

        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach {
                    try {
                        val response: HashMap<*, *> = it.value as HashMap<*, *>
                        val name = response["name"] as String
                        val category= response["category"] as String
                        var logoUrl = ""
                        val attachments : List<HashMap<*, *>> = response["attachments"] as  List<HashMap<*, *>>

                        if (attachments.isNotEmpty()) {
                            logoUrl = attachments.first()["value"] as String
                        }

                        channels.add(Channel(logoUrl,name, category))
                    } catch (e: Exception) {
                        Log.e("Firebase", e.message)
                    }


                }
                isLoading.set(false)

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("Firebase", databaseError.message) //Don't ignore errors!
            }
        }

        myRef.addValueEventListener(valueEventListener)
    }


    fun FormatGridView(list: MutableList<Channel>) {
        val adapter = CustomGridLayoutAdapter(this@MainActivity, list)
        gridlayout.adapter = adapter
        LLload.visibility = View.GONE
        gridlayout.visibility = View.VISIBLE
        LLfilter.visibility = View.VISIBLE

        gridlayout.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(this@MainActivity, "Click on : " + list[position].name, Toast.LENGTH_LONG).show()
        }
    }

    fun FilterList() {
        LLload.visibility = View.VISIBLE
        gridlayout.visibility = View.GONE
        LLfilter.visibility = View.GONE

        if (currentState == Filter.CHANNELS) {
            films = channels.filter { p -> p.category == "Cine" }.toMutableList()
            FormatGridView(films)
            TVfilter.text = getText(R.string.filter_channels)
            currentState = Filter.FILMS
        }
        else {
            FormatGridView(channels)
            TVfilter.text = getText(R.string.filter_film)
            currentState = Filter.CHANNELS
        }
    }
}
