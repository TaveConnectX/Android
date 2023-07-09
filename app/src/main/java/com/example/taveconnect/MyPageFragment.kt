package com.example.taveconnect

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.taveconnect.databinding.FragmentMypageBinding
import com.example.taveconnect.rank.MyRankData
import com.example.taveconnect.retrofit.RetrofitClient
import com.example.taveconnect.retrofit.RetroiftAPI
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageFragment : Fragment(R.layout.fragment_mypage) {
    private var _binding: FragmentMypageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showInit()
        myRankingAPI()
        kakaoLogout()





    }


    // 프로필 연결
    private fun showInit() {

        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e("PROFILE", "마이페이지 프로필 연결 실패", error)
            }

            else if (user != null) {
                Log.i("PROFILE", "마이페이지 연결 성공")
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




    private fun kakaoLogout() {

        binding.btnLogout.setOnClickListener {
            // 로그아웃
            UserApiClient.instance.logout { error ->
                if (error != null) {
                    Log.e("LOGOUT", "로그아웃 실패", error)
                }
                else {
                    Log.i("LOGOUT", "로그아웃 성공")
                }
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP))
            }
        }
    }





    fun myRankingAPI() {
        val rankAPI = RetrofitClient.getInstance().create(RetroiftAPI::class.java)

        rankAPI.getMyRanking()
            .enqueue(object: Callback<MyRankData> {
                override fun onResponse(call: Call<MyRankData>, response: Response<MyRankData>) {
                    if (response.isSuccessful) {
                        Log.d("MyRankAPI", "성공 ${response.body().toString()}")
                        GlobalApplication.prefs.setString("ranking", "${response?.body()?.ranking}")
                        GlobalApplication.prefs.setString("draw", "${response?.body()?.draw}")
                        GlobalApplication.prefs.setString("defeat", "${response?.body()?.defeat}")
                        GlobalApplication.prefs.setString("victory", "${response?.body()?.victory}")

                        binding.tvLoseCount.text = GlobalApplication.prefs.getString("defeat", "")
                        binding.tvMyRank.text = GlobalApplication.prefs.getString("ranking", "")
                        binding.tvNormalCount.text = GlobalApplication.prefs.getString("draw", "")
                        binding.tvWinCount.text = GlobalApplication.prefs.getString("victory", "")
                    }
                }

                override fun onFailure(call: Call<MyRankData>, t: Throwable) {
                    Log.d("MyRankAPI", "실패")
                }
            })

    }
}