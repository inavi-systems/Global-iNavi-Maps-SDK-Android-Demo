package com.inavi.global.maps.androiddemo.view.location

import android.graphics.Color
import android.graphics.PointF
import android.os.Bundle
import androidx.core.graphics.ColorUtils
import com.inavi.global.maps.androiddemo.R
import com.inavi.global.maps.androiddemo.databinding.ActivityLocationIconBinding
import com.inavi.global.maps.androiddemo.utils.extension.dpToPx
import com.inavi.global.maps.androiddemo.utils.extension.setCheckListener
import com.inavi.global.maps.androiddemo.view.map.IgmMapFragmentActivity
import com.inavi.global.mapsdk.geometry.LatLng
import com.inavi.global.mapsdk.maps.IgmLocationIcon
import com.inavi.global.mapsdk.maps.IgmMap
import com.inavi.global.mapsdk.maps.UserTrackingMode
import com.inavi.global.mapsdk.style.shapes.IgmImage

class LocationIconActivity : IgmMapFragmentActivity<ActivityLocationIconBinding>(R.layout.activity_location_icon),
    IgmMap.OnMapClickListener {

    private var igmMap: IgmMap? = null
    private var locationIcon: IgmLocationIcon? = null

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
    }

    override fun onMapReady(igmMap: IgmMap) {
        this.igmMap = igmMap
        this.locationIcon = igmMap.getLocationIcon()

        igmMap.enableLocationComponent(this@LocationIconActivity)
        igmMap.setUserTrackingMode(UserTrackingMode.TrackingCompass)

        igmMap.setOnMapClickListener(this)

        binding.optionVisible.setCheckListener { isChecked ->
            igmMap.setUserTrackingMode(if (!isChecked) UserTrackingMode.None else UserTrackingMode.TrackingCompass)
            binding.optionImage.isEnabled = isChecked
            binding.optionScale.isEnabled = isChecked
            binding.optionCircleRadius.isEnabled = isChecked
            binding.optionCircleColor.isEnabled = isChecked
        }

        binding.optionImage.setCheckListener { isChecked ->
            locationIcon?.imageTrackingCompass = when (isChecked) {
                true -> IgmImage(R.drawable.ic_launcher_foreground)
                else -> IgmLocationIcon.DEFAULT_IMAGE
            }
        }

        binding.optionScale.setCheckListener { isChecked ->
            locationIcon?.scale = when (isChecked) {
                true -> 2.0f
                else -> 1.0f
            }
        }

        binding.optionCircleRadius.setCheckListener { isChecked ->
            locationIcon?.circleRadius = when (isChecked) {
                true -> 48.dpToPx()
                else -> IgmLocationIcon.DEFAULT_CIRCLE_RADIUS.dpToPx()
            }
        }

        binding.optionCircleColor.setCheckListener { isChecked ->
            locationIcon?.circleColor = when (isChecked) {
                true -> ColorUtils.setAlphaComponent(Color.YELLOW, 128)
                else -> IgmLocationIcon.DEFAULT_CIRCLE_COLOR
            }
        }
    }

    override fun onMapClick(point: PointF, coords: LatLng) {
    }
}