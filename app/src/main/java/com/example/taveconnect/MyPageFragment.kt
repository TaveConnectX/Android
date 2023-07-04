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
import com.kakao.sdk.user.UserApiClient

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
}