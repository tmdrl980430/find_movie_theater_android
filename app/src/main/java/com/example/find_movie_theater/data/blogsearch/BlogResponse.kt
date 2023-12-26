package com.example.find_movie_theater.data.blogsearch

import com.google.gson.annotations.SerializedName

data class BlogResponse (
    @SerializedName("lastBuildDate") val lastBuildDate : String,
    @SerializedName("total") val total : Int,
    @SerializedName("start") val start : Int,
    @SerializedName("display") val display : Int,
    @SerializedName("items") val items: ArrayList<BlogItem>,
    )

data class BlogItem (
    @SerializedName("title") val title : String,
    @SerializedName("link") val link : String,
    @SerializedName("description") val description : String,
    @SerializedName("bloggername") val bloggername : String,
    @SerializedName("bloggerlink") val bloggerlink : String,
    @SerializedName("postdate") val postdate : String
    )