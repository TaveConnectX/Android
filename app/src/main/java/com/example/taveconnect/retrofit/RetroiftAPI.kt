package com.example.taveconnect.retrofit

import com.example.taveconnect.game.GameEndData
import com.example.taveconnect.game.GameTurnData
import com.example.taveconnect.rank.RankProfile
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RetroiftAPI {
    @POST("/games")
    fun getGameTurn(
    ): Call<GameTurnData>

    @POST("/games/results")
    fun getGameEnd(
    ): Call<GameEndData>

    @GET("/ranking")
    fun getRanking(
    ): Call<RankProfile>

    @GET("/login/{code}")
    fun getLogin(
        @Path("code")
        code: String
    )

    @GET("/api")
    fun getAPI(): Call<ModelAPI>
}