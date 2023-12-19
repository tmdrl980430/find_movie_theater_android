package com.example.find_movie_theater.data.entities

import com.google.gson.annotations.SerializedName


data class User(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("name") val name: String
)
