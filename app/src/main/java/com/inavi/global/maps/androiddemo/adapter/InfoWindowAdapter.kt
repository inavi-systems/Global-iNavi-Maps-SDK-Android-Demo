package com.inavi.global.maps.androiddemo.adapter

import android.content.Context
import com.inavi.global.maps.androiddemo.R
import com.inavi.global.mapsdk.style.shapes.IgmInfoWindow
import com.inavi.global.mapsdk.style.shapes.IgmInfoWindowTextAdapter

class InfoWindowAdapter(
    private val context: Context,
) : IgmInfoWindowTextAdapter(context) {

    override fun getText(infoWindow: IgmInfoWindow): CharSequence {
        val marker = infoWindow.marker

        return when (marker != null) {
            true -> context.getString(R.string.igm_format_info_window_on_marker, marker.tag.toString())
            else -> context.getString(R.string.igm_format_info_window_on_map, infoWindow.position.latitude, infoWindow.position.longitude)
        }
    }
}
