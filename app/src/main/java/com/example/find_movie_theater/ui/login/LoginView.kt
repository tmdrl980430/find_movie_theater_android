package com.example.find_movie_theater.ui.login

import com.example.find_movie_theater.data.remote.auth.Auth


interface LoginView {
    fun onLoginLoading()
    fun onLoginSuccess(auth: Auth)
    fun onLoginFailure(code: Int, message: String)
}