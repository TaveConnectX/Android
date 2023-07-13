package com.example.taveconnect

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.taveconnect.databinding.ActivityReviewBinding
import com.example.taveconnect.game.GameReviewData
import com.example.taveconnect.retrofit.RetrofitClient
import com.example.taveconnect.retrofit.RetroiftAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReviewBinding
    private lateinit var imageViews: List<ImageView> // 이미지뷰들을 저장할 리스트 변수
    private var arr = Array(7) { IntArray(6) }
    private var recommendationList: MutableList<Int> = mutableListOf()

    private var first: Int = 1
    private var nowIndex: Int = 0
    private var nowTurn: Int = 0
    private lateinit var col1: IntArray
    private lateinit var col2: IntArray
    private lateinit var col3: IntArray
    private lateinit var col4: IntArray
    private lateinit var col5: IntArray
    private lateinit var col6: IntArray
    private lateinit var col7: IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        col1 = intent.getIntArrayExtra("r_col1") ?: IntArray(6)
        col2 = intent.getIntArrayExtra("r_col2") ?: IntArray(6)
        col3 = intent.getIntArrayExtra("r_col3") ?: IntArray(6)
        col4 = intent.getIntArrayExtra("r_col4") ?: IntArray(6)
        col5 = intent.getIntArrayExtra("r_col5") ?: IntArray(6)
        col6 = intent.getIntArrayExtra("r_col6") ?: IntArray(6)
        col7 = intent.getIntArrayExtra("r_col7") ?: IntArray(6)

        val gameIndex = intent.getIntExtra("reIndex", 0)
        Log.d("지금 인덱스", gameIndex.toString())

        nowIndex = gameIndex
        gameReviewAPI { maxTurnData ->
            if (maxTurnData.list.size >= 7) {
                nowTurn = maxTurnData.turn
                Log.d("지금 턴", nowTurn.toString())
                val col1Row = maxTurnData.list[0]
                val col2Row = maxTurnData.list[1]
                val col3Row = maxTurnData.list[2]
                val col4Row = maxTurnData.list[3]
                val col5Row = maxTurnData.list[4]
                val col6Row = maxTurnData.list[5]
                val col7Row = maxTurnData.list[6]

                col1Row.indices.forEach { index ->
                    col1[index] = col1Row[index]
                }
                col2Row.indices.forEach { index ->
                    col2[index] = col2Row[index]
                }
                col3Row.indices.forEach { index ->
                    col3[index] = col3Row[index]
                }
                col4Row.indices.forEach { index ->
                    col4[index] = col4Row[index]
                }
                col5Row.indices.forEach { index ->
                    col5[index] = col5Row[index]
                }
                col6Row.indices.forEach { index ->
                    col6[index] = col6Row[index]
                }
                col7Row.indices.forEach { index ->
                    col7[index] = col7Row[index]
                }
            }

            // col1부터 col7까지의 값이 변경되었음
            Log.d("초기 복기", "col1: ${col1.contentToString()}")
            Log.d("초기 복기", "col2: ${col2.contentToString()}")
            Log.d("초기 복기", "col3: ${col3.contentToString()}")
            Log.d("초기 복기", "col4: ${col4.contentToString()}")
            Log.d("초기 복기", "col5: ${col5.contentToString()}")
            Log.d("초기 복기", "col6: ${col6.contentToString()}")
            Log.d("초기 복기", "col7: ${col7.contentToString()}")

            // 초기 화면 설정
            setImageViews(col1, 1)
            setImageViews(col2, 2)
            setImageViews(col3, 3)
            setImageViews(col4, 4)
            setImageViews(col5, 5)
            setImageViews(col6, 6)
            setImageViews(col7, 7)
        }

        if (nowIndex == gameIndex && nowIndex == 0)
            Toast.makeText(this, "이전 게임이 없습니다.", Toast.LENGTH_SHORT).show()

        // Use the received data as needed
        Log.d("Review_GameActivity", "Index = $gameIndex")
        Log.d("GameActivity", "현재 3열: ${col3[1]}")

        binding.btnHint.isVisible = false

        first = GlobalApplication.prefs.getInt("first", 0)
        Log.d("GameActivity", "$first")


        // 1열부터 7열까지의 ImageView를 설정하는 부분
        for (colIndex in 1..7) {
            val col = intent.getIntArrayExtra("r_col$colIndex") ?: IntArray(6)
            for (rowIndex in col.indices) {
                val coord = "iv_gm_$colIndex" + "_${rowIndex + 1}"
                val packageName = packageName
                val ivId = resources.getIdentifier(coord, "id", packageName)
                val imageView = findViewById<ImageView>(ivId)
                if (first == 1) {
                    if (col[rowIndex] % 2 == 0 && col[rowIndex] != 0) {
                        imageView.setImageResource(R.drawable.ic_white)
                    } else if (col[rowIndex] % 2 == 1) {
                        imageView.setImageResource(R.drawable.ic_black)
                    }
                } else if (first == 2) {
                    if (col[rowIndex] % 2 == 0 && col[rowIndex] != 0) {
                        imageView.setImageResource(R.drawable.ic_black)
                    } else if (col[rowIndex] % 2 == 1) {
                        imageView.setImageResource(R.drawable.ic_white)
                    }
                }
            }
        }

        binding.btnNext.setOnClickListener {
            if (nowIndex < gameIndex) {
                nowIndex++
                accessRecommendationList(nowIndex)

                setImageViews(col1, 1)
                setImageViews(col2, 2)
                setImageViews(col3, 3)
                setImageViews(col4, 4)
                setImageViews(col5, 5)
                setImageViews(col6, 6)
                setImageViews(col7, 7)

            } else {
                Toast.makeText(this@ReviewActivity, "더 볼 수가 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnBefore.setOnClickListener {
            if (nowIndex > 0) {
                nowIndex--
                accessRecommendationList(nowIndex)

                setImageViews(col1, 1)
                setImageViews(col2, 2)
                setImageViews(col3, 3)
                setImageViews(col4, 4)
                setImageViews(col5, 5)
                setImageViews(col6, 6)
                setImageViews(col7, 7)

                if (nowIndex % 2 == 0) {
                    binding.btnHint.isVisible = false
                } else {
                    binding.btnHint.isVisible = true
                }
            } else {
                Toast.makeText(this@ReviewActivity, "돌아갈 수가 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnHint.setOnClickListener {
            // 힌트 누르면 AI 추천 수 보여주기
        }
    }

    private fun setImageViews(col: IntArray, colIndex: Int) {
        for (rowIndex in col.indices) {
            val coord = "iv_gm_$colIndex" + "_${rowIndex + 1}"
            val packageName = packageName
            val ivId = resources.getIdentifier(coord, "id", packageName)
            val imageView = findViewById<ImageView>(ivId)
            if (col[rowIndex] == 1) {  // 값이 1인 경우
                imageView.setImageResource(R.drawable.ic_black)
            } else if (col[rowIndex] == 2) {  // 값이 2인 경우
                imageView.setImageResource(R.drawable.ic_white)
            }
        }
    }

    private fun gameReviewAPI(callback: (GameReviewData.GameReviewDataItem) -> Unit) {
        val gameAPI = RetrofitClient.getInstance().create(RetroiftAPI::class.java)

        gameAPI.getGameReview().enqueue(object : Callback<GameReviewData> {
            override fun onResponse(call: Call<GameReviewData>, response: Response<GameReviewData>) {
                if (response.isSuccessful) {
                    response.body()?.let { gameReviewData ->
                        val maxTurnRow = gameReviewData.maxByOrNull { it.turn }
                        maxTurnRow?.let { maxTurnData ->
                            callback(maxTurnData)
                        }
                    }

                    Log.d("GameReviewAPI", "성공 ${response.body().toString()}")
                }
            }

            override fun onFailure(call: Call<GameReviewData>, t: Throwable) {
                Log.d("GameReviewAPI", "실패")
            }
        })
    }

    // 버튼 클릭 시 nowIndex에 따라 RecommendationList에 저장된 리스트에 접근하는 함수
    private fun accessRecommendationList(nowIndex: Int) {
        // nowIndex가 유효한 범위 내에 있는지 확인
        if (nowIndex >= 0 && nowIndex < recommendationList.size) {
            val recommendationValue = recommendationList[nowIndex]
            Log.d("추천", recommendationValue.toString())
            binding.tvHint.text = recommendationValue.toString()
        } else {
            // 범위를 벗어난 경우 처리
        }
    }
}
