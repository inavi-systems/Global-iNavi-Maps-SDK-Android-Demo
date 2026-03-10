package com.inavi.global.maps.androiddemo.view.option

import android.graphics.PointF
import android.view.View
import com.inavi.global.maps.androiddemo.R
import com.inavi.global.maps.androiddemo.databinding.ActivityMapOptionPaddingBinding
import com.inavi.global.maps.androiddemo.utils.extension.dpToPx
import com.inavi.global.maps.androiddemo.utils.extension.setCheckListener
import com.inavi.global.maps.androiddemo.view.map.IgmMapFragmentActivity
import com.inavi.global.mapsdk.maps.CameraUpdate
import com.inavi.global.mapsdk.maps.IgmMap
import com.inavi.global.mapsdk.style.shapes.IgmMarker
import com.inavi.global.mapsdk.style.shapes.IgmMarkerIcons

class MapPaddingActivity : IgmMapFragmentActivity<ActivityMapOptionPaddingBinding>(R.layout.activity_map_option_padding) {

    override fun onMapReady(igmMap: IgmMap) {
        val marker = IgmMarker().apply {
            position = igmMap.cameraPosition.target
            iconImage = IgmMarkerIcons.RED
            anchor = PointF(0.5f,0.5f)
            map = igmMap
        }

        binding.chkPadding.setCheckListener { isChecked ->
            when (isChecked) {
                true -> {
                    igmMap.setPadding(25.dpToPx(), 25.dpToPx(), 100.dpToPx(), 100.dpToPx())
                    binding.clContentOverlay.visibility = View.VISIBLE
                }

                false -> {
                    igmMap.setPadding(0, 0, 0, 0)
                    binding.clContentOverlay.visibility = View.GONE
                }
            }

            igmMap.moveCamera(CameraUpdate.targetTo(marker.position))
        }
    }
}
