package com.example.find_movie_theater.ui.main

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.find_movie_theater.R
import com.example.find_movie_theater.data.moviesearch.MovieService
import com.example.find_movie_theater.databinding.ActivityMainBinding
import com.example.find_movie_theater.ui.BaseActivity
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class MainActivity: BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate), MainView {
    private lateinit var navHostFragment: NavHostFragment


    fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        val currentDate = Date(System.currentTimeMillis())
        return dateFormat.format(currentDate)
    }

    fun getYesterdayDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -1) // 현재 날짜에서 1일을 빼서 전날로 설정

        val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        val yesterdayDate = calendar.time

        return dateFormat.format(yesterdayDate)
    }

    override fun initAfterBinding() {

        Log.d("TAG", "start MainActivity!")



        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController: NavController = navHostFragment.findNavController()

        binding.mainBottomNavigation.setupWithNavController(navController)


        val formattedDate = getCurrentDate()
        Log.d("Date","현재 날짜: $formattedDate")

        val formattedYesterdayDate = getYesterdayDate()
        Log.d("Date","전날 날짜: $formattedYesterdayDate")


        val searchTerm = "서울의봄" // 여기에 실제 검색어를 넣으세요
        val encodedSearchTerm = encodeUTF8(searchTerm)

        Log.d("encodeUTF8","$searchTerm: $encodedSearchTerm")

        val naverMovieService = MovieService(this)
        naverMovieService.searchNaverMovie(encodedSearchTerm)

        val movieService = MovieService(this)
        movieService.searchMovie(formattedYesterdayDate)





    }

    fun encodeUTF8(value: String): String {
        return try {
            URLEncoder.encode(value, StandardCharsets.UTF_8.toString())
        } catch (e: UnsupportedEncodingException) {
            // 예외 처리: 인코딩을 지원하지 않을 경우
            e.printStackTrace()
            value // 원본 문자열을 그대로 반환
        }
    }

    override fun onMainLoading() {
        TODO("Not yet implemented")
    }

    override fun onMainSuccess() {
        Log.d("cccccccc","성공" )
    }

    override fun onMainFailure() {
        TODO("Not yet implemented")
    }
}