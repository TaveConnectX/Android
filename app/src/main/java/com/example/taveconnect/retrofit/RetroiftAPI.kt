package com.example.taveconnect.retrofit

import com.example.taveconnect.game.GameEndData
import com.example.taveconnect.game.GameStartData
import com.example.taveconnect.game.GameTurnDTO
import com.example.taveconnect.game.GameTurnData
import com.example.taveconnect.login.ResponseLoginData
import com.example.taveconnect.rank.RankData
import retrofit2.Call
import retrofit2.http.*

interface RetroiftAPI {

    // 게임 시작 API
    @GET("/games")
    fun getGameStart(
        @Query("difficulty") difficulty: String
    ): Call<GameStartData>



    // 게임 turn API
    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("/games")
    fun getGameTurn(
        @Body gameTurnDTO: GameTurnDTO
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