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
import com.inavi.global.mapsdk.style.shapes.IgmCircle

class IgmCircleActivity : IgmMapFragmentActivity<ActivityIgmShapeRemoveBinding>(
    layoutResId = R.layout.activity_igm_shape_remove,
    options = IgmMapOptions().camera(INIT_CAMERA),
) {

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        binding.rmShape.text = getString(R.string.igm_remove_circle)
    }

    override fun onMapReady(igmMap: IgmMap) {
        val circle1 = IgmCircle().apply {
            center = LatLng(25.033976, 121.561964)
            radius = 300.0
            fillColor = ColorUtils.setAlphaComponent(Color.RED, 127)
            strokeColor = Color.RED
            strokeWidth = resources.getDimensionPixelSize(R.dimen.shape_line_width).toFloat()
            map = igmMap
        }

        val circle2 = IgmCircle().apply {
            center = LatLng(25.026566, 121.562354)
            radius = 200.0
            fillColor = ColorUtils.setAlphaComponent(Color.GREEN, 127)
            strokeColor = Color.GREEN
            strokeWidth = resources.getDimensionPixelSize(R.dimen.shape_line_width).toFloat()
            map = igmMap
        }

        val allCircles = listOf(circle1, circle2)

        binding.rmShape.setOnClickListener { v ->
            if (v is Checkable) {
                val checked = v.isChecked
                v.isChecked = !checked

                allCircles.forEach { circle ->
                    circle.map = when (checked) {
                        true -> igmMap
                        else -> null
                    }
                }
            }
        }
    }

    companion object {
        private val INIT_CAMERA = IgmCameraPosition(LatLng(25.033976, 121.561964), 14.0, 0.0, 0.0)
    }
}
