package com.example.taveconnect

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.taveconnect.R
import com.kakao.sdk.user.UserApiClient



class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //버튼 지정
        val BtnEnjoy = findViewById<Button>(R.id.btn_enjoy)

        //클릭리스너
        BtnEnjoy.setOnClickListener {
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }
    }
}