package com.example.taveconnect.game

data class GameEndData(
    val difficulty: String,
    val gameIdx: Int,
    val list: List<List<Int>>,
    val turn: Int,
    val winner: Int
)