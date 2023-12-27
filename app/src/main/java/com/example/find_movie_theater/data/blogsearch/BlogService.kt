package com.example.find_movie_theater.data.blogsearch

import android.util.Log
import com.example.find_movie_theater.ApplicationClass
import com.example.find_movie_theater.ui.main.home.result.ResultView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BlogService (val view: ResultView){
    fun searchNaverBlog(blogName : String) {
        val searchNaverBlogInterface = ApplicationClass.retrofit_naver.create(
            BlogRetrofitInferface::class.java)

        searchNaverBlogInterface.getSearchBlog(
            ApplicationClass.CLIENT_ID,
            ApplicationClass.CLIENT_SECRET, blogName+" 후기")
            .enqueue(object : Callback<BlogResponse> {
                override fun onResponse(call: Call<BlogResponse>, response: Response<BlogResponse>) {
                    val resp = response.body()

                    Log.d("naver_blog",resp.toString() )


                    response.body()?.let {
                        if (true) view.onResultSuccess(response.body() as BlogResponse)
                    }

                }

                override fun onFailure(call: Call<BlogResponse>, t: Throwable) {
                    Log.d("${ApplicationClass.TAG}/API-ERROR+naver", t.message.toString())


                }
            })
    }

}