package com.example.find_movie_theater.ui.main.home

import com.example.find_movie_theater.R
import com.example.find_movie_theater.databinding.FragmentHomeBinding
import com.example.find_movie_theater.ui.BaseFragment
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMapSdk


class HomeFragment(): BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override fun initAfterBinding() {
        // 네이버 지도 SDK 초기화
        NaverMapSdk.getInstance(requireContext()).client =
            NaverMapSdk.NaverCloudPlatformClient("tn8gmubytk")

        // MapFragment를 사용하여 네이버 지도를 Fragment에 추가
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_container) as MapFragment?
        if (mapFragment == null) {
            val newMapFragment = MapFragment.newInstance()
            childFragmentManager.beginTransaction()
                .add(R.id.map_container, newMapFragment)
                .commit()
        }

        mapFragment?.getMapAsync { naverMap ->
            // 네이버 지도가 준비되었을 때 호출되는 콜백
            val seoul = LatLng(37.5666102, 126.9783881) // 좌표 설정 (서울 시청)
            naverMap.moveCamera(CameraUpdate.scrollTo(seoul))
        }

    }
}