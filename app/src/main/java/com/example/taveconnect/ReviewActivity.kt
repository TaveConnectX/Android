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
    private var arr = Array(7) { Array(6) { 0 } }
    private var recommendationList : MutableList<Int> = mutableListOf()



    private var first : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val col1 = intent.getIntArrayExtra("r_col1") ?: IntArray(6)
        val col2 = intent.getIntArrayExtra("r_col2") ?: IntArray(6)
        val col3 = intent.getIntArrayExtra("r_col3") ?: IntArray(6)
        val col4 = intent.getIntArrayExtra("r_col4") ?: IntArray(6)
        val col5 = intent.getIntArrayExtra("r_col5") ?: IntArray(6)
        val col6 = intent.getIntArrayExtra("r_col6") ?: IntArray(6)
        val col7 = intent.getIntArrayExtra("r_col7") ?: IntArray(6)

        val gameIndex = intent.getIntExtra("reIndex", 0)
        Log.d("지금 인덱스", gameIndex.toString())

        var nowIndex = gameIndex

        gameReviewAPI { listValues ->
            if (nowIndex >= 0 && nowIndex < listValues.size) {
                val row = listValues[nowIndex]
                if (row.size >= 7) {
                    val col1Row = row[0]
                    val col2Row = row[1]
                    val col3Row = row[2]
                    val col4Row = row[3]
                    val col5Row = row[4]
                    val col6Row = row[5]
                    val col7Row = row[6]

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
            } else {
                nowIndex = 0
                val row = listValues[nowIndex]

            }

            // col1부터 col7까지의 값이 변경되었음
            Log.d("리스트1313914", "col1: ${col1.contentToString()}")
            Log.d("리스트1313914", "col2: ${col2.contentToString()}")
            Log.d("리스트1313914", "col3: ${col3.contentToString()}")
            Log.d("리스트1313914", "col4: ${col4.contentToString()}")
            Log.d("리스트1313914", "col5: ${col5.contentToString()}")
            Log.d("리스트1313914", "col6: ${col6.contentToString()}")
            Log.d("리스트1313914", "col7: ${col7.contentToString()}")
        }

        if(nowIndex == gameIndex && nowIndex == 0)
            Toast.makeText(this, "이전 게임이 없습니다.", Toast.LENGTH_SHORT).show()

        // Use the received data as needed
        Log.d("Review_GameActivity", "Index = $gameIndex")
        Log.d("GameActivity", "현재 3열: ${col3[1]}")

        binding.btnHint.isVisible = false

        first = GlobalApplication.prefs.getInt("first", 0)
        Log.d("GameActivity", "$first")


        // 1열 이미지뷰 상태 복원전
        if (col1 != null) {
            for (i in 0 until col1.size) {
                val coord = "iv_gm_1_" + (i + 1)
                val packageName = packageName
                val ivId = resources.getIdentifier(coord, "id", packageName)
                val imageView = findViewById<ImageView>(ivId)
                if(first == 1) {
                    if (col1[i] % 2 == 0 && col1[i] != 0) {
                        imageView.setImageResource(R.drawable.ic_white)
                    } else if (col1[i] % 2 == 1) {
                        imageView.setImageResource(R.drawable.ic_black)
                    }
                } else if(first == 2) {
                    if (col1[i] % 2 == 0 && col1[i] != 0) {
                        imageView.setImageResource(R.drawable.ic_black)
                    } else if (col1[i] % 2 == 1) {
                        imageView.setImageResource(R.drawable.ic_white)
                    }
                }
            }
        }

        if (col2 != null) {
            for (i in 0 until col2.size) {
                val coord = "iv_gm_2_" + (i + 1)
                val packageName = packageName
                val ivId = resources.getIdentifier(coord, "id", packageName)
                val imageView = findViewById<ImageView>(ivId)
                if(first == 1) {
                    if (col1[i] % 2 == 0 && col1[i] != 0) {
                        imageView.setImageResource(R.drawable.ic_white)
                    } else if (col1[i] % 2 == 1) {
                        imageView.setImageResource(R.drawable.ic_black)
                    }
                } else if(first == 2) {
                    if (col1[i] % 2 == 0 && col1[i] != 0) {
                        imageView.setImageResource(R.drawable.ic_black)
                    } else if (col1[i] % 2 == 1) {
                        imageView.setImageResource(R.drawable.ic_white)
                    }
                }
            }
        }

        if (col3 != null) {
            for (i in 0 until col3.size) {
                val coord = "iv_gm_3_" + (i + 1)
                val packageName = packageName
                val ivId = resources.getIdentifier(coord, "id", packageName)
                val imageView = findViewById<ImageView>(ivId)
                if(first == 1) {
                    if (col1[i] % 2 == 0 && col1[i] != 0) {
                        imageView.setImageResource(R.drawable.ic_white)
                    } else if (col1[i] % 2 == 1) {
                        imageView.setImageResource(R.drawable.ic_black)
                    }
                } else if(first == 2) {
                    if (col1[i] % 2 == 0 && col1[i] != 0) {
                        imageView.setImageResource(R.drawable.ic_black)
                    } else if (col1[i] % 2 == 1) {
                        imageView.setImageResource(R.drawable.ic_white)
                    }
                }
            }
        }

        if (col4 != null) {
            for (i in 0 until col4.size) {
                val coord = "iv_gm_4_" + (i + 1)
                val packageName = packageName
                val ivId = resources.getIdentifier(coord, "id", packageName)
                val imageView = findViewById<ImageView>(ivId)
                if(first == 1) {
                    if (col1[i] % 2 == 0 && col1[i] != 0) {
                        imageView.setImageResource(R.drawable.ic_white)
                    } else if (col1[i] % 2 == 1) {
                        imageView.setImageResource(R.drawable.ic_black)
                    }
                } else if(first == 2) {
                    if (col1[i] % 2 == 0 && col1[i] != 0) {
                        imageView.setImageResource(R.drawable.ic_black)
                    } else if (col1[i] % 2 == 1) {
                        imageView.setImageResource(R.drawable.ic_white)
                    }
                }
            }
        }

        if (col5 != null) {
            for (i in 0 until col5.size) {
                val coord = "iv_gm_5_" + (i + 1)
                val packageName = packageName
                val ivId = resources.getIdentifier(coord, "id", packageName)
                val imageView = findViewById<ImageView>(ivId)
                if(first == 1) {
                    if (col1[i] % 2 == 0 && col1[i] != 0) {
                        imageView.setImageResource(R.drawable.ic_white)
                    } else if (col1[i] % 2 == 1) {
                        imageView.setImageResource(R.drawable.ic_black)
                    }
                } else if(first == 2) {
                    if (col1[i] % 2 == 0 && col1[i] != 0) {
                        imageView.setImageResource(R.drawable.ic_black)
                    } else if (col1[i] % 2 == 1) {
                        imageView.setImageResource(R.drawable.ic_white)
                    }
                }
            }
        }

        if (col6 != null) {
            for (i in 0 until col6.size) {
                val coord = "iv_gm_6_" + (i + 1)
                val packageName = packageName
                val ivId = resources.getIdentifier(coord, "id", packageName)
                val imageView = findViewById<ImageView>(ivId)
                if(first == 1) {
                    if (col1[i] % 2 == 0 && col1[i] != 0) {
                        imageView.setImageResource(R.drawable.ic_white)
                    } else if (col1[i] % 2 == 1) {
                        imageView.setImageResource(R.drawable.ic_black)
                    }
                } else if(first == 2) {
                    if (col1[i] % 2 == 0 && col1[i] != 0) {
                        imageView.setImageResource(R.drawable.ic_black)
                    } else if (col1[i] % 2 == 1) {
                        imageView.setImageResource(R.drawable.ic_white)
                    }
                }
            }
        }

        if (col7 != null) {
            for (i in 0 until col7.size) {
                val coord = "iv_gm_7_" + (i + 1)
                val packageName = packageName
                val ivId = resources.getIdentifier(coord, "id", packageName)
                val imageView = findViewById<ImageView>(ivId)
                if(first == 1) {
                    if (col1[i] % 2 == 0 && col1[i] != 0) {
                        imageView.setImageResource(R.drawable.ic_white)
                    } else if (col1[i] % 2 == 1) {
                        imageView.setImageResource(R.drawable.ic_black)
                    }
                } else if(first == 2) {
                    if (col1[i] % 2 == 0 && col1[i] != 0) {
                        imageView.setImageResource(R.drawable.ic_black)
                    } else if (col1[i] % 2 == 1) {
                        imageView.setImageResource(R.drawable.ic_white)
                    }
                }
            }
        }

        binding.btnNext.setOnClickListener {
            if (nowIndex < gameIndex) {
                nowIndex++
                accessRecommendationList(nowIndex)

                for (i in 0 until col1.size) {
                    val coord = "iv_gm_1_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col1[i] % 2 == 0 && col1[i] != 0) {
                        imageView.setImageResource(R.drawable.ic_white)
                    } else if (col1[i] % 2 == 1) {
                        imageView.setImageResource(R.drawable.ic_black)
                    }
                }
                for (i in 0 until col2.size) {
                    val coord = "iv_gm_2_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col2[i] % 2 == 0 && col1[i] != 0) {
                        imageView.setImageResource(R.drawable.ic_white)
                    } else if (col1[i] % 2 == 1) {
                        imageView.setImageResource(R.drawable.ic_black)
                    }
                }
                for (i in 0 until col3.size) {
                    val coord = "iv_gm_3_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col3[i] % 2 == 0 && col3[i] != 0) {
                        imageView.setImageResource(R.drawable.ic_white)
                    } else if (col3[i] % 2 == 1) {
                        imageView.setImageResource(R.drawable.ic_black)
                    }
                }
                for (i in 0 until col4.size) {
                    val coord = "iv_gm_4_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col4[i] % 2 == 0 && col4[i] != 0) {
                        imageView.setImageResource(R.drawable.ic_white)
                    } else if (col4[i] % 2 == 1) {
                        imageView.setImageResource(R.drawable.ic_black)
                    }
                }
                for (i in 0 until col5.size) {
                    val coord = "iv_gm_5_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col5[i] % 2 == 0 && col5[i] != 0) {
                        imageView.setImageResource(R.drawable.ic_white)
                    } else if (col5[i] % 2 == 1) {
                        imageView.setImageResource(R.drawable.ic_black)
                    }
                }
                for (i in 0 until col6.size) {
                    val coord = "iv_gm_6_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col6[i] % 2 == 0 && col6[i] != 0) {
                        imageView.setImageResource(R.drawable.ic_white)
                    } else if (col6[i] % 2 == 1) {
                        imageView.setImageResource(R.drawable.ic_black)
                    }
                }
                for (i in 0 until col7.size) {
                    val coord = "iv_gm_7_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col7[i] % 2 == 0 && col7[i] != 0) {
                        imageView.setImageResource(R.drawable.ic_white)
                    } else if (col7[i] % 2 == 1) {
                        imageView.setImageResource(R.drawable.ic_black)
                    }
                }

            } else {
                Toast.makeText(this@ReviewActivity, "더 볼 수가 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnBefore.setOnClickListener {
            if (nowIndex > 1) {
                for (i in 0 until col1.size) {
                    val coord = "iv_gm_1_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col1[i] == nowIndex) {
                        imageView.setImageResource(R.drawable.nothing)
                        break
                    }
                }
                for (i in 0 until col2.size) {
                    val coord = "iv_gm_2_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col2[i] == nowIndex) {
                        imageView.setImageResource(R.drawable.nothing)
                        break
                    }
                }
                for (i in 0 until col3.size) {
                    val coord = "iv_gm_3_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col3[i] == nowIndex) {
                        imageView.setImageResource(R.drawable.nothing)
                        break
                    }
                }
                for (i in 0 until col4.size) {
                    val coord = "iv_gm_4_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col4[i] == nowIndex) {
                        imageView.setImageResource(R.drawable.nothing)
                        break
                    }
                }
                for (i in 0 until col5.size) {
                    val coord = "iv_gm_5_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col5[i] == nowIndex) {
                        imageView.setImageResource(R.drawable.nothing)
                        break
                    }
                }
                for (i in 0 until col6.size) {
                    val coord = "iv_gm_6_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col6[i] == nowIndex) {
                        imageView.setImageResource(R.drawable.nothing)
                        break
                    }
                }
                for (i in 0 until col7.size) {
                    val coord = "iv_gm_7_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col7[i] == nowIndex) {
                        imageView.setImageResource(R.drawable.nothing)
                        break
                    }
                }
                nowIndex--

                accessRecommendationList(nowIndex)

                if (nowIndex % 2 == 0) {
                    binding.btnHint.isVisible = false
                } else {
                    binding.btnHint.isVisible = true
                }
            } else if (nowIndex == 1) {
                nowIndex--
                Toast.makeText(this@ReviewActivity, "돌아갈 수가 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnHint.setOnClickListener {
            // 힌트 누르면 AI 추천 수 보여주기
        }

    }

    // BurgerFragment 클릭 이벤트
    fun showBurger() {
        binding.btnBurger.setOnClickListener {
            val intent = Intent(this, BurgerActivity::class.java)
            startActivity(intent)
        }
    }
    fun gameReviewAPI(callback: (MutableList<List<List<Int>>>) -> Unit) {
        val gameAPI = RetrofitClient.getInstance().create(RetroiftAPI::class.java)

        gameAPI.getGameReview().enqueue(object : Callback<GameReviewData> {
            override fun onResponse(call: Call<GameReviewData>, response: Response<GameReviewData>) {
                if (response.isSuccessful) {
                    response.body()?.let { gameReviewData ->
                        val listValues: MutableList<List<List<Int>>> = mutableListOf()

                        // 받아온 데이터를 순회하면서 list 값을 추출하여 리스트에 추가
                        for (gameReviewDataItem in gameReviewData) {
                            val listValue = gameReviewDataItem.list
                            listValues.add(listValue)
                        }

                        // 추출한 list 값들을 확인
                        Log.d("ListValues", listValues.toString())

                        // listValues를 이용한 작업을 여기에서 수행
                        callback(listValues)
                    }
                    Log.d("GameReviewAPI", "성공 ${response.body().toString()}")
                }
            }

            override fun onFailure(call: Call<GameReviewData>, t: Throwable) {
                Log.d("GameReviewAPI", "실패")
            }
        })
    }


    fun convertTo2DArray(col1: IntArray, col2: IntArray, col3: IntArray, col4: IntArray, col5: IntArray, col6: IntArray, col7: IntArray): Array<Array<Int>> {
        val arrays = arrayOf(col1, col2, col3, col4, col5, col6, col7)
        val numRows = col1.size
        val numCols = arrays.size


        val result = Array(numRows) { row ->
            Array(numCols) { col ->
                arrays[col][row]
            }
        }

        Log.d("Tlqkf2", "${java.util.Arrays.deepToString(arr)}")


        for (i in 0 until 6) {
            arr[0][i] = col1[i]
            arr[1][i] = col2[i]
            arr[2][i] = col3[i]
            arr[3][i] = col4[i]
            arr[4][i] = col5[i]
            arr[5][i] = col6[i]
            arr[6][i] = col7[i]
        }

        return arr
    }


    // 버튼 클릭 시 nowIndex에 따라 RecommendationList에 저장된 리스트에 접근하는 함수
    fun accessRecommendationList(nowIndex: Int) {
        // nowIndex가 유효한 범위 내에 있는지 확인
        if (nowIndex >= 0 && nowIndex < recommendationList.size) {
            val recommendationValue = recommendationList[nowIndex]
            Log.d("추천", recommendationValue.toString())
            binding.tvHint.text = recommendationValue.toString()

        } else {
        }
    }

    fun processListValues(listValues: List<List<List<Int>>>): Array<Array<Int>> {
        // listValues를 사용하여 필요한 작업을 수행
        Log.d("리스트", listValues.toString())

        val col1 = listValues[0].map { it[0] }.toIntArray()
        val col2 = listValues[1].map { it[0] }.toIntArray()
        val col3 = listValues[2].map { it[0] }.toIntArray()
        val col4 = listValues[3].map { it[0] }.toIntArray()
        val col5 = listValues[4].map { it[0] }.toIntArray()
        val col6 = listValues[5].map { it[0] }.toIntArray()
        val col7 = listValues[6].map { it[0] }.toIntArray()

        // col1부터 col7까지 사용하여 필요한 작업을 수행
        return convertTo2DArray(col1, col2, col3, col4, col5, col6, col7)
    }

}