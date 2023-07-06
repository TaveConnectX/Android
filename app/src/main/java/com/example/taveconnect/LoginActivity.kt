package com.example.taveconnect

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.taveconnect.R
import com.kakao.sdk.user.UserApiClient
import com.example.taveconnect.databinding.ActivityLoginBinding
import com.example.taveconnect.retrofit.ModelAPI
import com.example.taveconnect.retrofit.RetrofitClient
import com.example.taveconnect.retrofit.RetroiftAPI
import com.kakao.sdk.auth.Constants
import com.kakao.sdk.auth.model.AccessTokenResponse
import com.kakao.sdk.auth.model.OAuthToken
import okhttp3.internal.userAgent
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val api = RetrofitClient.getInstance().create(RetroiftAPI::class.java)

        api.getAPI()
            .enqueue(object: Callback<ModelAPI> {
                override fun onResponse(call: Call<ModelAPI>, response: Response<ModelAPI>) {
                    Log.d("API", "성공 ${response.body().toString()}")
                }

                override fun onFailure(call: Call<ModelAPI>, t: Throwable) {
                    Log.d("API", "API 테스트 ${t.message}")
                }
            })

        loginInfo()

        //버튼 지정
        binding.btnEnjoy.setOnClickListener {
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }



    }


    // 닉네임, 프로필 받아오기

    private fun loginInfo() {

        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e("LOGIN", "사용자 정보 요청 실패", error)
            }

            else if (user != null) {
                Log.i("LOGIN", "사용자 정보 요청 성공")
                binding.tvName.text = user.kakaoAccount?.profile?.nickname
                val profile = user.kakaoAccount?.profile?.profileImageUrl

                Glide.with(this)
                    .load(profile)
                    .placeholder(binding.ivProfile.drawable)
                    .error(binding.ivProfile.drawable)
                    .fallback(binding.ivProfile.drawable)
                    .circleCrop()
                    .into(binding.ivProfile)
            }
        }

    }
}