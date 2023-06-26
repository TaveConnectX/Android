package com.example.taveconnect

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.taveconnect.databinding.ActivityBurgerBinding

class BurgerActivity: AppCompatActivity() {
    private lateinit var binding: ActivityBurgerBinding
    val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBurgerBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

    }


    private fun Fragment.changeFragment() {
        manager.beginTransaction().replace(R.id.fv_burger, this).commit()
    }


}