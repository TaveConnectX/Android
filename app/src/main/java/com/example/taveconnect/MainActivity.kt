package com.example.taveconnect

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast

import com.example.taveconnect.R
import com.example.taveconnect.databinding.ActivityLoginBinding
import com.example.taveconnect.databinding.ActivityMainBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import java.net.URISyntaxException


class MainActivity : AppCompatActivity() {

    private var doubleBackToExitPressedOnce = false
    private lateinit var webView: WebView
    private lateinit var webViewLayout: FrameLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
/*

        binding.webView.settings.run {
            javaScriptEnabled = true
            domStorageEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            setSupportMultipleWindows(true)
        }

        webView.webChromeClient = object: WebChromeClient() {
            // 팝업열기

            override fun onCreateWindow(
                view: WebView?,
                isDialog: Boolean,
                isUserGesture: Boolean,
                resultMsg: Message?
            ): Boolean {
                // 웹뷰 만들기
                var childWebView = view?.let { WebView(it.context) }
                childWebView?.run {
                    settings.run {
                        javaScriptEnabled = true
                        javaScriptCanOpenWindowsAutomatically = true
                        setSupportMultipleWindows(true)
                    }
                    if (view != null) {
                        layoutParams = view.layoutParams
                    }
                    if (view != null) {
                        webViewClient = view.webViewClient
                    }
                    if (view != null) {
                        webChromeClient = view.webChromeClient
                    }
                }

                // 화면에 추가하기
                webViewLayout.addView(childWebView)

                val transport = resultMsg?.obj as WebView.WebViewTransport
                transport.webView = childWebView
                resultMsg.sendToTarget()

                return true

            }

            // 팝업 닫기
            override fun onCloseWindow(window: WebView?) {
                super.onCloseWindow(window)

                webViewLayout.removeView(window)

            }
        }

        webView.webViewClient = object: WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                if (request != null) {
                    if (request.url.scheme == "https") {

                    }
                }
                if (request != null) {
                    if (request.url.scheme == "intent") {
                        try {
                            val intent = Intent.parseUri(request.url.toString(),
                                Intent.URI_INTENT_SCHEME)
                            if (intent.resolveActivity(packageManager) != null) {
                                startActivity(intent)
                                return true
                            }

                            val fallbackUrl =
                                intent.getStringExtra("browser_fallback_url")
                            if (fallbackUrl != null) {
                                if (view != null) {
                                    view.loadUrl(fallbackUrl)
                                }
                                return true
                            }
                        } catch (e: URISyntaxException) {

                        }
                    }
                }

                return false
            }
        }

        webView.loadUrl("https://www.googsu.com/kakao_talk_message.html")

*/



        // 키 해시 확인하기
        var keyHash = Utility.getKeyHash(this)
        Log.d("KeyHash", keyHash)

        //클릭리스너
        binding.btnKakao.setOnClickListener {
            Log.d("LOGIN", "로그인 시도")
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


            // 카카오톡 설치 됨 -> 카카오 로그인, 설치 안됨 -> 카카오 계정으로 로그인
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                    if (error != null) {
                        Log.e("LOGIN", "카카오톡으로 로그인 실패", error)

                        // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소
                        // 의도적은 로그인 취소로 판단, 카카오계정으로 로그인 시도 없이 뒤로가기
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }

                        // 카카오톡에 연결된 카카오 계정이 없는 경우 카카오 계정으로 로그인 시도
                        UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                    } else if (token != null) {
                        Log.i("LOGIN", "카카오톡으로 로그인 성공 ${token.accessToken}")

                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }

            // 토큰 정보 보기
            UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
                if (error != null) {
                    Log.e("LOGIN", "토큰 정보 보기 실패", error)
                }
                else if (tokenInfo != null) {
                    Log.i("LOGIN", "토큰 정보 보기 성공" +
                    "\n회원번호: ${tokenInfo.id}" +
                    "\n만료시간: ${tokenInfo.expiresIn} 초")
                }
            }
        }
    }
}