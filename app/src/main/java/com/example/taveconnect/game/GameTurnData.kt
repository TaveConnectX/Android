package com.example.taveconnect.game

data class GameTurnData(
    val difficulty: String,
    val gameIdx: Int,
    val list: Array<Array<Int>>,
    val turn: Int,
    val now: Int
)