package com.example.taveconnect.game

class GameReviewData : ArrayList<GameReviewData.GameReviewDataItem>(){
    data class GameReviewDataItem(
        val list: List<List<Int>>,
        val recommendation: Int,
        val turn: Int,
        val first: Int
    )
}