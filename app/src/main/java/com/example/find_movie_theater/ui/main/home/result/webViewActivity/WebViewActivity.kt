package com.example.find_movie_theater.ui.main.home.result.webViewActivity

import android.annotation.SuppressLint
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.find_movie_theater.databinding.ActivityWebviewBinding
import com.example.find_movie_theater.ui.BaseActivity

class WebViewActivity : BaseActivity<ActivityWebviewBinding>(ActivityWebviewBinding::inflate) ,
    WebViewView {

    private lateinit var webView: WebView

    override fun initAfterBinding() {

        // 이 부분에서 intent를 사용할 수 있습니다.
        val receivedIntent = intent

        // Intent로 전달된 데이터를 확인합니다.
        //수용
        val blogUrl = receivedIntent.getStringExtra("blogUrl")

        Log.d("Blog_URL" , "${blogUrl}")


        webView = binding.webviewWebViewWv
        setupWebView()
        loadNaverWebView(blogUrl.toString())
    }
    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
    }

    private fun loadNaverWebView(url : String) {
        webView.loadUrl(url)
    }
    override fun onWebViewLoading() {

    }

    override fun onWebViewSuccess() {

    }

    override fun onWebViewFailure() {

    }


}