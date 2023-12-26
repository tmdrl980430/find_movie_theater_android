    package com.example.find_movie_theater.data.moviesearch

    import android.util.Log
    import com.example.find_movie_theater.ApplicationClass
    import com.example.find_movie_theater.ui.main.MainView
    import retrofit2.Call
    import retrofit2.Callback
    import retrofit2.Response

    class MovieService (val view: MainView) {

        fun searchNaverMovie(movieName : String) {
            val searchNaverMovieInterface = ApplicationClass.retrofit_naver.create(MovieRetrofitInterface::class.java)

            searchNaverMovieInterface.getSearchMovie(ApplicationClass.CLIENT_ID,ApplicationClass.CLIENT_SECRET, movieName)
                .enqueue(object : Callback<MovieSearchResponse> {
                    override fun onResponse(call: Call<MovieSearchResponse>, response: Response<MovieSearchResponse>) {
                        val resp = response.body()?.items

                        Log.d("naver",resp.toString() )



                    }

                    override fun onFailure(call: Call<MovieSearchResponse>, t: Throwable) {
                        Log.d("${ApplicationClass.TAG}/API-ERROR+naver", t.message.toString())


                    }
                })
        }

        fun searchMovie(yesterdayDate : String) {
            val searchMovieInterface= ApplicationClass.retrofit.create(MovieRetrofitInterface::class.java)


            searchMovieInterface.getSearchMovies("148d384108e94628a590d2399bfc4296", yesterdayDate)
                .enqueue(object : Callback<PlayingMovieSearchResponse> {
                override fun onResponse(call: Call<PlayingMovieSearchResponse>, response: Response<PlayingMovieSearchResponse>) {
                    val resp = response.body()!!

                    Log.d("cccccccc",resp.boxOfficeResult.dailyBoxOfficeList[0].movieNm )

                    val movieNames = mutableListOf<String>()

                    // 데이터 클래스에서 영화 이름을 추출하여 리스트에 추가
                    resp.boxOfficeResult.dailyBoxOfficeList.forEach {
                        movieNames.add(it.movieNm)
                    }
                    Log.d("cccccccc",movieNames.toString() )
//                    when(resp.boxOfficeResult.dailyBoxOfficeList){
//                        1000 -> view.onMainSuccess()
//                        else -> view.onMainFailure()
//                    }

                }

                override fun onFailure(call: Call<PlayingMovieSearchResponse>, t: Throwable) {
                    Log.d("${ApplicationClass.TAG}/API-ERROR", t.message.toString())


                }
            })
        }

    }

