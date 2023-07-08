package com.example.taveconnect.retrofit

import com.example.taveconnect.GlobalApplication
import com.example.taveconnect.retrofit.RetrofitClient.okHttpClient
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.IOException
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitClient {
    private const val BASE_URL = "https://cconnect.backlogs.dev"


    @Provides
    @Singleton
    fun okHttpClient(interceptor: AppInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    var gson = GsonBuilder().setLenient().create()

    private val client = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient(AppInterceptor()))
        .build()





    fun getInstance() : Retrofit {
        return client
    }


    class AppInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain) : Response = with(chain) {
            val accessToken = GlobalApplication.prefs.getString("accessToken", "") // ViewModel에서 지정한 key로 JWT 토큰을 가져온다.
            val newRequest = request().newBuilder()
                .addHeader("authorization", accessToken) // 헤더에 authorization라는 key로 JWT 를 넣어준다.
                .build()
            proceed(newRequest)
        }
    }
}