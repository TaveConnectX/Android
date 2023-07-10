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

        gameReviewAPI()

        binding.btnHint.setOnClickListener {
            gameReviewAPI()
        }
        showBurger()


        val col1 = intent.getIntArrayExtra("r_col1") ?: IntArray(6)
        val col2 = intent.getIntArrayExtra("r_col2") ?: IntArray(6)
        val col3 = intent.getIntArrayExtra("r_col3") ?: IntArray(6)
        val col4 = intent.getIntArrayExtra("r_col4") ?: IntArray(6)
        val col5 = intent.getIntArrayExtra("r_col5") ?: IntArray(6)
        val col6 = intent.getIntArrayExtra("r_col6") ?: IntArray(6)
        val col7 = intent.getIntArrayExtra("r_col7") ?: IntArray(6)

        var arraysGame = convertTo2DArray(col1, col2, col3, col4, col5, col6, col7)

        val gameIndex = intent.getIntExtra("reIndex", 0)


        var nowIndex = gameIndex

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
                    if (col1[i] == nowIndex) {
                        if (nowIndex % 2 == 0) {
                            imageView.setImageResource(R.drawable.ic_white)
                            binding.btnHint.isVisible = false
                        } else {
                            imageView.setImageResource(R.drawable.ic_black)
                            binding.btnHint.isVisible = true
                        }
                        break
                    }
                }
                for (i in 0 until col2.size) {
                    val coord = "iv_gm_2_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col2[i] == nowIndex) {
                        if (nowIndex % 2 == 0) {
                            imageView.setImageResource(R.drawable.ic_white)
                            binding.btnHint.isVisible = false
                        } else {
                            imageView.setImageResource(R.drawable.ic_black)
                            binding.btnHint.isVisible = true
                        }
                        break
                    }
                }
                for (i in 0 until col3.size) {
                    val coord = "iv_gm_3_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col3[i] == nowIndex) {
                        if (nowIndex % 2 == 0) {
                            imageView.setImageResource(R.drawable.ic_white)
                            binding.btnHint.isVisible = false
                        } else {
                            imageView.setImageResource(R.drawable.ic_black)
                            binding.btnHint.isVisible = true
                        }
                        break
                    }
                }
                for (i in 0 until col4.size) {
                    val coord = "iv_gm_4_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col4[i] == nowIndex) {
                        if (nowIndex % 2 == 0) {
                            imageView.setImageResource(R.drawable.ic_white)
                            binding.btnHint.isVisible = false
                        } else {
                            imageView.setImageResource(R.drawable.ic_black)
                            binding.btnHint.isVisible = true
                        }
                        break
                    }
                }
                for (i in 0 until col5.size) {
                    val coord = "iv_gm_5_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col5[i] == nowIndex) {
                        if (nowIndex % 2 == 0) {
                            imageView.setImageResource(R.drawable.ic_white)
                            binding.btnHint.isVisible = false
                        } else {
                            imageView.setImageResource(R.drawable.ic_black)
                            binding.btnHint.isVisible = true
                        }
                        break
                    }
                }
                for (i in 0 until col6.size) {
                    val coord = "iv_gm_6_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col6[i] == nowIndex) {
                        if (nowIndex % 2 == 0) {
                            imageView.setImageResource(R.drawable.ic_white)
                            binding.btnHint.isVisible = false
                        } else {
                            imageView.setImageResource(R.drawable.ic_black)
                            binding.btnHint.isVisible = true
                        }
                        break
                    }
                }
                for (i in 0 until col7.size) {
                    val coord = "iv_gm_7_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col7[i] == nowIndex) {
                        if (nowIndex % 2 == 0) {
                            imageView.setImageResource(R.drawable.ic_white)
                            binding.btnHint.isVisible = false
                        } else {
                            imageView.setImageResource(R.drawable.ic_black)
                            binding.btnHint.isVisible = true
                        }
                        break
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

    fun gameReviewAPI() {
        val gameAPI = RetrofitClient.getInstance().create(RetroiftAPI::class.java)

        gameAPI.getGameReview().enqueue(object: Callback<GameReviewData> {
            override fun onResponse(call: Call<GameReviewData>, response: Response<GameReviewData>) {
                if (response.isSuccessful) {
                    response.body()?.let { gameReviewData ->
                        val firstList: MutableList<Int> = mutableListOf()

                        // 받아온 데이터를 순회하면서 recommendation 값과 first 값을 추출하여 리스트에 추가
                        for (gameReviewDataItem in gameReviewData) {
                            recommendationList.add(gameReviewDataItem.recommendation)
                            firstList.add(gameReviewDataItem.first)
                        }


                        // 추출한 recommendation 값들을 확인
                        Log.d("RecommendationList", recommendationList.toString())
                        // 추출한 first 값들을 확인
                        Log.d("FirstList", firstList[0].toString())

                        GlobalApplication.prefs.setInt("first", firstList[0])

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


}
