package com.example.taveconnect.rank

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MyRankData (

    @SerializedName("defeat")
    @Expose
    val defeat: Int,

    @SerializedName("draw")
    @Expose
    val draw: Int,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("picture")
    @Expose
    val picture: String,

    @SerializedName("ranking")
    @Expose
    val ranking: Int,

    @SerializedName("victory")
    @Expose
    val victory: Int


)
