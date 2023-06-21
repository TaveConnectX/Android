package com.example.taveconnect

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.taveconnect.databinding.ActivityDifficultyBinding

class DifficultyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDifficultyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDifficultyBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        binding.btnEasy.setOnClickListener {
            val intent = Intent(this,GameActivity::class.java)
            intent.putExtra("difficulty", "easy")
            startActivity(intent)
        }

        binding.btnNormal.setOnClickListener {
            val intent = Intent(this,GameActivity::class.java)
            intent.putExtra("difficulty", "normal")
            startActivity(intent)
        }

        binding.btnHard.setOnClickListener {
            val intent = Intent(this,GameActivity::class.java)
            intent.putExtra("difficulty", "hard")
            startActivity(intent)
        }

    }

}