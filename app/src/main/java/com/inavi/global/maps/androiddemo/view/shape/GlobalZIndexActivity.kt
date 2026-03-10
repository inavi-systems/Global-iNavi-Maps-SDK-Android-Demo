package com.inavi.global.maps.androiddemo.view.shape

import android.graphics.Color
import androidx.core.graphics.ColorUtils
import com.inavi.global.maps.androiddemo.R
import com.inavi.global.maps.androiddemo.databinding.ActivityGlobalZIndexBinding
import com.inavi.global.maps.androiddemo.utils.extension.setCheckListener
import com.inavi.global.maps.androiddemo.view.map.IgmMapFragmentActivity
import com.inavi.global.mapsdk.constants.IgmConstants
import com.inavi.global.mapsdk.geometry.LatLng
import com.inavi.global.mapsdk.maps.IgmCameraPosition
import com.inavi.global.mapsdk.maps.IgmMap
import com.inavi.global.mapsdk.maps.IgmMapOptions
import com.inavi.global.mapsdk.style.shapes.IgmCircle
import com.inavi.global.mapsdk.style.shapes.IgmPolyline

class GlobalZIndexActivity : IgmMapFragmentActivity<ActivityGlobalZIndexBinding>(
    layoutResId = R.layout.activity_global_z_index,
    options = IgmMapOptions().camera(INIT_CAMERA),
) {

    override fun onMapReady(igmMap: IgmMap) {
        IgmCircle().apply {
            center = LatLng(25.033976, 121.561594)
            radius = 500.0
            fillColor = ColorUtils.setAlphaComponent(Color.RED, 127)
            strokeColor = Color.RED
            strokeWidth = resources.getDimensionPixelSize(R.dimen.shape_line_width).toFloat()
            map = igmMap
        }

        val polyline = IgmPolyline().apply {
            coords = POLYLINE_COORDS
            color = Color.BLUE
            width = resources.getDimensionPixelSize(R.dimen.shape_line_width)
            map = igmMap
        }

        binding.polylineUnderCircle.setCheckListener { isChecked ->
            polyline.globalZIndex = when (!isChecked) {
                true -> IgmCircle.DEFAULT_GLOBAL_Z_INDEX + 1
                else -> IgmCircle.DEFAULT_GLOBAL_Z_INDEX - 1
            }
        }
    }

    companion object {
        private val INIT_CAMERA = IgmCameraPosition(LatLng(25.033976, 121.561964), 14.0, 0.0, 0.0)

        private val POLYLINE_COORDS = listOf(
            LatLng(25.040936, 121.565194),
            LatLng(25.036436, 121.561054),
            LatLng(25.032496, 121.567094),
            LatLng(25.031236, 121.559584),
            LatLng(25.026706, 121.562464)
        )
    }
}
