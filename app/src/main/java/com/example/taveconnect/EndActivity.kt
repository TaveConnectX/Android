package com.example.taveconnect

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.taveconnect.databinding.ActivityEndBinding


class EndActivity: AppCompatActivity() {
    private lateinit var binding: ActivityEndBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEndBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        val turn: String? = intent.getStringExtra("t")

        if(turn == "3")
            binding.tvGameEnd.text = "당신이 이겼습니다!"
        else if(turn == "4")
            binding.tvGameEnd.text = "당신은 패배했습니다."

        binding.tvOut.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

    }
}