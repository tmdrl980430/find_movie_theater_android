package com.example.find_movie_theater.ui.main.home.result

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.find_movie_theater.data.blogsearch.BlogResponse
import com.example.find_movie_theater.data.blogsearch.BlogService
import com.example.find_movie_theater.databinding.ActivityResultBinding
import com.example.find_movie_theater.ui.BaseActivity
import com.example.find_movie_theater.ui.main.MainActivity
import com.example.find_movie_theater.ui.main.search.adapter.SearchBlogRVAdapter

class ResultActivity : BaseActivity<ActivityResultBinding>(ActivityResultBinding::inflate) , ResultView{
    override fun initAfterBinding() {

        setClickListeners()

        // 이 부분에서 intent를 사용할 수 있습니다.
        val receivedIntent = intent

        // Intent로 전달된 데이터를 확인합니다.
        //수용
        val locationName = receivedIntent.getStringExtra("selectLocationName")

        // 받아온 데이터를 사용하거나 처리합니다.
        // 예시: TextView에 데이터를 설정하는 경우
        binding.resultLocationNameTv.text = locationName

        //이 이름을 바탕으로 네이버 검색 API 를 사용하여 데이터를 가져온다.

        val naverBlogService = BlogService(this)
        naverBlogService.searchNaverBlog(locationName.toString())



        //intent.putExtra("blogUrl", blogUrl)
        //startNextActivity(WebViewActivity::class.java)

    }

    fun setClickListeners() {
        binding.resultBackBtn.setOnClickListener{
            startActivityWithClear(MainActivity::class.java)

        }
    }






    override fun onResultLoading() {

    }

    override fun onResultSuccess(resp : BlogResponse) {

        val SearchAdapter = SearchBlogRVAdapter(resp.items)
        binding.searchRvBlog.adapter = SearchAdapter
        binding.searchRvBlog.layoutManager = LinearLayoutManager(this).also { it.orientation = LinearLayoutManager.VERTICAL }

    }

    override fun onResultFailure() {

    }


}