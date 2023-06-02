package com.example.taveconnect

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.taveconnect.R
import com.example.taveconnect.databinding.ActivityMainBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient


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

            // 카카오 로그인

            // 카카오계정으로 로그인 공통 callback 구성
            // 카카오톡 로그인 불가 -> 카카오 계정으로 로그인 할 경우
            val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                if (error != null) {
                    Log.e("KaKao", "카카오계정으로 로그인 실패", error)
                } else if (token != null) {
                    Log.i("KaKao", "카카오계정으로 로그인 성공 ${token.accessToken}")
                }
            }

/*
            // 카카오톡 설치 됨 -> 카카오 로그인, 설치 안됨 -> 카카오 계쩡으로 로그인
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
                    if (error != null) {
                        Log.e("KaKao", "카카오톡으로 로그인 실패", error)
                    }
                }
            } */



/*
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)*/
        }

        // 키 해시 확인하기
        var keyHash = Utility.getKeyHash(this)
        Log.d("KeyHash", keyHash)

        //HashGetter.getHashKey(getApplicationContext());
    }
}