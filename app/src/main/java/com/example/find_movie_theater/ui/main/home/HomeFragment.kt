package com.example.find_movie_theater.ui.main.home

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.find_movie_theater.R
import com.example.find_movie_theater.data.entities.LocationData
import com.example.find_movie_theater.databinding.FragmentHomeBinding
import com.example.find_movie_theater.ui.BaseFragment
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

    override fun initAfterBinding() {
        if (!hasPermission()) {
            requestPermissions(PERMISSIONS, LOCATION_PERMISSION_REQUEST_CODE)
        } else {
            initMapView()
        }

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

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        naverMap.locationSource = locationSource
        naverMap.uiSettings.isLocationButtonEnabled = true
        naverMap.locationTrackingMode = LocationTrackingMode.Follow

        val locationList: ArrayList<LocationData> = arrayListOf(
            LocationData(126.897348, 37.5682507),
            LocationData(126.941194, 37.5597128),
            LocationData(126.922071, 37.5560525)
        )

        locationList.forEach { locationData ->
            addMarker(locationData.latitude ?: 0.0, locationData.longitude ?: 0.0)
        }

        naverMap.setOnMapClickListener { point, coord ->
            addMarker(coord.latitude, coord.longitude)
        }
    }

    private fun addMarker(latitude: Double, longitude: Double) {
        val newMarker = Marker()
        newMarker.position = LatLng(latitude, longitude)
        newMarker.map = naverMap

        // 새로운 마커를 리스트에 추가
        markers.add(newMarker)

        // 마커 클릭 이벤트 처리
        newMarker.setOnClickListener { overlay ->
            // overlay는 Marker 인스턴스
            val clickedMarker = overlay as Marker
            val markerIndex = markers.indexOf(clickedMarker)
            //Toast.makeText(requireActivity(), "마커 $markerIndex 클릭", Toast.LENGTH_SHORT).show()
            Log.d("clickedMarker.position.latitude : ","${clickedMarker.position.latitude}")
            Log.d("clickedMarker.position.longitude : ","${clickedMarker.position.longitude}")
            getAddressInBackground(clickedMarker.position.latitude,clickedMarker.position.longitude)
            true
        }
    }
}
