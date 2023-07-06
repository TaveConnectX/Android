package com.example.taveconnect.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.net.URL

data class ResponseLoginData(
    @SerializedName("name")
    @Expose
    val name : String,

    @SerializedName("profile")
    @Expose
    val profile : String
)
