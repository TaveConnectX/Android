package com.example.taveconnect.game

data class GameRestartData(
    val difficulty: String,
    val gameIdx: Int,
    val isNextTurn: Int,
    val list: Array<Array<Int>>,
    val totalTurnCount: Int
)