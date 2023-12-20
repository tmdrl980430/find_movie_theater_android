package com.example.find_movie_theater

import android.app.Application
import android.content.SharedPreferences
import com.example.find_movie_theater.config.XAccessTokenInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApplicationClass : Application() {
    companion object{
        const val X_ACCESS_TOKEN: String = "X-ACCESS-TOKEN"         // JWT Token Key
        const val TAG: String = "TEMPLATE-APP"                      // Log, SharedPreference
        const val APP_DATABASE = "$TAG-DB"

        const val DEV_URL: String = "https://edu-api-test.softsquared.com";       // 테스트 서버 주소
        const val PROD_URL: String = "https://edu-api-test.softsquared.com"    // 실서버 주소
        const val NAVER_SEARCH_URL : String = "https://openapi.naver.com/"
        const val PLAYING_MOVIE_SEARCH_URL : String = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/"
        const val BASE_URL: String = DEV_URL

        const val CLIENT_ID : String = "M6M1Eeh14qwehoRSKCkn"
        const val CLIENT_SECRET : String = "pXSDdqr2C5"

        lateinit var mSharedPreferences: SharedPreferences
        lateinit var retrofit: Retrofit
        lateinit var retrofit_playing_naver_movie: Retrofit
    }

    override fun onCreate() {
        super.onCreate()

        val client: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(30000, TimeUnit.MILLISECONDS)
            .connectTimeout(30000, TimeUnit.MILLISECONDS)
            .addNetworkInterceptor(XAccessTokenInterceptor()) // JWT 자동 헤더 전송
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(PLAYING_MOVIE_SEARCH_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit_playing_naver_movie = Retrofit.Builder()
            .baseUrl(NAVER_SEARCH_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mSharedPreferences = applicationContext.getSharedPreferences(TAG, MODE_PRIVATE)
    }
}