package com.example.taveconnect.rank

class AllRankData : ArrayList<AllRankData.AllRankDataItem>(){
    data class AllRankDataItem(
        val defeat: Int,
        val draw: Int,
        val name: String,
        val picture: String,
        val ranking: Int,
        val victory: Int
    )
}