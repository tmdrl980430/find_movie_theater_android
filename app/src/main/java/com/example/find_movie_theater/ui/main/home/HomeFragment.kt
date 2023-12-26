package com.example.find_movie_theater.ui.main.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.find_movie_theater.R
import com.example.find_movie_theater.data.entities.LocationData
import com.example.find_movie_theater.databinding.FragmentHomeBinding
import com.example.find_movie_theater.ui.BaseFragment
import com.example.find_movie_theater.ui.main.home.result.ResultActivity
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import java.util.Locale

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    OnMapReadyCallback {

    private val LOCATION_PERMISSION_REQUEST_CODE = 5000
    private val PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )



    private lateinit var naverMap: NaverMap
    private lateinit var locationSource: FusedLocationSource
    private val markers = mutableListOf<Marker>()

    private lateinit var locationList : ArrayList<LocationData>

    override fun initAfterBinding() {
        if (!hasPermission()) {
            requestPermissions(PERMISSIONS, LOCATION_PERMISSION_REQUEST_CODE)
        } else {
            initMapView()
            setRadioClickListeners()
        }

    }

    fun changeHobby(hobby: String) {
        // 선택된 취미에 따라 locationList 업데이트
        locationList = when (hobby) {
            "movie" -> {
                arrayListOf(
                    LocationData(37.5597128, 126.941194, "메가박스 신촌"),
                    LocationData(37.5682507, 126.897348, "메가박스 상암"),
                    LocationData(37.5560525, 126.922071, "메가박스 홍대"),
                    LocationData(37.5293522, 126.876065, "메가박스 목동"),
                    LocationData(37.5405495, 126.837564, "메가박스 화곡"),
                    LocationData(37.5592959, 126.835214, "메가박스 마곡"),
                    LocationData(37.5670170, 127.007302, "메가박스 동대문"),
                    LocationData(37.5268373, 126.875017, "메가박스 더 부티크 목동현대백화점"),
                    LocationData(37.5043439, 127.003599, "메가박스 센트럴"),
                    LocationData(37.4846013, 126.981641, "메가박스 이수"),
                    LocationData(37.4979261, 127.026522, "메가박스 강남"),
                    LocationData(37.5003587, 127.026896, "메가박스 강남대로(씨티)"),
                    LocationData(37.5125020, 127.058796, "메가박스 코엑스"),
                    LocationData(37.5419436, 127.044641, "메가박스 성수"),
                    LocationData(37.5932132, 127.074665, "메가박스 상봉"),
                    LocationData(37.6545328, 127.038881, "메가박스 창동"),
                    LocationData(37.5530296, 127.073686, "메가박스 군자"),
                    LocationData(37.4458471, 126.894139, "메가박스 광명소하"),
                    LocationData(37.6476807, 126.896455, "메가박스 고양스타필드"),
                    LocationData(37.6424162, 126.792699, "메가박스 백석벨라시타"),
                    LocationData(37.4614606, 126.813898, "메가박스 부천스타필드시티"),
                    LocationData(37.5285159, 127.125126, "메가박스 강동"),
                    LocationData(37.6679058, 126.751484, "메가박스 킨텍스"),
                    LocationData(37.4187247, 126.882610, "메가박스 광명AK플라자"),
                    LocationData(37.4805933, 127.123645, "메가박스 송파파크하비오"),
                    LocationData(37.7057529, 126.758841, "메가박스 파주운정"),
                    LocationData(37.6558596, 127.126731, "메가박스 별내"),
                    LocationData(37.5881005, 126.675337, "메가박스 검단"),
                    LocationData(37.5664166, 127.189628, "메가박스 미사강변"),
                    LocationData(37.3722964, 126.944821, "메가박스 금정AK플라자"),
                    LocationData(37.6162298, 127.153735, "메가박스 남양주현대아울렛 스페이스원"),
                    LocationData(37.6898192, 126.756637, "메가박스 일산"),
                    LocationData(37.3973832, 126.727404, "메가박스 인천논현"),
                    LocationData(37.7458323, 127.095726, "메가박스 의정부민락"),
                    LocationData(37.3855201, 127.122305, "메가박스 분당"),
                    LocationData(37.7648602, 126.774503, "메가박스 파주금촌"),
                    LocationData(37.5336700, 126.655696, "메가박스 청라지젤"),
                    LocationData(37.7138021, 126.687762, "메가박스 파주출판도시"),
                    LocationData(37.3179251, 126.835623, "메가박스 안산중앙"),
                    LocationData(37.6449089, 126.624486, "메가박스 김포한강신도시"),
                    LocationData(37.3786370, 126.662822, "메가박스 송도"),
                    LocationData(37.3699173, 126.730055, "메가박스 시흥배곧"),
                    LocationData(37.6550639, 127.244054, "메가박스 남양주")
                )
            }
            "exercise" -> {
                arrayListOf(
                    LocationData(37.5816649, 126.885940, "골프존파크 상암 블랙점"),
                    LocationData(37.5816649, 126.885940, "여민블리스 상암점"),
                    LocationData(37.5816649, 126.885940, "골프존파크 상암 IT TOWER점"),
                    LocationData(37.5816649, 126.885940, "커브스 상암클럽"),
                    LocationData(37.5813690, 126.887424, "에이블짐 상암점"),
                    LocationData(37.5813690, 126.887424, "플라잉요가핏"),
                    LocationData(37.5813690, 126.887424, "H 필라테스"),
                )
            }
            else -> arrayListOf() // 다른 취미에 대한 처리 필요시 추가
        }

        // 지도에 있는 기존 마커들 제거
        markers.forEach { it.map = null }
        markers.clear()

        // 업데이트된 locationList를 기반으로 새로운 마커 추가
        locationList.forEach { locationData ->
            addMarker(locationData.latitude, locationData.longitude, locationData.captionText)
        }
    }

    // 라디오 버튼에 대한 클릭 리스너 설정 (각 라디오 버튼에 대해 필요한 만큼 추가)
    fun setRadioClickListeners() {
        binding.rdBtnHobby1.setOnClickListener { changeHobby("movie") }
        binding.rdBtnHobby2.setOnClickListener { changeHobby("exercise") }
        // 다른 라디오 버튼에 대한 리스너도 필요에 따라 추가
    }


    private fun initMapView() {
        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map_container) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map_container, it).commit()
            }

        mapFragment.getMapAsync(this)
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)


    }

    private fun hasPermission(): Boolean {
        return PERMISSIONS.all {
            ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
        }
    }
    private fun getAddressInBackground(latitude: Double, longitude: Double) {
        Thread {
            val geocoder = Geocoder(requireContext(), Locale.KOREAN)
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)

            if (addresses != null && addresses.isNotEmpty()) {
                val address = addresses[0].getAddressLine(0)
                //toast(address)
                toast(address)

            }
        }.start()
    }

    private fun toast(text: String) {
        requireActivity().runOnUiThread {
            Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onMapReady(naverMap: NaverMap ) {
        this.naverMap = naverMap
        naverMap.locationSource = locationSource
        naverMap.uiSettings.isLocationButtonEnabled = true
        naverMap.locationTrackingMode = LocationTrackingMode.Follow


        locationList = arrayListOf(
            LocationData(37.5597128, 126.941194, "메가박스 신촌"),
            LocationData(37.5682507, 126.897348, "메가박스 상암"),
            LocationData(37.5560525, 126.922071, "메가박스 홍대"),
            LocationData(37.5293522, 126.876065, "메가박스 목동"),
            LocationData(37.5405495, 126.837564, "메가박스 화곡"),
            LocationData(37.5592959, 126.835214, "메가박스 마곡"),
            LocationData(37.5670170, 127.007302, "메가박스 동대문"),
            LocationData(37.5268373, 126.875017, "메가박스 더 부티크 목동현대백화점"),
            LocationData(37.5043439, 127.003599, "메가박스 센트럴"),
            LocationData(37.4846013, 126.981641, "메가박스 이수"),
            LocationData(37.4979261, 127.026522, "메가박스 강남"),
            LocationData(37.5003587, 127.026896, "메가박스 강남대로(씨티)"),
            LocationData(37.5125020, 127.058796, "메가박스 코엑스"),
            LocationData(37.5419436, 127.044641, "메가박스 성수"),
            LocationData(37.5932132, 127.074665, "메가박스 상봉"),
            LocationData(37.6545328, 127.038881, "메가박스 창동"),
            LocationData(37.5530296, 127.073686, "메가박스 군자"),
            LocationData(37.4458471, 126.894139, "메가박스 광명소하"),
            LocationData(37.6476807, 126.896455, "메가박스 고양스타필드"),
            LocationData(37.6424162, 126.792699, "메가박스 백석벨라시타"),
            LocationData(37.4614606, 126.813898, "메가박스 부천스타필드시티"),
            LocationData(37.5285159, 127.125126, "메가박스 강동"),
            LocationData(37.6679058, 126.751484, "메가박스 킨텍스"),
            LocationData(37.4187247, 126.882610, "메가박스 광명AK플라자"),
            LocationData(37.4805933, 127.123645, "메가박스 송파파크하비오"),
            LocationData(37.7057529, 126.758841, "메가박스 파주운정"),
            LocationData(37.6558596, 127.126731, "메가박스 별내"),
            LocationData(37.5881005, 126.675337, "메가박스 검단"),
            LocationData(37.5664166, 127.189628, "메가박스 미사강변"),
            LocationData(37.3722964, 126.944821, "메가박스 금정AK플라자"),
            LocationData(37.6162298, 127.153735, "메가박스 남양주현대아울렛 스페이스원"),
            LocationData(37.6898192, 126.756637, "메가박스 일산"),
            LocationData(37.3973832, 126.727404, "메가박스 인천논현"),
            LocationData(37.7458323, 127.095726, "메가박스 의정부민락"),
            LocationData(37.3855201, 127.122305, "메가박스 분당"),
            LocationData(37.7648602, 126.774503, "메가박스 파주금촌"),
            LocationData(37.5336700, 126.655696, "메가박스 청라지젤"),
            LocationData(37.7138021, 126.687762, "메가박스 파주출판도시"),
            LocationData(37.3179251, 126.835623, "메가박스 안산중앙"),
            LocationData(37.6449089, 126.624486, "메가박스 김포한강신도시"),
            LocationData(37.3786370, 126.662822, "메가박스 송도"),
            LocationData(37.3699173, 126.730055, "메가박스 시흥배곧"),
            LocationData(37.6550639, 127.244054, "메가박스 남양주")
        )
        locationList.forEach { locationData ->
            addMarker(locationData.latitude, locationData.longitude, locationData.captionText)
        }


        naverMap.setOnMapClickListener { point, coord ->
            addMarker(coord.latitude, coord.longitude, "")
        }
    }

    private fun addMarker(latitude: Double, longitude: Double, locationName : String) {
        val newMarker = Marker()
        newMarker.position = LatLng(latitude, longitude)
        newMarker.map = naverMap
        newMarker.captionText = locationName
        Log.d("newMarker.position ","newMarker.position  : ${newMarker.position }")
        Log.d("newMarker.map ","newMarker.map  : ${newMarker.map }")

        // 새로운 마커를 리스트에 추가
        markers.add(newMarker)


        // 마커 클릭 이벤트 처리
        newMarker.setOnClickListener { overlay ->
            // overlay는 Marker 인스턴스
            val clickedMarker = overlay as Marker
            val markerIndex = markers.indexOf(clickedMarker)
            //Toast.makeText(requireActivity(), "마커 $markerIndex 클릭", Toast.LENGTH_SHORT).show()

            getAddressInBackground(clickedMarker.position.latitude,clickedMarker.position.longitude)
            //startNextActivity(this, ResultActivity::class.java)

            startResultActivity(clickedMarker.position.latitude, clickedMarker.position.longitude, locationName)
            true
        }
    }

    private fun startResultActivity(latitude: Double, longitude: Double, selectLocationName : String) {
        val intent = Intent(requireContext(), ResultActivity::class.java)
        intent.putExtra("latitude", latitude)
        intent.putExtra("longitude", longitude)
        intent.putExtra("selectLocationName", selectLocationName)
        startActivity(intent)
    }





}
