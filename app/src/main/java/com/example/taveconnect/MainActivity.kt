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
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //클릭리스너
        binding.btnKakao.setOnClickListener {

            Log.d("KaKao", "로그인 시도")
            // 카카오 로그인

            // 카카오계정으로 로그인 공통 callback 구성
            // 카카오톡 로그인 불가 -> 카카오 계정으로 로그인 할 경우
            val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                if (error != null) {
                    Log.e("LOGIN", "카카오계정으로 로그인 실패", error)
                } else if (token != null) {
                    Log.i("LOGIN", "카카오계정으로 로그인 성공 ${token.accessToken}")
                }
            }

            /*
            // 카카오톡 설치 됨 -> 카카오 로그인, 설치 안됨 -> 카카오 계정으로 로그인
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                    if (error != null) {
                        Log.e("LOGIN", "카카오톡으로 로그인 실패", error)

                        // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소
                        // 의도적은 로그인 취소로 판단, 카카오계정으로 로그인 시도 없이 뒤로가기
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }

                        // 카카오톡에 연결된 카카오 계정이 없는 경우 카카오 계정으로 로그인 시도
                        UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                    } else if (token != null) {
                        Log.i("LOGIN", "카카오톡으로 로그인 성공 ${token.accessToken}")
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }  else {
                UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
            }
*/



        }

        // 키 해시 확인하기
        var keyHash = Utility.getKeyHash(this)
        Log.d("KeyHash", keyHash)

        //HashGetter.getHashKey(getApplicationContext());
    }
}