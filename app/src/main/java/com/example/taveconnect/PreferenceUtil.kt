package com.example.taveconnect

import android.content.Context
import android.content.SharedPreferences
import com.example.taveconnect.game.GameReviewData
import com.google.gson.Gson

class PreferenceUtil(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)




    fun getString(key: String, defValue: String): String {
        return prefs.getString(key, defValue).toString()
    }

    fun setString(key: String, str: String) {
        prefs.edit().putString(key, str).apply()
    }

    fun getInt(key: String, defInt: Int): Int {
        return prefs.getInt(key, defInt)
    }

    fun setInt(key: String, int: Int) {
        prefs.edit().putInt(key, int).apply()
    }



    // 함수를 호출하여 SharedPreferences에 데이터를 저장
    fun saveReviewData(context: Context, reviewData: List<GameReviewData.GameReviewDataItem>) {
        val sharedPreferences = context.getSharedPreferences("ReviewData", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(reviewData)
        editor.putString("reviewData", json)
        editor.apply()
    }

    // 함수를 호출하여 SharedPreferences에서 데이터를 가져옴
    fun getReviewData(context: Context): List<GameReviewData.GameReviewDataItem> {
        val sharedPreferences = context.getSharedPreferences("ReviewData", Context.MODE_PRIVATE)
        val json = sharedPreferences.getString("reviewData", null)
        val gson = Gson()
        return gson.fromJson(json, Array<GameReviewData.GameReviewDataItem>::class.java).toList()
    }


}
