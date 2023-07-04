package com.example.taveconnect.game

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface GameService {
    @POST("games")
    fun getGame(
        @Body gameInfo: GameData
    ): Call<GameResponse>
}

