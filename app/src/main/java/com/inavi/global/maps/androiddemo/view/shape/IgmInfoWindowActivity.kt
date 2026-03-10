package com.inavi.global.maps.androiddemo.view.shape

import android.widget.Checkable
import com.inavi.global.maps.androiddemo.R
import com.inavi.global.maps.androiddemo.adapter.CustomInfoWindowViewAdapter
import com.inavi.global.maps.androiddemo.adapter.InfoWindowAdapter
import com.inavi.global.maps.androiddemo.databinding.ActivityIgmInfoWindowBinding
import com.inavi.global.maps.androiddemo.view.map.IgmMapFragmentActivity
import com.inavi.global.mapsdk.geometry.LatLng
import com.inavi.global.mapsdk.maps.IgmMap
import com.inavi.global.mapsdk.style.shapes.IgmInfoWindow
import com.inavi.global.mapsdk.style.shapes.IgmMarker
import com.inavi.global.mapsdk.style.shapes.IgmMarkerIcons


class IgmInfoWindowActivity : IgmMapFragmentActivity<ActivityIgmInfoWindowBinding>(R.layout.activity_igm_info_window) {

    override fun onMapReady(igmMap: IgmMap) {
        val defaultAdapter = InfoWindowAdapter(this)
        val customAdapter = CustomInfoWindowViewAdapter(this)

        val infoWindow = IgmInfoWindow().apply {
            position = LatLng(25.034146, 121.56452)
            adapter = defaultAdapter
            setOnClickListener {
                map = null
                true
            }
        }
        infoWindow.map = igmMap

        IgmMarker().apply {
            position = LatLng(25.033346, 121.56352)
            iconImage = IgmMarkerIcons.RED
            setOnClickListener {
                infoWindow.marker = this
                if (!infoWindow.isAttached()) {
                    infoWindow.map = igmMap
                }
                true
            }
            tag = "RED"
            map = igmMap
        }

        IgmMarker().apply {
            position = LatLng(25.038722, 121.56524)
            iconImage = IgmMarkerIcons.BLUE
            setOnClickListener {
                infoWindow.marker = this
                if (!infoWindow.isAttached()) {
                    infoWindow.map = igmMap
                }
                true
            }
            tag = "BLUE"
            map = igmMap
        }

        igmMap.setOnMapClickListener { _, coords ->
            infoWindow.position = coords
            if (!infoWindow.isAttached()) {
                infoWindow.map = igmMap
            }
        }

        binding.useCustomInfoWindowAdapter.setOnClickListener { v ->
            if (v is Checkable) {
                val checked = v.isChecked
                v.isChecked = !checked
                infoWindow.adapter = when (!checked) {
                    true -> customAdapter
                    else -> defaultAdapter
                }
            }
        }
    }
}
