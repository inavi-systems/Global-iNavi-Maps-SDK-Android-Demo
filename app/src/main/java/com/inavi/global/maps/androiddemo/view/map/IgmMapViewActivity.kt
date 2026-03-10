package com.inavi.global.maps.androiddemo.view.map

import android.os.Bundle
import com.inavi.global.maps.androiddemo.R
import com.inavi.global.maps.androiddemo.base.IgmBaseActivity
import com.inavi.global.maps.androiddemo.databinding.ActivityIgmMapViewBinding
import com.inavi.global.mapsdk.maps.IgmMap

class IgmMapViewActivity : IgmBaseActivity<ActivityIgmMapViewBinding>(R.layout.activity_igm_map_view) {

    override fun init(savedInstanceState: Bundle?) {
        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync(this@IgmMapViewActivity)
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    override fun onMapReady(igmMap: IgmMap) {}
}
