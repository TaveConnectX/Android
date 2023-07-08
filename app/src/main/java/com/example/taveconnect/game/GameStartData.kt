package com.example.taveconnect.game

data class GameStartData(
    val difficulty: String,
    val gameIdx: Int,
    val list: Array<IntArray>,
    val turn: Int
)