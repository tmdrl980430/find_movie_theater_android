package com.example.find_movie_theater.data.moviesearch

import com.google.gson.annotations.SerializedName

data class MovieSearchResponse(
    @SerializedName("items") val items: List<MovieItem>,
    // 다른 필요한 속성들...
)

data class MovieItem(
    @SerializedName("title") val title: String,
    @SerializedName("link") val link: String,
    @SerializedName("image") val image: String
)

data class PlayingMovieSearchResponse(
    val boxOfficeResult: BoxOfficeResult
)

data class BoxOfficeResult(
    val boxofficeType: String,
    val showRange: String,
    val dailyBoxOfficeList: List<DailyBoxOfficeList>
)

data class DailyBoxOfficeList(
    val rnum: String,
    val rank: String,
    val rankInten: String,
    val rankOldAndNew: String,
    val movieCd: String,
    val movieNm: String,
    val openDt: String,
    val salesAmt: String,
    val salesShare: String,
    val salesInten: String,
    val salesChange: String,
    val salesAcc: String,
    val audiCnt: String,
    val audiInten: String,
    val audiChange: String,
    val audiAcc: String,
    val scrnCnt: String,
    val showCnt: String
)