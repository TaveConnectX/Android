package com.example.taveconnect.rank

class MyRankData : ArrayList<MyRankData.MyRankDataItem>(){
    data class MyRankDataItem(
        val defeat: Int,
        val draw: Int,
        val name: String,
        val picture: String,
        val ranking: Int,
        val victory: Int
    )
}