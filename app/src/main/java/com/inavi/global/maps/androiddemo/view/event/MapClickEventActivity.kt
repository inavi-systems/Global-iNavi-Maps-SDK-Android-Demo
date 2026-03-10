package com.inavi.global.maps.androiddemo.view.event

import android.graphics.PointF
import android.widget.Toast
import com.inavi.global.maps.androiddemo.R
import com.inavi.global.maps.androiddemo.databinding.ActivityClickMapBinding
import com.inavi.global.maps.androiddemo.utils.extension.setCheckListener
import com.inavi.global.maps.androiddemo.view.map.IgmMapFragmentActivity
import com.inavi.global.mapsdk.geometry.LatLng
import com.inavi.global.mapsdk.maps.IgmMap

class MapClickEventActivity : IgmMapFragmentActivity<ActivityClickMapBinding>(R.layout.activity_click_map),
    IgmMap.OnMapClickListener,
    IgmMap.OnMapLongClickListener {

    override fun onMapReady(igmMap: IgmMap) {
        binding.chkClickMapClick.setCheckListener { isChecked ->
            when (isChecked) {
                true -> igmMap.setOnMapClickListener(this)
                false -> igmMap.setOnMapClickListener(null)
            }
        }

        binding.chkClickMapLongClick.setCheckListener { isChecked ->
            when (isChecked) {
                true -> igmMap.setOnMapLongClickListener(this)
                false -> igmMap.setOnMapLongClickListener(null)
            }
        }
    }

    override fun onMapClick(point: PointF, coords: LatLng) {
        Toast.makeText(
            this,
            getString(
                R.string.igm_format_event_map_click,
                "Click",
                point.x,
                point.y,
                coords.latitude,
                coords.longitude
            ),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onMapLongClick(point: PointF, coords: LatLng) {
        Toast.makeText(
            this,
            getString(
                R.string.igm_format_event_map_click,
                "Long Click",
                point.x,
                point.y,
                coords.latitude,
                coords.longitude
            ),
            Toast.LENGTH_SHORT
        ).show()
    }
}
