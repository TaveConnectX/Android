package com.example.taveconnect

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
}