package com.example.taveconnect

import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.Locale.Category

interface RetroiftAPI {
    @POST("/games")
    fun getGame(
        @Query("now") now: Int,
        @Query("turn") turn: Int,
        @Query("gameIdx") gameIdx: Int
    ): Call<RetroiftAPI>
}