package com.inavi.global.maps.androiddemo.view.shape

import android.graphics.Color
import android.os.Bundle
import android.widget.Checkable
import androidx.core.graphics.ColorUtils
import com.inavi.global.maps.androiddemo.R
import com.inavi.global.maps.androiddemo.databinding.ActivityIgmShapeRemoveBinding
import com.inavi.global.maps.androiddemo.view.map.IgmMapFragmentActivity
import com.inavi.global.mapsdk.constants.IgmConstants
import com.inavi.global.mapsdk.geometry.LatLng
import com.inavi.global.mapsdk.maps.IgmCameraPosition
import com.inavi.global.mapsdk.maps.IgmMap
import com.inavi.global.mapsdk.maps.IgmMapOptions
import com.inavi.global.mapsdk.style.shapes.IgmPolygon

class IgmPolygonActivity : IgmMapFragmentActivity<ActivityIgmShapeRemoveBinding>(
    layoutResId = R.layout.activity_igm_shape_remove,
    options = IgmMapOptions().camera(INIT_CAMERA),
) {

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        binding.rmShape.text = getString(R.string.igm_remove_polygon)
    }

    override fun onMapReady(igmMap: IgmMap) {
        val polygon1 = IgmPolygon().apply {
            coords = POLYGON1_COORDS
            fillColor = ColorUtils.setAlphaComponent(Color.BLUE, 127)
            strokeColor = Color.BLUE
            strokeWidth = resources.getDimensionPixelSize(R.dimen.shape_line_width).toFloat()
            map = igmMap
        }

        val polygon2 = IgmPolygon().apply {
            coords = POLYGON2_COORDS
            fillColor = ColorUtils.setAlphaComponent(Color.YELLOW, 127)
            strokeColor = Color.BLACK
            strokeWidth = resources.getDimensionPixelSize(R.dimen.shape_line_width).toFloat()
            map = igmMap
        }

        val allPolygons = listOf(polygon1, polygon2)

        binding.rmShape.setOnClickListener { v ->
            if (v is Checkable) {
                val checked = v.isChecked
                v.isChecked = !checked

                allPolygons.forEach { polygon ->
                    polygon.map = when (checked) {
                        true -> igmMap
                        else -> null
                    }
                }
            }
        }
    }

    companion object {
        private val INIT_CAMERA = IgmCameraPosition(IgmConstants.POSITION_DEFAULT.target, 14.0, 0.0, 0.0)

        private val POLYGON1_COORDS = listOf(
            LatLng(25.04210, 121.56486),
            LatLng(25.04111, 121.56875),
            LatLng(25.03734, 121.56915),
            LatLng(25.03661, 121.56461),
            LatLng(25.03951, 121.56085)
        )

        private val POLYGON2_COORDS = listOf(
            LatLng(25.03141, 121.56214),
            LatLng(25.03267, 121.56965),
            LatLng(25.02591, 121.56950),
            LatLng(25.02750, 121.56202)
        )
    }
}
