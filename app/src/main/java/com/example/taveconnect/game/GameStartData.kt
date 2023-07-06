package com.example.taveconnect.game

import com.example.taveconnect.retrofit.ResponseData
import com.google.gson.annotations.SerializedName

data class GameStartData(
    @SerializedName("Body")
    val body: List<StartModel> = emptyList()
) : ResponseData()

data class StartModel(
    @SerializedName("turn")
    val turn: Int,
    @SerializedName("difficulty")
    val difficulty: String = ""
)
