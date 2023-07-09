package com.example.taveconnect.game

data class GameTurnDTO(
    val difficulty: String?,
    val gameIdx: Int?,
    val list: Array<Array<Int>>,
    val turn: Int?
)