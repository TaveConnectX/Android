package com.example.taveconnect.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetroiftAPI {
    @POST("/games")
    fun getGame(
        @Query("now") now: Int,
        @Query("turn") turn: Int,
        @Query("gameIdx") gameIdx: Int
    ): Call<RetroiftAPI>

    @GET("/api")
    fun getAPI(): Call<ModelAPI>
}