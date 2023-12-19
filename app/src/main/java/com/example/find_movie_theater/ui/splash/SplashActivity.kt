package com.example.find_movie_theater.ui.splash

import android.os.Handler
import android.os.Looper
import com.example.find_movie_theater.data.remote.auth.AuthService
import com.example.find_movie_theater.databinding.ActivitySplashBinding
import com.example.find_movie_theater.ui.BaseActivity
import com.example.find_movie_theater.ui.main.MainActivity


class SplashActivity: BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate), SplashView {

    override fun initAfterBinding() {
        Handler(Looper.getMainLooper()).postDelayed({
            autoLogin()
        }, 2000)
    }

    private fun autoLogin() {
        AuthService.autoLogin(this)
    }

    override fun onAutoLoginLoading() {

    }

    override fun onAutoLoginSuccess() {
        startActivityWithClear(MainActivity::class.java)
    }

    override fun onAutoLoginFailure(code: Int, message: String) {
        startActivityWithClear(MainActivity::class.java)
    }
}