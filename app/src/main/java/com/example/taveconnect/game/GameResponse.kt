package com.example.taveconnect.game

import com.google.gson.annotations.SerializedName

data class GameResponse(
    @SerializedName("result")
    val result: String?,
    @SerializedName("status")
    val status: String?
)
