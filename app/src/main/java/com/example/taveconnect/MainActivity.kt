package com.example.taveconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.taveconnect.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //버튼 지정
        val BtnKakao = findViewById<Button>(R.id.btn_kakao)

        //클릭리스너
        BtnKakao.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        //HashGetter.getHashKey(getApplicationContext());
    }
}