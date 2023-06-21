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

        var num = 0
        var sec = 0

        setContentView(view)

        binding.btnEasy.setOnClickListener {
            val intent = Intent(this,GameActivity::class.java)
            num = 1
            startActivity(intent)
        }

        binding.btnNormal.setOnClickListener {
            val intent = Intent(this,GameActivity::class.java)
            num = 2
            startActivity(intent)
        }

        binding.btnHard.setOnClickListener {
            val intent = Intent(this,GameActivity::class.java)
            num = 3
            startActivity(intent)
        }

        if(num == 1) sec = 0
        else if(num == 2) sec = 30000
        else if(num == 3) sec = 15000

        intent.putExtra("seconds", sec)

    }

}