package com.example.find_movie_theater.ui.main.home.result

import com.example.find_movie_theater.data.blogsearch.BlogResponse

interface ResultView {
    fun onResultLoading()
    fun onResultSuccess(resp : BlogResponse)
    fun onResultFailure()
}