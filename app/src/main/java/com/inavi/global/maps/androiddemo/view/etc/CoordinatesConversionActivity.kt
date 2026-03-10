package com.inavi.global.maps.androiddemo.view.etc

import android.app.AlertDialog
import android.graphics.PointF
import android.os.Bundle
import android.view.View
import com.inavi.global.maps.androiddemo.R
import com.inavi.global.maps.androiddemo.databinding.ActivityCoordinatesConversionBinding
import com.inavi.global.maps.androiddemo.view.map.IgmMapFragmentActivity
import com.inavi.global.mapsdk.geometry.Grs80
import com.inavi.global.mapsdk.geometry.Katec
import com.inavi.global.mapsdk.geometry.LatLng
import com.inavi.global.mapsdk.geometry.Tm
import com.inavi.global.mapsdk.geometry.Utmk
import com.inavi.global.mapsdk.maps.IgmMap

class CoordinatesConversionActivity : IgmMapFragmentActivity<ActivityCoordinatesConversionBinding>(R.layout.activity_coordinates_conversion),
    IgmMap.OnCameraChangeListener {
    private val crosshairPoint = PointF()
    private var currentPosition = LatLng.INVALID
    private var igmMap: IgmMap? = null

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        binding.btCoordinatesConversion.setOnClickListener(this::onClickCoordinatesConversion)
    }

    override fun onMapReady(igmMap: IgmMap) {
        this.igmMap = igmMap

        igmMap.addOnCameraChangeListener(this)

        updateProjection()
    }

    override fun onCameraChange(reason: Int) {
        updateProjection()
    }

    private fun onClickCoordinatesConversion(v: View) {
        val wgs84 = currentPosition
        val katec = Katec(wgs84)
        val utmk = Utmk(wgs84)
        val tm = Tm(wgs84)
        val grs80 = Grs80(wgs84)

        val message = """
KATEC Coordinates
${"(%.5f, %.5f)".format(katec.x, katec.y)}

UTM-K Coordinates
${"(%.5f, %.5f)".format(utmk.x, utmk.y)}

TM Coordinates
${"(%.5f, %.5f)".format(tm.x, tm.y)}

GRS80 Coordinates
${"(%.5f, %.5f)".format(grs80.x, grs80.y)}
"""

        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton(R.string.igm_text_popup_ok, null)
            .show()
    }

    private fun updateProjection() {
        igmMap?.let { map ->
            crosshairPoint.set(binding.ivProjectionCross.x + binding.ivProjectionCross.width / 2, binding.ivProjectionCross.y + binding.ivProjectionCross.height / 2)

            currentPosition = map.getProjection().getLatLngFromPoint(crosshairPoint)
            binding.tvCoordsWgs84Info.text = getString(R.string.igm_format_misc_coordinates_wgs84, currentPosition.latitude, currentPosition.longitude)
        }
    }
}