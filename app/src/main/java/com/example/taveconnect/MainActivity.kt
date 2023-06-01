package com.example.taveconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.taveconnect.R
import com.example.taveconnect.databinding.ActivityMainBinding
import com.kakao.sdk.common.util.Utility

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //버튼 지정
        val BtnKakao = findViewById<Button>(R.id.btn_kakao)

        //클릭리스너
        BtnKakao.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        // 키 해시 확인하기
        var keyHash = Utility.getKeyHash(this)
        Log.d("KeyHash", keyHash)

        //HashGetter.getHashKey(getApplicationContext());
    }
}