package com.example.taveconnect.game

data class GameEndDTO(
    val difficulty: String?,
    val gameIdx: Int?,
    val list: Array<Array<Int>>?,
    val turn: Int?,
    val winner: Int?,
    val now: Int?
)