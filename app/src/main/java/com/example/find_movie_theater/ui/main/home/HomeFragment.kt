package com.example.find_movie_theater.ui.main.home

import android.util.Log
import com.example.find_movie_theater.R
import com.example.find_movie_theater.databinding.FragmentHomeBinding
import com.example.find_movie_theater.ui.BaseFragment
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.NaverMapSdk
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    OnMapReadyCallback {

    private val LOCATION_PERMISSION_REQUEST_CODE = 5000
    private val marker = Marker()



    private lateinit var naverMap: NaverMap
    private lateinit var locationSource: FusedLocationSource

    override fun initAfterBinding() {
        // 네이버 지도 SDK 초기화
        NaverMapSdk.getInstance(requireContext()).client =
            NaverMapSdk.NaverCloudPlatformClient("tn8gmubytk")

        // MapFragment를 사용하여 네이버 지도를 Fragment에 추가
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_container) as MapFragment?
            ?: MapFragment.newInstance().also {
                childFragmentManager.beginTransaction()
                    .add(R.id.map_container, it)
                    .commit()
            }

        // fragment의 getMapAsync() 메서드로 OnMapReadyCallback 콜백을 등록하면 비동기로 NaverMap 객체를 얻을 수 있습니다.
        mapFragment.getMapAsync(this)

        // FusedLocationSource 초기화
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
    }

    // onMapReady에서 네이버 지도가 준비되었을 때 호출되는 콜백에서 현재 위치 설정 및 기능 추가
    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap

        // 현재 위치를 표시하기 위해 FusedLocationSource를 사용합니다.
        naverMap.locationSource = locationSource

        // 현재 위치 버튼 활성화
        naverMap.uiSettings.isLocationButtonEnabled = true

        // 위치를 추적하면서 카메라도 따라 움직이도록 설정
        naverMap.locationTrackingMode = LocationTrackingMode.Follow

        // 지도가 클릭 되면 onMapClick() 콜백 메서드가 호출 되며, 파라미터로 클릭된 지점의 화면 좌표와 지도 좌표가 전달 된다.
        naverMap.setOnMapClickListener { point, coord ->
            Log.d("this", "${coord.latitude}, ${coord.longitude}")
            marker.position = LatLng(coord.latitude, coord.longitude)
            marker.map = naverMap


        }

        // 지도가 롱 클릭 되면 onMapLongClick() 콜백 메서드가 호출 되며, 파라미터로 클릭된 지점의 화면 좌표와 지도 좌표가 전달 된다.
        naverMap.setOnMapLongClickListener { point, coord ->

            Log.d("this", "${coord.latitude}, ${coord.longitude}")
        }

        // 네이버 지도가 준비되면 현재 위치를 표시하는 코드를 추가할 수 있습니다.
        // 예를 들어, 서울 시청으로 카메라 이동
        val seoul = LatLng(37.5666102, 126.9783881)
        naverMap.moveCamera(CameraUpdate.scrollTo(seoul))
    }
}