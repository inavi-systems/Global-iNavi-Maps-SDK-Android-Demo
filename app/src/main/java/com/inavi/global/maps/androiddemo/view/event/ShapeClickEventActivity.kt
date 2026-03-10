package com.inavi.global.maps.androiddemo.view.event

import android.graphics.Color
import android.graphics.PointF
import android.widget.Toast
import com.inavi.global.maps.androiddemo.R
import com.inavi.global.maps.androiddemo.databinding.ActivityClickShapeBinding
import com.inavi.global.maps.androiddemo.utils.extension.setCheckListener
import com.inavi.global.maps.androiddemo.view.map.IgmMapFragmentActivity
import com.inavi.global.mapsdk.geometry.LatLng
import com.inavi.global.mapsdk.maps.IgmMap
import com.inavi.global.mapsdk.style.shapes.IgmMarker

class ShapeClickEventActivity : IgmMapFragmentActivity<ActivityClickShapeBinding>(R.layout.activity_click_shape),
    IgmMap.OnMapClickListener {

    override fun onMapReady(igmMap: IgmMap) {
        val igmMarker = IgmMarker().apply {
            tag = 0
            position = igmMap.cameraPosition.target
            titleColor = Color.rgb(255, 0, 0)
            titleSize = 14f
            map = igmMap
        }

        igmMap.setOnMapClickListener(this)

        binding.chkClickShapeClick.setCheckListener { isChecked ->
            when (isChecked) {
                true -> {
                    igmMarker.setOnClickListener {
                        igmMarker.tag = (igmMarker.tag as Int) + 1
                        igmMarker.title = "Click the marker ${igmMarker.tag} times"
                        binding.chkClickShapeConsumeClick.isChecked
                    }
                }

                false -> {
                    igmMarker.setOnClickListener(null)
                }
            }

            binding.chkClickShapeConsumeClick.isEnabled = isChecked
        }

        binding.chkClickShapeConsumeClick.setCheckListener()
    }


    override fun onMapClick(point: PointF, coords: LatLng) {
        Toast.makeText(
            this,
            getString(
                R.string.igm_format_event_map_click,
                "클릭",
                point.x,
                point.y,
                coords.latitude,
                coords.longitude
            ),
            Toast.LENGTH_SHORT
        ).show()
    }
}
