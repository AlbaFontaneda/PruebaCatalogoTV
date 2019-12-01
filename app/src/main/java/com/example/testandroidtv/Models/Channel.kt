package com.example.testandroidtv.Models

import com.example.testandroidtv.Support.Constants

data class Channel(var logo: String, var name: String, var category: String) {
    init {
        logo = Constants.baseURL + this.logo
    }
}