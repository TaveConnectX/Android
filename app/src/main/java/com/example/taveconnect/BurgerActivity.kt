package com.example.taveconnect

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.taveconnect.databinding.ActivityBurgerBinding

class BurgerActivity: AppCompatActivity() {
    private lateinit var binding: ActivityBurgerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBurgerBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

    }

    fun buttonOn() {

        // 메인화면으로
        binding.btnGoMain.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        // 새로 시작하기
        binding.btnNewStart.setOnClickListener {
            val intent = Intent(this, DifficultyActivity::class.java)
            startActivity(intent)
        }


    }
}