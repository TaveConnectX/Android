package com.example.taveconnect.retrofit

import com.example.taveconnect.game.GameEndData
import com.example.taveconnect.game.GameStartData
import com.example.taveconnect.game.GameTurnData
import com.example.taveconnect.login.ResponseLoginData
import com.example.taveconnect.login.oAuthToken
import com.example.taveconnect.rank.RankData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface RetroiftAPI {

    // 게임 시작 API
    @GET
    fun getGameStart(
        @Header("Authorization") authorization: String,
        @Query("difficulty") difficulty: String
    ): Call<GameStartData>



    // 게임 turn API
    @POST("/games")
    fun getGameTurn(
        @Header("Authorization") authorization: String
    ): Call<GameTurnData>

    @POST("/games/results")
    fun getGameEnd(
    ): Call<GameEndData>

    @GET("/ranking")
    fun getRanking(
    ): Call<RankData>


    @FormUrlEncoded
    @POST("/login")
    fun getLogin(
        @Field("accessToken") accessToken: String,
        @Field("refreshToken") refreshToken: String
    ): Call<ResponseLoginData>

    @GET("/api")
    fun getAPI(): Call<Void>
}