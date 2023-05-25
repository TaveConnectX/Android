package com.example.taveconnect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taveconnect.R
import com.example.taveconnect.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}