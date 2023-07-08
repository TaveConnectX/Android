package com.example.taveconnect

import android.app.Application
import com.kakao.sdk.common.KakaoSdk


class GlobalApplication : Application() {

    companion object {
        lateinit var prefs: PreferenceUtil
    }

    override fun onCreate() {
        super.onCreate()

        prefs = PreferenceUtil(applicationContext)

        // KaKao SDK 초기화
        KakaoSdk.init(this, getString(R.string.kakao_native_app_key))

    }
}