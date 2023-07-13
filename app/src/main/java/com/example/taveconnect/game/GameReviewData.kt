package com.example.taveconnect.game

class GameReviewData : ArrayList<GameReviewData.GameReviewDataItem>(){
    data class GameReviewDataItem(
        val list: Array<Array<Int>>,
        val recommendation: Int,
        val turn: Int,
        val first: Int
    )
}