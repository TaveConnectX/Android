package com.example.taveconnect

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.kakao.sdk.user.UserApiClient
import com.example.taveconnect.databinding.ActivityLoginBinding
import com.example.taveconnect.retrofit.RetrofitClient
import com.example.taveconnect.retrofit.RetroiftAPI
import retrofit2.*


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val api = RetrofitClient.getInstance().create(RetroiftAPI::class.java)

        api.getAPI()
            .enqueue(object: Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Log.d("API", "API 테스트 성공 ${GlobalApplication.prefs?.getString("accessToken", "")}")
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d("API", "API 테스트 실패")
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
                binding.tvName.text = GlobalApplication.prefs.getString("nickname", "")
                val profile = GlobalApplication.prefs.getString("profile", "")

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