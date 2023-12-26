package com.example.find_movie_theater.data.blogsearch

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BlogRetrofitInferface {
    @GET("blog.json") // 예시: 블로그 검색 API
    fun getSearchBlog(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Query("query") query: String,
        @Query("display") display: Int = 10,
        @Query("sort") sort: String = "sim",
        @Query("start") start: Int = 1
    ): Call<BlogResponse> // PlayingMovieSearchResponse API 응답을 나타내는 데이터 클래스

}