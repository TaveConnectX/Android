package com.example.taveconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.DragAndDropPermissionsCompat.request
import com.example.taveconnect.databinding.ActivityMainBinding
import com.example.taveconnect.login.ResponseLoginData
import com.example.taveconnect.retrofit.RetrofitClient
import com.example.taveconnect.retrofit.RetroiftAPI
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.common.model.AuthErrorCause.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 키 해시 확인하기
        var keyHash = Utility.getKeyHash(this)
        Log.d("KeyHash", keyHash)

        kakaoLogin()

    }




    private fun kakaoLogin() {
        val api = RetrofitClient.getInstance().create(RetroiftAPI::class.java)


        // 카카오계정으로 로그인 공통 callback 구성
        // 카카오톡 로그인 불가 -> 카카오 계정으로 로그인 할 경우
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                when {
                    error.toString() == AccessDenied.toString() -> {
                        Toast.makeText(this, "접근이 거부 됨(동의 취소)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == InvalidClient.toString() -> {
                        Toast.makeText(this, "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == InvalidGrant.toString() -> {
                        Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == InvalidRequest.toString() -> {
                        Toast.makeText(this, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == InvalidScope.toString() -> {
                        Toast.makeText(this, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == Misconfigured.toString() -> {
                        Toast.makeText(this, "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == ServerError.toString() -> {
                        Toast.makeText(this, "서버 내부 에러", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == Unauthorized.toString() -> {
                        Toast.makeText(this, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(this, "기타 에러", Toast.LENGTH_SHORT).show()
                    }
                }
            } else if (token != null) {
                Log.i("LOGIN", "카카오계정으로 로그인 성공 ${token.accessToken}")

                val tokenKakao =
                    getSharedPreferences("kakaoToken", MODE_PRIVATE)

                tokenKakao.edit().putString("access", token.accessToken).apply()
                val accessToken = tokenKakao.getString("access", "")

                tokenKakao.edit().putString("refresh", token.refreshToken).apply()
                val refreshToken = tokenKakao.getString("refresh", "")

                Log.d("kakao_access_token", "$accessToken")
                Log.d("kakao_refresh_token", "$refreshToken")

                // api 통신

                api.getLogin(accessToken = token.accessToken, refreshToken = token.refreshToken)
                    .enqueue(object: Callback<ResponseLoginData> {
                        override fun onResponse(call: Call<ResponseLoginData>, response: Response<ResponseLoginData>) {
                            Log.d("LoginAPI", "로그인 통신 성공 \n이름 : ${response.body()?.name}" +
                                    "\n프로필 사진 링크 : ${response.body()?.profile}" +
                                    "\nJWT 토큰 : ${response?.raw()?.headers?.get("Authorization")}")

                            GlobalApplication.prefs.setString("nickname",
                                "${ response?.body()?.name }")

                            GlobalApplication.prefs.setString("profile",
                                "${ response?.body()?.profile }")


                            GlobalApplication.prefs.setString("accessToken",
                                "${ response?.raw()?.headers?.get("Authorization") }")
                        }

                        override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
                            Log.d("LoginAPI", t.message.toString())
                        }
                    })

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        }

        //클릭리스너
        binding.btnKakao.setOnClickListener {
            Log.d("LOGIN", "로그인 시도")

            // 카카오톡 설치 됨 -> 카카오 로그인, 설치 안됨 -> 카카오 계정으로 로그인
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }

            // 토큰 정보 보기
            UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
                if (error != null) {
                    Log.e("LOGIN", "토큰 정보 보기 실패", error)
                } else if (tokenInfo != null) {
                    Log.i(
                        "LOGIN", "토큰 정보 보기 성공" +
                                "\n회원번호: ${tokenInfo.id}" +
                                "\n만료시간: ${tokenInfo.expiresIn} 초"
                    )
                }
            }



        }
    }
}