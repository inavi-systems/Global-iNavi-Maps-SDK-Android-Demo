package com.inavi.global.maps.androiddemo.view.etc

import android.graphics.PointF
import com.inavi.global.maps.androiddemo.R
import com.inavi.global.maps.androiddemo.databinding.ActivityCoordsProjectionBinding
import com.inavi.global.maps.androiddemo.view.map.IgmMapFragmentActivity
import com.inavi.global.mapsdk.maps.IgmMap

class ProjectionActivity : IgmMapFragmentActivity<ActivityCoordsProjectionBinding>(R.layout.activity_coords_projection),
    IgmMap.OnCameraChangeListener {

    private val crosshairPoint = PointF()

    private var igmMap: IgmMap? = null

    override fun onMapReady(igmMap: IgmMap) {
        this.igmMap = igmMap

        igmMap.addOnCameraChangeListener(this)
        updateProjection()
    }

    override fun onCameraChange(reason: Int) {
        updateProjection()
    }

    private fun updateProjection() {
        val map = igmMap ?: return

        crosshairPoint.set(binding.ivProjectionCross.x + binding.ivProjectionCross.width / 2, binding.ivProjectionCross.y + binding.ivProjectionCross.height / 2)
        binding.tvProjectionInfoScreen.text = getString(R.string.igm_format_misc_projection_coords_screen, crosshairPoint.x, crosshairPoint.y)

        val latLng = map.getProjection().getLatLngFromPoint(crosshairPoint)
        binding.tvProjectionInfoMap.text = getString(R.string.igm_format_misc_projection_coords_map, latLng.latitude, latLng.longitude)
    }
}
