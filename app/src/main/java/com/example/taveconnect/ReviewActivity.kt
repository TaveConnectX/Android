package com.example.taveconnect

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taveconnect.databinding.ActivityReviewBinding

class ReviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReviewBinding
    private lateinit var imageViews: List<ImageView> // 이미지뷰들을 저장할 리스트 변수

    var index = 0

    val extras: Bundle? = intent.extras

    val d_index = extras?.getInt("index", 0)
    val col1 = extras?.getIntArray("col1")
    val col2 = extras?.getIntArray("col2")
    val col3 = extras?.getIntArray("col3")
    val col4 = extras?.getIntArray("col4")
    val col5 = extras?.getIntArray("col5")
    val col6 = extras?.getIntArray("col6")
    val col7 = extras?.getIntArray("col7")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 이미지뷰들을 초기화하고 리스트에 추가
        imageViews = listOf(
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
        )

        showBurger()

        // 이전 복기
        binding.btnBefore.setOnClickListener {
            if (index == 0) {
                Toast.makeText(this, "되돌아갈 수가 없습니다.", Toast.LENGTH_SHORT).show()
            } else {
                index--
                updateImageView(index) // 이전 인덱스의 이미지뷰를 업데이트
            }
        }

        // 다음 복기
        binding.btnNext.setOnClickListener {
            if (index >= d_index!!) {
                Toast.makeText(this, "더 이상 수가 없습니다.", Toast.LENGTH_SHORT).show()
            } else {
                index++
                updateImageView(index) // 다음 인덱스의 이미지뷰를 업데이트
            }
        }
    }

    // 이미지뷰 업데이트 함수
    private fun updateImageView(index: Int) {
        val arrays = listOf(col1, col2, col3, col4, col5, col6, col7)
        val imageIndex = index - 1 // 인덱스는 0부터 시작하므로 1을 빼줌

        for ((i, array) in arrays.withIndex()) {
            if (array != null && i < imageViews.size) {
                val imageView = imageViews[i]
                if (imageIndex >= 0 && imageIndex < array.size) {
                    // 이미지뷰의 이미지 변경
                    val image = array[imageIndex]
                    imageView.setImageResource(image)
                } else {
                    // 이미지뷰의 이미지를 기본 이미지로 변경
                    imageView.setImageResource(R.drawable.nothing)
                }
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

