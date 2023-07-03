package com.example.taveconnect

import android.util.Log
import retrofit2.Call
import retrofit2.Response

class RetrofitWork(private val gameInfo: GameData) {
    fun work() {
        val service = RetrofitClient.gameService

        service.getGame(gameInfo)
            .enqueue(object : retrofit2.Callback<GameResponse> {
                override fun onResponse(
                    call: Call<GameResponse>,
                    response: Response<GameResponse>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        Log.d("통신 성공", "$result")
                    }
                }

                override fun onFailure(call: Call<GameResponse>, t: Throwable) {
                    Log.d("통신 실패", t.message.toString())
                }
            })

    }
}