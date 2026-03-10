package com.inavi.global.maps.androiddemo.view.camera

import android.os.Bundle
import com.inavi.global.maps.androiddemo.R
import com.inavi.global.maps.androiddemo.databinding.ActivityFabBinding
import com.inavi.global.maps.androiddemo.view.map.IgmMapFragmentActivity
import com.inavi.global.mapsdk.constants.IgmConstants
import com.inavi.global.mapsdk.geometry.LatLng
import com.inavi.global.mapsdk.geometry.LatLngBounds
import com.inavi.global.mapsdk.maps.CameraAnimationType
import com.inavi.global.mapsdk.maps.CameraUpdate
import com.inavi.global.mapsdk.maps.IgmMap
import com.inavi.global.mapsdk.style.shapes.IgmMarker
import com.inavi.global.mapsdk.style.shapes.IgmMarkerIcons

class CameraFitBoundsActivity : IgmMapFragmentActivity<ActivityFabBinding>(R.layout.activity_fab),
    IgmMap.OnCameraChangeListener {
    private var isInitPosition = true

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        binding.fab.setImageResource(R.drawable.ic_control_camera_24dp)
    }

    override fun onMapReady(igmMap: IgmMap) {
        IgmMarker().apply {
            position = POSITION1
            iconImage = IgmMarkerIcons.RED
            map = igmMap
        }

        IgmMarker().apply {
            position = POSITION2
            iconImage = IgmMarkerIcons.BLUE
            map = igmMap
        }

        binding.fab.setOnClickListener {
            onClickFloatingActionButton(igmMap)
        }
    }

    override fun onCameraChange(reason: Int) {
        if (!isInitPosition) {
            return
        }

        if (reason != CameraUpdate.REASON_API_GESTURE) {
            return
        }

        isInitPosition = false
        binding.fab.setImageResource(R.drawable.ic_replay_24dp)
    }

    private fun onClickFloatingActionButton(igmMap: IgmMap) {
        val bounds = LatLngBounds(POSITION1, POSITION2)
        val padding = resources.getDimensionPixelSize(R.dimen.camera_bounds_padding)

        val newCameraPosition = when (isInitPosition) {
            true -> CameraUpdate.fitBounds(bounds, padding)
            else -> CameraUpdate.newCameraPosition(IgmConstants.POSITION_DEFAULT)
        }.setAnimationType(CameraAnimationType.Fly, 3000)

        igmMap.moveCamera(newCameraPosition)

        binding.fab.setImageResource(
            when (isInitPosition) {
                true -> R.drawable.ic_replay_24dp
                else -> R.drawable.ic_control_camera_24dp
            }
        )

        isInitPosition = !isInitPosition
    }

    companion object {
        private val POSITION1 = LatLng(24.157552, 120.66603)
        private val POSITION2 = LatLng(25.034146, 121.56452)
    }
}
