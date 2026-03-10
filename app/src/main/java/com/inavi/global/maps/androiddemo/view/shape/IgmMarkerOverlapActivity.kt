package com.inavi.global.maps.androiddemo.view.shape

import android.widget.Checkable
import com.inavi.global.maps.androiddemo.R
import com.inavi.global.maps.androiddemo.databinding.ActivityIgmMarkerOverlapBinding
import com.inavi.global.maps.androiddemo.view.map.IgmMapFragmentActivity
import com.inavi.global.mapsdk.geometry.LatLng
import com.inavi.global.mapsdk.maps.IgmMap
import com.inavi.global.mapsdk.style.shapes.IgmMarker
import com.inavi.global.mapsdk.style.shapes.IgmMarkerIcons
import kotlin.random.Random

class IgmMarkerOverlapActivity : IgmMapFragmentActivity<ActivityIgmMarkerOverlapBinding>(R.layout.activity_igm_marker_overlap) {

    override fun onMapReady(igmMap: IgmMap) {
        val markers = mutableListOf<IgmMarker>()
        val bounds = igmMap.getVisibleBounds()
        val markerIcons = mutableListOf(
            IgmMarkerIcons.RED,
            IgmMarkerIcons.GREEN,
            IgmMarkerIcons.BLUE,
            IgmMarkerIcons.YELLOW,
            IgmMarkerIcons.GRAY,
        )

        for (i in 1..50) {
            IgmMarker().apply {
                position = LatLng(
                    (bounds.northLatitude - bounds.southLatitude) * Math.random() + bounds.southLatitude,
                    (bounds.eastLongitude - bounds.westLongitude) * Math.random() + bounds.westLongitude
                )
                iconImage = markerIcons[Random.nextInt(markerIcons.size)]
                title = "Marker #$i"
                map = igmMap
            }.let { markers.add(it) }
        }

        binding.allowOverlapMarkers.setOnClickListener { v ->
            if (v is Checkable) {
                val checked = v.isChecked
                v.isChecked = !checked
                for (marker in markers) {
                    marker.allowOverlapMarkers = !checked
                }
            }
        }
    }
}
