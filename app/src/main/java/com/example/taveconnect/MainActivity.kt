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

        //Intent 를 활용해서 액티비티 이동하기
        val intent = Intent(this, LoginActivity::class.java)

        //버튼 지정
        val BtnKakao = findViewById<Button>(R.id.btnKakao)
        //클릭리스너
        BtnKakao.setOnClickListener {
            //액티비티 이동
            startActivity(intent)
        }

        //HashGetter.getHashKey(getApplicationContext());
    }
}