package com.example.find_movie_theater.data.moviesearch

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieRetrofitInterface {
    @GET("v1/search/movie") // 예시: 영화 검색 API
    fun getSearchMovie(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Query("query") query: String,
        @Query("display") display: Int? = null,
        @Query("start") start: Int? = null
    ): Call<MovieSearchResponse> // MovieSearchResponse API 응답을 나타내는 데이터 클래스


    @GET("searchDailyBoxOfficeList.json") // 예시: 영화 검색 API
    fun getSearchMovies(
        @Query("key") key: String?,
        @Query("targetDt") targetDt: String?
    ): Call<PlayingMovieSearchResponse> // PlayingMovieSearchResponse API 응답을 나타내는 데이터 클래스



}