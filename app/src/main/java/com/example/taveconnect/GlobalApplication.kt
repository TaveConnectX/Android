package com.example.taveconnect

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // KaKao SDK 초기화
        KakaoSdk.init(this, "{NATIVE_APP_KEY}")
    }
}