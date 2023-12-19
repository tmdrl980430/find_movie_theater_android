package com.example.find_movie_theater.utils

import com.example.find_movie_theater.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.example.find_movie_theater.ApplicationClass.Companion.mSharedPreferences


fun saveJwt(jwtToken: String) {
    val editor = mSharedPreferences.edit()
    editor.putString(X_ACCESS_TOKEN, jwtToken)

    editor.apply()
}

fun getJwt(): String? = mSharedPreferences.getString(X_ACCESS_TOKEN, null)
