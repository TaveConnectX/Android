package com.example.taveconnect

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taveconnect.databinding.ActivityReviewBinding

class ReviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReviewBinding
    private lateinit var imageViews: List<ImageView> // 이미지뷰들을 저장할 리스트 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        showBurger()

        // 이미지뷰들을 초기화하고 리스트에 추가
        /*imageViews = listOf(
            binding.ivGm11,
            binding.ivGm12,
            binding.ivGm13,
            binding.ivGm14,
            binding.ivGm15,
            binding.ivGm16,
            binding.ivGm21,
            binding.ivGm22,
            binding.ivGm23,
            binding.ivGm24,
            binding.ivGm25,
            binding.ivGm26,
            binding.ivGm31,
            binding.ivGm32,
            binding.ivGm33,
            binding.ivGm34,
            binding.ivGm35,
            binding.ivGm36,
            binding.ivGm41,
            binding.ivGm42,
            binding.ivGm43,
            binding.ivGm44,
            binding.ivGm45,
            binding.ivGm46,
            binding.ivGm51,
            binding.ivGm52,
            binding.ivGm53,
            binding.ivGm54,
            binding.ivGm55,
            binding.ivGm56,
            binding.ivGm61,
            binding.ivGm62,
            binding.ivGm63,
            binding.ivGm64,
            binding.ivGm65,
            binding.ivGm66,
            binding.ivGm71,
            binding.ivGm72,
            binding.ivGm73,
            binding.ivGm74,
            binding.ivGm75,
            binding.ivGm76,
        )*/

        var nowIndex = 0

        val gameIndex = intent.getIntExtra("reIndex", 0)
        val goMain = intent.getBooleanExtra("goMain", true)
        val col1 = intent.getIntArrayExtra("r_col1") ?: IntArray(6)
        val col2 = intent.getIntArrayExtra("r_col2") ?: IntArray(6)
        val col3 = intent.getIntArrayExtra("r_col3") ?: IntArray(6)
        val col4 = intent.getIntArrayExtra("r_col4") ?: IntArray(6)
        val col5 = intent.getIntArrayExtra("r_col5") ?: IntArray(6)
        val col6 = intent.getIntArrayExtra("r_col6") ?: IntArray(6)
        val col7 = intent.getIntArrayExtra("r_col7") ?: IntArray(6)

        Log.d("GameActivity", "test = $goMain")
        Log.d("GameActivity", "Index = $gameIndex")
        Log.d("GameActivity", "현재 3열: ${col3[1]}")

        // 1열 이미지뷰 상태 복원
        if (col1 != null) {
            for (i in 0 until col1.size) {
                val coord = "iv_gm_1_" + (i + 1)
                val packageName = packageName
                val ivId = resources.getIdentifier(coord, "id", packageName)
                val imageView = findViewById<ImageView>(ivId)
                if (col1[i] % 2 == 0 && col1[i] != 0) {
                    imageView.setImageResource(R.drawable.ic_black)
                } else if (col1[i] % 2 == 1) {
                    imageView.setImageResource(R.drawable.ic_white)
                }
            }
        }

        if (col2 != null) {
            for (i in 0 until col2.size) {
                val coord = "iv_gm_2_" + (i + 1)
                val packageName = packageName
                val ivId = resources.getIdentifier(coord, "id", packageName)
                val imageView = findViewById<ImageView>(ivId)
                if (col2[i] % 2 == 0 && col2[i] != 0) {
                    imageView.setImageResource(R.drawable.ic_black)
                } else if (col2[i] % 2 == 1) {
                    imageView.setImageResource(R.drawable.ic_white)
                }
            }
        }

        if (col3 != null) {
            for (i in 0 until col3.size) {
                val coord = "iv_gm_3_" + (i + 1)
                val packageName = packageName
                val ivId = resources.getIdentifier(coord, "id", packageName)
                val imageView = findViewById<ImageView>(ivId)
                if (col3[i] % 2 == 0 && col3[i] != 0) {
                    imageView.setImageResource(R.drawable.ic_black)
                } else if (col3[i] % 2 == 1) {
                    imageView.setImageResource(R.drawable.ic_white)
                }
            }
        }

        if (col4 != null) {
            for (i in 0 until col4.size) {
                val coord = "iv_gm_4_" + (i + 1)
                val packageName = packageName
                val ivId = resources.getIdentifier(coord, "id", packageName)
                val imageView = findViewById<ImageView>(ivId)
                if (col4[i] % 2 == 0 && col4[i] != 0) {
                    imageView.setImageResource(R.drawable.ic_black)
                } else if (col4[i] % 2 == 1) {
                    imageView.setImageResource(R.drawable.ic_white)
                }
            }
        }

        if (col5 != null) {
            for (i in 0 until col5.size) {
                val coord = "iv_gm_5_" + (i + 1)
                val packageName = packageName
                val ivId = resources.getIdentifier(coord, "id", packageName)
                val imageView = findViewById<ImageView>(ivId)
                if (col5[i] % 2 == 0 && col5[i] != 0) {
                    imageView.setImageResource(R.drawable.ic_black)
                } else if (col5[i] % 2 == 1) {
                    imageView.setImageResource(R.drawable.ic_white)
                }
            }
        }

        if (col6 != null) {
            for (i in 0 until col6.size) {
                val coord = "iv_gm_6_" + (i + 1)
                val packageName = packageName
                val ivId = resources.getIdentifier(coord, "id", packageName)
                val imageView = findViewById<ImageView>(ivId)
                if (col6[i] % 2 == 0 && col6[i] != 0) {
                    imageView.setImageResource(R.drawable.ic_black)
                } else if (col6[i] % 2 == 1) {
                    imageView.setImageResource(R.drawable.ic_white)
                }
            }
        }

        if (col7 != null) {
            for (i in 0 until col7.size) {
                val coord = "iv_gm_7_" + (i + 1)
                val packageName = packageName
                val ivId = resources.getIdentifier(coord, "id", packageName)
                val imageView = findViewById<ImageView>(ivId)
                if (col7[i] % 2 == 0 && col7[i] != 0) {
                    imageView.setImageResource(R.drawable.ic_black)
                } else if (col7[i] % 2 == 1) {
                    imageView.setImageResource(R.drawable.ic_white)
                }
            }
        }

        // 이전 복기
        binding.btnBefore.setOnClickListener {
            if(nowIndex < gameIndex && nowIndex != 0) {
                for (i in 0 until col1.size) {
                    val coord = "iv_gm_1_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col1[i] == nowIndex ) {
                        imageView.setImageResource(R.drawable.ic_black)
                        nowIndex --
                    }
                }

                for (i in 0 until col2.size) {
                    val coord = "iv_gm_2_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col2[i] == nowIndex ) {
                        imageView.setImageResource(R.drawable.ic_black)
                        nowIndex --
                    }
                }

                for (i in 0 until col3.size) {
                    val coord = "iv_gm_3_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col3[i] == nowIndex ) {
                        imageView.setImageResource(R.drawable.ic_black)
                        nowIndex --
                    }
                }

                for (i in 0 until col4.size) {
                    val coord = "iv_gm_4_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col4[i] == nowIndex ) {
                        imageView.setImageResource(R.drawable.ic_black)
                        nowIndex --
                    }
                }

                for (i in 0 until col5.size) {
                    val coord = "iv_gm_5_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col5[i] == nowIndex ) {
                        imageView.setImageResource(R.drawable.ic_black)
                        nowIndex --
                    }
                }

                for (i in 0 until col6.size) {
                    val coord = "iv_gm_6_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col6[i] == nowIndex ) {
                        imageView.setImageResource(R.drawable.ic_black)
                        nowIndex --
                    }
                }

                for (i in 0 until col7.size) {
                    val coord = "iv_gm_7_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col7[i] == nowIndex ) {
                        imageView.setImageResource(R.drawable.ic_black)
                        nowIndex --
                    }
                }
            } else {
                Toast.makeText(this@ReviewActivity, "돌아갈 수가 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        // 다음 복기
        binding.btnNext.setOnClickListener {
            if(nowIndex <= gameIndex ) {
                for (i in 0 until col1.size) {
                    val coord = "iv_gm_1_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col1[i] == nowIndex ) {
                        imageView.setImageResource(R.drawable.ic_black)
                        nowIndex ++
                    }
                }

                for (i in 0 until col2.size) {
                    val coord = "iv_gm_2_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col2[i] == nowIndex ) {
                        imageView.setImageResource(R.drawable.ic_black)
                        nowIndex ++
                    }
                }

                for (i in 0 until col3.size) {
                    val coord = "iv_gm_3_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col3[i] == nowIndex ) {
                        imageView.setImageResource(R.drawable.ic_black)
                        nowIndex ++
                    }
                }

                for (i in 0 until col4.size) {
                    val coord = "iv_gm_4_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col4[i] == nowIndex ) {
                        imageView.setImageResource(R.drawable.ic_black)
                        nowIndex ++
                    }
                }

                for (i in 0 until col5.size) {
                    val coord = "iv_gm_5_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col5[i] == nowIndex ) {
                        imageView.setImageResource(R.drawable.ic_black)
                        nowIndex ++
                    }
                }

                for (i in 0 until col6.size) {
                    val coord = "iv_gm_6_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col6[i] == nowIndex ) {
                        imageView.setImageResource(R.drawable.ic_black)
                        nowIndex ++
                    }
                }

                for (i in 0 until col7.size) {
                    val coord = "iv_gm_7_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col7[i] == nowIndex ) {
                        imageView.setImageResource(R.drawable.ic_black)
                        nowIndex ++
                    }
                }
            } else {
                Toast.makeText(this@ReviewActivity, "더 볼 수가 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 이미지뷰 업데이트 함수
    // BurgerFragment 클릭 이벤트
    fun showBurger() {
        binding.btnBurger.setOnClickListener {
            val intent = Intent(this, BurgerActivity::class.java)
            startActivity(intent)
        }
    }
}

