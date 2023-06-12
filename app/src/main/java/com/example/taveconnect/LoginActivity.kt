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
import com.example.taveconnect.databinding.ActivityLoginBinding
import okhttp3.internal.userAgent


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        loginInfo()

        //버튼 지정
        binding.btnEnjoy.setOnClickListener {
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }
    }

    // 닉네임 받아오기

    private fun loginInfo() {

        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e("LOGIN", "사용자 정보 요청 실패", error)
            }

            else if (user != null) {
                Log.i("LOGIN", "사용자 정보 요청 성공")
                binding.tvName.text = user.kakaoAccount?.profile?.nickname
            }
        }

    }
}