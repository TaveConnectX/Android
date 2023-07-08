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

        val gameIndex = intent.getIntExtra("reIndex", 0)
        val col1 = intent.getIntArrayExtra("r_col1") ?: IntArray(6)
        val col2 = intent.getIntArrayExtra("r_col2") ?: IntArray(6)
        val col3 = intent.getIntArrayExtra("r_col3") ?: IntArray(6)
        val col4 = intent.getIntArrayExtra("r_col4") ?: IntArray(6)
        val col5 = intent.getIntArrayExtra("r_col5") ?: IntArray(6)
        val col6 = intent.getIntArrayExtra("r_col6") ?: IntArray(6)
        val col7 = intent.getIntArrayExtra("r_col7") ?: IntArray(6)

        var arrays = arrayOf(col1, col2, col3, col4, col5, col6, col7)

        if(arrays == {0}) {
            Toast.makeText(this, "직전 게임이 없습니다.", Toast.LENGTH_SHORT).show()
        }
        var nowIndex = gameIndex

        // Use the received data as needed
        Log.d("Review_GameActivity", "Index = $gameIndex")
        Log.d("GameActivity", "현재 3열: ${col3[1]}")

        // 1열 이미지뷰 상태 복원
        if (col1 != null) {
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
        }

        if (col2 != null) {
            for (i in 0 until col2.size) {
                val coord = "iv_gm_2_" + (i + 1)
                val packageName = packageName
                val ivId = resources.getIdentifier(coord, "id", packageName)
                val imageView = findViewById<ImageView>(ivId)
                if (col2[i] % 2 == 0 && col2[i] != 0) {
                    imageView.setImageResource(R.drawable.ic_white)
                } else if (col2[i] % 2 == 1) {
                    imageView.setImageResource(R.drawable.ic_black)
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
                    imageView.setImageResource(R.drawable.ic_white)
                } else if (col3[i] % 2 == 1) {
                    imageView.setImageResource(R.drawable.ic_black)
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
                    imageView.setImageResource(R.drawable.ic_white)
                } else if (col4[i] % 2 == 1) {
                    imageView.setImageResource(R.drawable.ic_black)
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
                    imageView.setImageResource(R.drawable.ic_white)
                } else if (col5[i] % 2 == 1) {
                    imageView.setImageResource(R.drawable.ic_black)
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
                    imageView.setImageResource(R.drawable.ic_white)
                } else if (col6[i] % 2 == 1) {
                    imageView.setImageResource(R.drawable.ic_black)
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
                    imageView.setImageResource(R.drawable.ic_white)
                } else if (col7[i] % 2 == 1) {
                    imageView.setImageResource(R.drawable.ic_black)
                }
            }
        }

        binding.btnNext.setOnClickListener {
            if (nowIndex < gameIndex) {
                nowIndex++
                for (i in 0 until col1.size) {
                    val coord = "iv_gm_1_" + (i + 1)
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    if (col1[i] == nowIndex) {
                        if (nowIndex % 2 == 0) {
                            imageView.setImageResource(R.drawable.ic_white)
                        } else {
                            imageView.setImageResource(R.drawable.ic_black)
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
                        } else {
                            imageView.setImageResource(R.drawable.ic_black)
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
                        } else {
                            imageView.setImageResource(R.drawable.ic_black)
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
                        } else {
                            imageView.setImageResource(R.drawable.ic_black)
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
                        } else {
                            imageView.setImageResource(R.drawable.ic_black)
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
                        } else {
                            imageView.setImageResource(R.drawable.ic_black)
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
                        } else {
                            imageView.setImageResource(R.drawable.ic_black)
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
            } else if (nowIndex == 1) {
                nowIndex--
                Toast.makeText(this@ReviewActivity, "돌아갈 수가 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }

    }

    // BurgerFragment 클릭 이벤트
    fun showBurger() {
        binding.btnBurger.setOnClickListener {
            val intent = Intent(this, BurgerActivity::class.java)
            startActivity(intent)
        }
    }
}

