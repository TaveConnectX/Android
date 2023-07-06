package com.example.taveconnect.login

import com.google.gson.annotations.SerializedName

data class oAuthToken (
    @SerializedName("accessToken")
    var accessToken: String,

    @SerializedName("refreshToken")
    var refreshToken: String

)