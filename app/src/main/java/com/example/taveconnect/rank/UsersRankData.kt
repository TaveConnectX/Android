package com.example.taveconnect.rank

class UsersRankData : ArrayList<UsersRankData.UsersRankDataItem>(){
    data class UsersRankDataItem(
        val defeat: Int,
        val draw: Int,
        val name: String,
        val picture: String,
        val ranking: Int,
        val victory: Int
    )
}