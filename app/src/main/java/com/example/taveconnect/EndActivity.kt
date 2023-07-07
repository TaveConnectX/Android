package com.example.taveconnect

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.taveconnect.databinding.ActivityEndBinding


class EndActivity: AppCompatActivity() {
    private lateinit var binding: ActivityEndBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEndBinding.inflate(layoutInflater)
        var context_end = this
        val view = binding.root

        setContentView(view)

        val turn = intent.getIntExtra("t", 0)

        val gameIndex = intent.getIntExtra("reIndex", 0)
        Log.d("GameActivity", "Index = "+gameIndex)
        val col1 = intent.getIntArrayExtra("r_col1") ?: IntArray(6)
        val col2 = intent.getIntArrayExtra("r_col2") ?: IntArray(6)
        val col3 = intent.getIntArrayExtra("r_col3") ?: IntArray(6)
        val col4 = intent.getIntArrayExtra("r_col4") ?: IntArray(6)
        val col5 = intent.getIntArrayExtra("r_col5") ?: IntArray(6)
        val col6 = intent.getIntArrayExtra("r_col6") ?: IntArray(6)
        val col7 = intent.getIntArrayExtra("r_col7") ?: IntArray(6)


        if(turn == 2)
            binding.tvGameEnd.text = "당신이 이겼습니다!"
        else if(turn == 3)
            binding.tvGameEnd.text = "당신은 패배했습니다."

        binding.tvOut.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("reIndex", gameIndex)
            intent.putExtra("r_col1", col1)
            intent.putExtra("r_col2", col2)
            intent.putExtra("r_col3", col3)
            intent.putExtra("r_col4", col4)
            intent.putExtra("r_col5", col5)
            intent.putExtra("r_col6", col6)
            intent.putExtra("r_col7", col7)
            startActivity(intent)
        }

    }
}