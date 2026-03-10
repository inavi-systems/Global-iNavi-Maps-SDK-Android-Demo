package com.inavi.global.maps.androiddemo.view.shape

import android.graphics.Color
import android.graphics.PointF
import android.widget.Checkable
import com.inavi.global.maps.androiddemo.R
import com.inavi.global.maps.androiddemo.databinding.ActivityIgmShapeRemoveBinding
import com.inavi.global.maps.androiddemo.view.map.IgmMapFragmentActivity
import com.inavi.global.mapsdk.geometry.LatLng
import com.inavi.global.mapsdk.maps.IgmMap
import com.inavi.global.mapsdk.style.shapes.IgmImage
import com.inavi.global.mapsdk.style.shapes.IgmMarker
import com.inavi.global.mapsdk.style.shapes.IgmMarkerIcons

class IgmMarkerActivity : IgmMapFragmentActivity<ActivityIgmShapeRemoveBinding>(R.layout.activity_igm_shape_remove) {

    override fun onMapReady(igmMap: IgmMap) {
        val marker1 = IgmMarker().apply {
            position = LatLng(25.034146, 121.56452)
            title = "Title"
            map = igmMap
        }

        val marker2 = IgmMarker().apply {
            position = LatLng(25.036606, 121.56361)
            iconImage = IgmImage(R.drawable.inv_marker_right_bottom)
            anchor = PointF(0.9f,0.9f)
            angle = 90f
            map = igmMap
        }

        val marker3 = IgmMarker().apply {
            position = LatLng(25.034696, 121.56181)
            iconImage = IgmMarkerIcons.BLUE
            title = "Apply title color"
            titleColor = Color.GREEN
            map = igmMap
        }

        val marker4 = IgmMarker().apply {
            position = LatLng(25.031857, 121.56340)
            iconImage = IgmMarkerIcons.YELLOW
            titleSize = 16f
            title = "Apply title size"
            map = igmMap
        }

        val marker5 = IgmMarker().apply {
            position = LatLng(25.035196, 121.56651)
            iconImage = IgmMarkerIcons.GREEN
            alpha = 0.5f
            title = "Semi-transparent marker"
            map = igmMap
        }

        val marker6 = IgmMarker().apply {
            position = LatLng(25.032536, 121.56606)
            iconImage = IgmImage(R.drawable.ic_star_black_24dp)
            iconScale = 2.0f
            anchor = PointF(0.5f, 0.5f)
            map = igmMap
        }

        val allMarkers = listOf(marker1, marker2, marker3, marker4, marker5, marker6)

        binding.rmShape.setOnClickListener { v ->
            if (v is Checkable) {
                val checked = v.isChecked
                v.isChecked = !checked

                allMarkers.forEach { marker ->
                    marker.map = when (checked) {
                        true -> igmMap
                        else -> null
                    }
                }
            }
        }
    }
}
