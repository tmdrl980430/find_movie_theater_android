package com.example.find_movie_theater.ui.main.home.result

import com.example.find_movie_theater.databinding.ActivityResultBinding
import com.example.find_movie_theater.ui.BaseActivity

class ResultActivity : BaseActivity<ActivityResultBinding>(ActivityResultBinding::inflate) {
    override fun initAfterBinding() {

        // 이 부분에서 intent를 사용할 수 있습니다.
        val receivedIntent = intent

        // Intent로 전달된 데이터를 확인합니다.
        val locationName = receivedIntent.getStringExtra("selectLocationName")

        // 받아온 데이터를 사용하거나 처리합니다.
        // 예시: TextView에 데이터를 설정하는 경우
        binding.resultLocationNameTv.text = locationName

        //이 이름을 바탕으로 네이버 검색 API 를 사용하여 데이터를 가져온다.


    }
}