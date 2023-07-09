package com.example.taveconnect.retrofit

import com.example.taveconnect.game.*
import com.example.taveconnect.login.ResponseLoginData
import com.example.taveconnect.rank.MyRankData
import com.example.taveconnect.rank.RankData
import com.example.taveconnect.rank.UsersRankData
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


    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("/games/results")
    fun getGameEnd(
        @Body gameEndDTO: GameEndDTO,
    ): Call<GameEndData>

    @GET("/games/review")
    fun getGameReview(
    ): Call<GameReviewData>



    @GET("/ranking")
    fun getMyRanking(
    ): Call<MyRankData>

    @GET("/ranking/users")
    fun getUsersRanking(
    ): Call<UsersRankData>


    @FormUrlEncoded
    @POST("/login")
    fun getLogin(
        @Field("accessToken") accessToken: String,
        @Field("refreshToken") refreshToken: String
    ): Call<ResponseLoginData>

    @GET("/api")
    fun getAPI(): Call<Void>
}