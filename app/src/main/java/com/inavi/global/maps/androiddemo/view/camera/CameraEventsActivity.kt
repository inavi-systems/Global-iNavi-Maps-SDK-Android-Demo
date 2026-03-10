package com.inavi.global.maps.androiddemo.view.camera

import android.view.View
import android.widget.Toast
import com.inavi.global.maps.androiddemo.R
import com.inavi.global.maps.androiddemo.databinding.ActivityCameraEventsBinding
import com.inavi.global.maps.androiddemo.view.map.IgmMapFragmentActivity
import com.inavi.global.mapsdk.geometry.LatLng
import com.inavi.global.mapsdk.maps.CameraAnimationType
import com.inavi.global.mapsdk.maps.CameraUpdate
import com.inavi.global.mapsdk.maps.IgmCameraPosition
import com.inavi.global.mapsdk.maps.IgmMap
import com.inavi.global.mapsdk.style.shapes.IgmMarker
import com.inavi.global.mapsdk.style.shapes.IgmMarkerIcons

class CameraEventsActivity : IgmMapFragmentActivity<ActivityCameraEventsBinding>(R.layout.activity_camera_events),
    IgmMap.OnCameraChangeListener,
    IgmMap.OnCameraIdleListener {

    private var igmMap: IgmMap? = null
    private var isInitPosition = true
    private var isMovingByButton = false

    override fun onMapReady(igmMap: IgmMap) {
        this.igmMap = igmMap

        IgmMarker().apply {
            position = CAMERA_POSITION1.target
            iconImage = IgmMarkerIcons.RED
            map = igmMap
        }

        IgmMarker().apply {
            position = CAMERA_POSITION2.target
            iconImage = IgmMarkerIcons.BLUE
            map = igmMap
        }

        igmMap.addOnCameraChangeListener(this)
        igmMap.addOnCameraIdleListener(this)

        binding.textCameraInfo.visibility = View.VISIBLE
        showCameraPositionInfo(false)

        binding.fab.setOnClickListener(this::onClickFloatingButton)
    }

    private fun showCameraPositionInfo(isMoving: Boolean) {
        val statusText = when (isMoving) {
            true -> "Move"
            false -> "Standby"
        }

        igmMap?.cameraPosition?.let {
            binding.textCameraInfo.text = getString(R.string.igm_format_camera_info,
                statusText, it.target.latitude, it.target.longitude, it.zoom, it.tilt, it.bearing)
        }
    }

    override fun onCameraChange(p: Int) {
        showCameraPositionInfo(true)
    }

    override fun onCameraIdle() {
        showCameraPositionInfo(false)
    }

    private fun onClickFloatingButton(v: View) {
        if (isMovingByButton) {
            igmMap?.cancelTransitions()
            return
        }

        val newCameraPosition = CameraUpdate.newCameraPosition(
            when (isInitPosition) {
                true -> CAMERA_POSITION2
                else -> CAMERA_POSITION1
            }
        ).setAnimationType(CameraAnimationType.Fly, 3000)
            .setCancelCallback {
                isMovingByButton = false
                binding.fab.setImageResource(R.drawable.ic_play_arrow_black_24dp)
                Toast.makeText(this@CameraEventsActivity, R.string.igm_toast_camera_update_cancelled, Toast.LENGTH_SHORT).show()
            }
            .setFinishCallback {
                isMovingByButton = false
                binding.fab.setImageResource(R.drawable.ic_play_arrow_black_24dp)
                Toast.makeText(this@CameraEventsActivity, R.string.igm_toast_camera_update_finished, Toast.LENGTH_SHORT).show()
            }

        igmMap?.moveCamera(newCameraPosition)

        isMovingByButton = true
        binding.fab.setImageResource(R.drawable.ic_stop_black_24dp)

        isInitPosition = !isInitPosition
    }
    companion object {
        private val CAMERA_POSITION1 = IgmCameraPosition(LatLng(25.034146, 121.56452), 14.0, 0.0, 0.0)
        private val CAMERA_POSITION2 = IgmCameraPosition(LatLng(24.157552, 120.66603), 14.0, 0.0, 0.0)
    }
}
